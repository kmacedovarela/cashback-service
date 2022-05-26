package org.acme.cashback.processor;

import org.acme.cashback.enums.CustomerStatus;
import org.acme.cashback.model.CashbackDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Named("valuesProcessor")
@ApplicationScoped
public class ValuesProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Map body = exchange.getMessage().getBody(Map.class);
        BigDecimal newEarnedCashbackForSingleExpense;
        BigDecimal earnedCashbackForExpense;
        CashbackDTO cashbackDTO;

        cashbackDTO = buildCashbackDTO(body);
        earnedCashbackForExpense = calculateAmountCashback(cashbackDTO);

        if(exchange.getMessage().getHeader("operation").toString().equals("c")){
            cashbackDTO = buildCashbackDTO(body);
            earnedCashbackForExpense = calculateAmountCashback(cashbackDTO);

            // updates single expense incoming cashback value
            cashbackDTO.setExpenseEarnedAmount(earnedCashbackForExpense);

            // updates cashback wallet
            cashbackDTO.addToCashbackAmount(earnedCashbackForExpense);

        }else if(exchange.getMessage().getHeader("operation").toString().equals("u")){
            BigDecimal originalCashbackAmount = ((BigDecimal) body.get("cashback_amount"));
            BigDecimal originalSingleExpenseCashback = ((BigDecimal) body.get("expense_earned_cashback"));
            newEarnedCashbackForSingleExpense = calculateAmountCashback(cashbackDTO);

            calculateAmountCashback(cashbackDTO);

            // updates single expense cashback value
            cashbackDTO.setExpenseEarnedAmount(newEarnedCashbackForSingleExpense);

            // updates wallet values
            cashbackDTO.subtractFromCashbackAmount(originalSingleExpenseCashback); //subtracts outdated value
            cashbackDTO.addToCashbackAmount(newEarnedCashbackForSingleExpense);
        }

        System.out.println("Adding to cashback "+cashbackDTO.getExpenseEarnedAmount());
        exchange.getIn().setBody(cashbackDTO);
    }
    /**
     * Returns x percent of input expenseAmount.
     * Output format is rounded as:  11.12345 returned as 11.12, 11.12556, returned as 11.13
     */

    public BigDecimal calculateAmountCashback(CashbackDTO cashbackDTO){
        BigDecimal percentage = CustomerStatus.get(cashbackDTO.getCustomerStatus().toLowerCase()).getCashbackPercentage();
        return percentage.multiply(cashbackDTO.getExpenseAmount()).setScale(2, RoundingMode.HALF_EVEN);
    }

    private CashbackDTO buildCashbackDTO(Map body) {
        CashbackDTO cashbackDTO =
                new CashbackDTO
                        .Builder((Long) body.get("customer_id"))
                        .cashbackId((Long) body.get("cashback_id"))
                        .saleId((Long) body.get("sale_id"))
                        .expenseAmount((BigDecimal) body.get("expense_amount"))
                        .cashbackAmount((BigDecimal) body.get("cashback_amount"))
                        .customerStatus((String) body.get("customer_status"))
                        .build();

        return cashbackDTO;
    }
}

