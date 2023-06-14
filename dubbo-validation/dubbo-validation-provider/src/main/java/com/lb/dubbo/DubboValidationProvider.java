package com.lb.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

public class DubboValidationProvider {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();

        //阻塞主线程
        System.in.read();
    }
}
