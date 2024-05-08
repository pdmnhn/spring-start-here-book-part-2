package org.example.models;

import java.math.BigDecimal;
import org.springframework.data.annotation.Id;


public class Account {
    @Id
    private Integer id;
    private String name;
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
}
