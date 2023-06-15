package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.common.constants.ClusterRules;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 只发起一次调用,失败立即报错
 */
@DubboService(cluster = ClusterRules.FAIL_FAST, loadbalance = LoadbalanceRules.CONSISTENT_HASH)
public class OrderServiceImpl implements OrderService {
    private final AtomicLong sum = new AtomicLong(0);

    @Value(value = "${dubbo.application.name}")
    private String appName;

    @Override
    public long save(int sleepSecond) {
        System.out.println("Save appName ===> " + appName + " time ===>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS")));
        try {
            TimeUnit.SECONDS.sleep(sleepSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sum.incrementAndGet();
    }
}
