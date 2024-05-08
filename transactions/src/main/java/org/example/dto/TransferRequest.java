package org.example.dto;

import java.math.BigDecimal;


public class TransferRequest {
    private int srcActId;
    private int dstActId;
    private BigDecimal amount;

    public int getSrcActId() {
        return srcActId;
    }

    public void setSrcActId(final int srcActId) {
        this.srcActId = srcActId;
    }

    public int getDstActId() {
        return dstActId;
    }

    public void setDstActId(final int dstActId) {
        this.dstActId = dstActId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
