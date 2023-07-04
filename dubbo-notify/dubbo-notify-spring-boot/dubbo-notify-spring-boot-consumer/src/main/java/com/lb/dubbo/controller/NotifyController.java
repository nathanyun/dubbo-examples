package com.lb.dubbo.controller;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("test")
@RestController
public class NotifyController {

    @DubboReference(timeout = 6000, methods = @Method(name = "sayHi", oninvoke = "notifyServiceImpl.onInvoke", onreturn = "notifyServiceImpl.onReturn", onthrow = "notifyServiceImpl.onThrow"))
    private GreetingService greetingService;

    @GetMapping("sayHi")
    public String sayHi(@RequestParam String name){
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(start + "  准备请求远程调用...");

        String result = greetingService.sayHi(name);

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(time + "  sayHi result = " + result);
        return result;
    }
}
