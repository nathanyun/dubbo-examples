package com.lb.dubbo.service.impl;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @DubboService 指定为Dubbo服务
 *
 * @author yunnasheng
 * @date: 2020-12-30 17:14<br/>
 * @since JDK 1.8
 */
@DubboService(version = "v1.0.2")
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHi(String name) {
        System.out.println("Hi v1.0.2 ===> " + name);
        return String.format("Hi, %s",name);
    }
}
