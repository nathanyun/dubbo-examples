package com.lb.springboot.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Accessors(fluent = true)
@Data
public class Order implements Serializable {

    private Long id;
    private String productName;
    private BigDecimal amount;
    private Date date;
}
