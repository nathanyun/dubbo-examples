package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @DubboService 指定为Dubbo服务
 *
 * @author yunnasheng
 * @date: 2020-12-30 17:14<br/>
 * @since JDK 1.8
 */
@DubboService
public class GreetingServiceImpl implements GreetingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHi(String name) {
        logger.info("GreetingServiceImpl start name :{}", name);
        return String.format("Hi, %s",name);
    }
}
