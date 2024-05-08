package org.example.models;

public class Payment {
    private String id;
    private double amount;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }
}
