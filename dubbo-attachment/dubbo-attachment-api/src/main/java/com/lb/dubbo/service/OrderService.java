package com.lb.dubbo.service;

public interface OrderService {

    /**
     * 下单
     * @param name
     * @return
     */
    default String create(String name){
        return "Hi, " + name +" Power by default!";
    }

}
