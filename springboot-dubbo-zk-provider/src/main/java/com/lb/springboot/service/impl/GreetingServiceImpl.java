package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 功能描述:GreetingServiceImpl <br/>
 *
 * @author yunnasheng
 * @date: 2020-12-30 17:14<br/>
 * @since JDK 1.8
 */
@DubboService(version = "1.0",registry = "${dubbo.registry.server}")
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHi(String name) {
        return String.format("Hi, %s",name);
    }
}
