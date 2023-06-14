package com.lb.springboot.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Accessors(fluent = true)
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 377017377423825303L;

    private Long id;
    private String productName;
    private BigDecimal amount;
    private Date date;

    public Order() {
    }

    public Order(Long id, String productName, BigDecimal amount, Date date) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.date = date;
    }
}
