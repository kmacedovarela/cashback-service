package org.acme.cashback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseEvent {
    @JsonProperty("sale_id")
    private Long saleId;

    @JsonProperty("op")
    private Character operation;

    public ExpenseEvent() {
    }

    public ExpenseEvent(Long saleId, Character operation) {
        this.saleId = saleId;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ExpenseEvent{" +
                "saleId=" + saleId +
                ", operation=" + operation +
                '}';
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Character getOperation() {
        return operation;
    }

    public void setOperation(Character operation) {
        this.operation = operation;
    }

    public boolean isUpdate(){
        return this.operation.equals("u");
    }

    public boolean isCreate(){
        return this.operation.equals("c");
    }

}
