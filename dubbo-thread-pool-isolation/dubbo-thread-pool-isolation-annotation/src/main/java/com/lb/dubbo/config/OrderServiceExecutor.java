package com.lb.dubbo.config;

import org.apache.dubbo.common.utils.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OrderServiceExecutor extends ThreadPoolExecutor {

    public OrderServiceExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, int queueLength) {

        super(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueLength) ,
                new NamedThreadFactory("orderThread"),
                new AbortPolicy());
    }
}
