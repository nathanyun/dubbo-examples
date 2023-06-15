package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.common.constants.ClusterRules;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

/**
 * ClusterRules.FAIL_OVER 失败自动切换
 * LoadbalanceRules.ROUND_ROBIN 负载均衡 轮询加权访问
 * timeout = 2000 超时时间2秒
 * retries=2 重试2次,不包含首次
 */
@DubboService(cluster = ClusterRules.FAIL_OVER, retries = 2, timeout = 2000, loadbalance = LoadbalanceRules.ROUND_ROBIN)
public class GreetingServiceImpl implements GreetingService {

    @Value(value = "${dubbo.application.name}")
    private String appName;

    @Override
    public String sayHi(String name, int sleepSecond) {
        System.out.println("Hi appName ===> " + appName);
        try {
            TimeUnit.SECONDS.sleep(sleepSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return String.format("Hi, %s",name);
    }

}
