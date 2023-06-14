package com.lb.dubbo.service;

import com.lb.dubbo.dto.Order;

public interface OrderService {

    String find();

    Order create(Order order);
}
