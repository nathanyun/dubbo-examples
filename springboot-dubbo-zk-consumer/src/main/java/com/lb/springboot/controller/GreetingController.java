package com.lb.springboot.controller;

import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述:GreetingController <br/>
 *
 * @author yunnasheng
 * @date: 2020-12-30 17:20<br/>
 * @since JDK 1.8
 */
@RestController
public class GreetingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @DubboReference(version = "1.0",url = "dubbo://10.211.55.5:20800")
    private GreetingService greetingService;

    @GetMapping(value = "index")
    public String index(@RequestParam(defaultValue = "World") String name){
        LOGGER.info("execute index start - name: {}",name);
        return greetingService.sayHi(name);
    }
}
