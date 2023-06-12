package com.lb.springboot.controller;


import com.lb.springboot.service.GreetingAnnotationService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/annotation")
@RestController
public class AnnotationController {
    @DubboReference(version = "v1.0", group = "qa")
    private GreetingAnnotationService greetingAnnotationService;
    @DubboReference(version = "v2.0")
    private GreetingAnnotationService greetingAnnotationServiceV2;
    @DubboReference(version = "v3.0", group = "qa")
    private GreetingAnnotationService greetingAnnotationServiceV3;

    @GetMapping("/v1")
    public String v1(){
        return greetingAnnotationService.sayHi("v1");
    }

    @GetMapping("/v2")
    public String v2(){
        return greetingAnnotationServiceV2.sayHi("v2");
    }

    @GetMapping("/v3")
    public String v3(){
        return greetingAnnotationServiceV3.sayHi("v3");
    }
}
