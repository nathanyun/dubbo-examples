package com.lb.dubbo.service;

public interface DeliverService {

    /**
     * 发货
     * @param name
     * @return
     */
    default String deliver(String name){
        return "Hi, " + name +" Power by default!";
    }

}
