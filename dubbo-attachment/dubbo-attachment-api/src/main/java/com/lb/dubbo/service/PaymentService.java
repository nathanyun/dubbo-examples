package com.lb.dubbo.service;

public interface PaymentService {
    /**
     * 支付
     * @param name
     * @return
     */
    default String payment(String name){
        return "Hi, " + name +" Power by default!";
    }

}
