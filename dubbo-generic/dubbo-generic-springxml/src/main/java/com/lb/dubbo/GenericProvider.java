package com.lb.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class GenericProvider {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();
        System.out.println("dubbo service started");
        //阻塞主线程
        LockSupport.park();
    }
}
