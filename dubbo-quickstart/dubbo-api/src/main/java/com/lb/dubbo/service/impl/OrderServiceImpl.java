package com.lb.dubbo.service.impl;

import com.lb.dubbo.dto.Order;
import com.lb.dubbo.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public String find() {
        System.out.println("Order find ===>");
        return "ok";
    }

    @Override
    public Order create(Order order) {
        System.out.println("Order create ===>" + order);
        return new Order().id(order.id())
                .productName("iPhone17")
                .date(new Date())
                .amount(BigDecimal.TEN);
    }
}
