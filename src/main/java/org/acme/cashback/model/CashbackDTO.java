package org.acme.cashback.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CashbackDTO {
    private Long customerId;
    private Long saleId;
    private Long cashbackId;
    private BigDecimal expenseAmount;
    private BigDecimal cashbackAmount;
    private BigDecimal expenseEarnedAmount;
    private String customerStatus;

    public void setExpenseEarnedAmount(BigDecimal incomingExpenseEarnedAmount) {
        expenseEarnedAmount = incomingExpenseEarnedAmount;
    }

    public void subtractFromCashbackAmount(BigDecimal incomingExpenseEarnedAmount) {
        cashbackAmount = cashbackAmount.subtract(incomingExpenseEarnedAmount);
    }

    public void addToCashbackAmount(BigDecimal incomingExpenseEarnedAmount) {
        if(cashbackAmount == null){
            cashbackAmount = new BigDecimal(BigInteger.ZERO);
        }

        cashbackAmount = cashbackAmount.add(incomingExpenseEarnedAmount);
    }

    @Override
    public String toString() {
        return "CashbackDTO{" +
                "customerId=" + customerId +
                ", saleId=" + saleId +
                ", cashbackId=" + cashbackId +
                ", expenseAmount=" + expenseAmount +
                ", cashbackAmount=" + cashbackAmount +
                ", expenseEarnedAmount=" + expenseEarnedAmount +
                ", customerStatus='" + customerStatus + '\'' +
                '}';
    }

    /**
     * ToString and Constructor
     */


    private CashbackDTO(){}
    private CashbackDTO(Long customerId, Long saleId, Long cashbackId, BigDecimal expenseAmount, BigDecimal cashbackAmount, BigDecimal expenseEarnedAmount, String customerStatus, BigDecimal earnedCashback) {
        this.customerId = customerId;
        this.saleId = saleId;
        this.cashbackId = cashbackId;
        this.expenseAmount = expenseAmount;
        this.cashbackAmount = cashbackAmount;
        this.customerStatus = customerStatus;
        this.expenseEarnedAmount = earnedCashback;
    }


    /**
     * Getters
     */
    public BigDecimal getExpenseEarnedAmount() {
        return expenseEarnedAmount;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public Long getSaleId() {
        return saleId;
    }
    public Long getCashbackId() {
        return cashbackId;
    }
    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }
    public String getCustomerStatus() {return customerStatus;}
    public BigDecimal getCashbackAmount() {return (cashbackAmount == null) ? BigDecimal.ZERO : cashbackAmount;}



    /**
     * Builder
     */

    public static class Builder {
        private Long customerId;
        private Long saleId;
        private Long cashbackId;
        private BigDecimal expenseAmount;
        private BigDecimal cashbackAmount;
        private String customerStatus;
        private BigDecimal earnedCashback;

        public Builder (Long customerId) {
            this.customerId = customerId;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder saleId(Long saleId) {
            this.saleId = saleId;
            return this;
        }

        public Builder cashbackId(Long cashbackId) {
            this.cashbackId = cashbackId;
            return this;
        }

        public Builder expenseAmount(BigDecimal expenseAmount) {
            this.expenseAmount = expenseAmount;
            return this;
        }

        public Builder earnedCashback(BigDecimal earnedCashback) {
            this.earnedCashback = earnedCashback;
            return this;
        }

        public Builder cashbackAmount(BigDecimal cashbackAmount) {
            this.cashbackAmount = cashbackAmount;
            return this;
        }


        public Builder customerStatus(String customerStatus) {
            this.customerStatus = customerStatus;
            return this;
        }

        public CashbackDTO build() {
            CashbackDTO dto = new CashbackDTO();
            dto.cashbackId = this.cashbackId;
            dto.customerId = customerId;
            dto.saleId = saleId;
            dto.expenseAmount = expenseAmount;
            dto.cashbackAmount = cashbackAmount;
            dto.customerStatus = customerStatus;
            return dto;
        }
    }
}


