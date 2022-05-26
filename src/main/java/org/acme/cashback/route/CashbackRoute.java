package org.acme.cashback.route;

import org.acme.cashback.model.ExpenseEvent;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CashbackRoute extends RouteBuilder {

    private final static String INVALID_OPERATOR_JSON_PATH = "$..[?(@.op =~ /d|r|t|m/)]";

    @SuppressWarnings({"unchecked"})
    @Override
    public void configure() throws Exception {

        from("kafka:{{kafka.expenses.topic.name}}?groupId={{kafka.cashback_processor.consumer.group}}" +
                "&autoOffsetReset=earliest")
                .routeId("CashbackProcessor")
                .unmarshal(new JacksonDataFormat(ExpenseEvent.class))
                .setHeader("operation", simple("${body.operation}"))
                .setHeader("sale_id", simple("${body.saleId}"))
                .to("direct:filterInvalidOperationCodes")
                .to("direct:getData")
                .to("direct:filterInvalidData")
                .choice()
                .when().simple("${header.operation} == 'c'").log(LoggingLevel.DEBUG,"Processing create event")
                    .process("valuesProcessor")
                    .choice()
                        .when(simple("${body.cashbackId} == null"))
                            .log(LoggingLevel.DEBUG, "No cashback wallet exists. Creating new cashback for: ${body}")
                            .to("direct:createAndPersistCashback")
                    .end()
                    .to("direct:updateEarnedCashbackData")
                .endChoice()
                .otherwise().when().simple("${header.operation}== 'u'").log(LoggingLevel.DEBUG,"Processing update event")
                    .process("valuesProcessor")
                    .to("direct:updateEarnedCashbackData")
                .end();

    /**
     *  filters
     */
    from("direct:filterInvalidOperationCodes")
            .routeId("filterInvalidOperationCodes")
            .filter()
            .jsonpath(INVALID_OPERATOR_JSON_PATH)
            .log(LoggingLevel.DEBUG, "Filtering out change event which is not 'create' or 'update'")
            .stop().end();

    from("direct:filterInvalidData")
            .routeId("filterInvalidData")
            .filter()
            .simple("${body} == null")
            .log(LoggingLevel.ERROR, "Filtering out message with nonexistent expense or customer id ${body}")
            .stop().end();

    /**
     * Database operations
     */
    from("direct:getData")
            .routeId("getData")
            .setHeader("sale_id", simple("${body.saleId}"))
            .setBody(constant(
                  " SELECT " +
                            " cust.customer_id, cash.cashback_id, e.sale_id, e.amount as expense_amount," +
                            " cash.amount as cashback_amount, cust.status as customer_status," +
                            " e.earned_cashback as expense_earned_cashback"+
                        " FROM expense e" +
                            " left join customer cust on e.customer_id = cust.customer_id" +
                            " left join cashback cash on cust.customer_id = cash.customer_id" +
                        " WHERE sale_id = :?sale_id"))
            .to("jdbc:cashback?useHeadersAsParameters=true&outputType=SelectOne")
            .log(LoggingLevel.DEBUG, "[SQL] Obtaining data from DB for ${header.sale_id}: ${body}");

    from("direct:createAndPersistCashback")
            .routeId("createAndPersistCashback")
            .setHeader("body", body())
            .setBody(simple("INSERT INTO cashback (cashback_id, customer_id) VALUES (${body.customerId}, ${body.customerId});" +
                    "UPDATE expense SET cashback_id =${body.customerId}"))
            .log(LoggingLevel.DEBUG, "[SQL] Saving cashback: ${body}")
            .to("jdbc:cashback")
            .setBody(header("body"));

        from("direct:updateEarnedCashbackData")
                .routeId("updateEarnedCashbackData")
                .setHeader("customer_id", simple("${body.customerId}"))
                .setHeader("sale_id", simple("${body.saleId}"))
                .setHeader("earned_cashback", simple("${body.expenseEarnedAmount}"))
                .setHeader("cashback_amount", simple("${body.cashbackAmount}"))
                .setBody(constant(
                        " UPDATE expense SET earned_cashback = :?earned_cashback WHERE sale_id = :?sale_id;" +
                                " UPDATE cashback SET amount = :?cashback_amount WHERE customer_id = :?customer_id;"))
                .log(LoggingLevel.DEBUG, "[SQL] Updating cashback values: SQL: ${body} parameteres: ${headers}")
                .to("jdbc:cashback?useHeadersAsParameters=true");

    }
}
