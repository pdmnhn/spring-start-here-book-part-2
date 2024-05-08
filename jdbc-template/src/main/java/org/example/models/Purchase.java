package org.example.models;

import java.math.BigDecimal;


public class Purchase {
    private Integer id;
    private String product;
    private BigDecimal price;

    public Purchase(int id, String product, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.price = price;
    }

    public Purchase() {}

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(final String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
