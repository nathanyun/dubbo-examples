package com.lb.springboot.service.impl;

import com.lb.springboot.dto.Order;
import com.lb.springboot.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Order create(Order order) {
        log.info("OrderServiceImpl create ...");
        return new Order().id(order.id())
                .productName("iPhone17")
                .date(new Date())
                .amount(BigDecimal.valueOf(Math.random()).setScale(2, RoundingMode.UP));
    }
}
