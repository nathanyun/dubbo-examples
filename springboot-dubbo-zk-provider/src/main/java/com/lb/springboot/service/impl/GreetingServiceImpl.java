package com.lb.springboot.service.impl;

import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 功能描述:GreetingServiceImpl <br/>
 *
 * @author yunnasheng
 * @date: 2020-12-30 17:14<br/>
 * @since JDK 1.8
 */
@DubboService(version = "1.0")
public class GreetingServiceImpl implements GreetingService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Random costTimeRandom = new Random();

    @Override
    public String sayHi(String name) {
        await();
        return String.format("Hi, %s",name);
    }

    private void await() {
        try {
            long timeInMillisToWait = costTimeRandom.nextInt(500);
            Thread.sleep(timeInMillisToWait);
            logger.info("execution time : " + timeInMillisToWait + " ms.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
