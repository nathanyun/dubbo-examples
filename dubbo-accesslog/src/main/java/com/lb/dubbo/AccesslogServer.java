package com.lb.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

public class AccesslogServer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();

        //阻塞主线程
        LockSupport.park();
    }
}
