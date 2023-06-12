package com.lb.springboot;

import com.lb.springboot.service.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/consumer")
@RestController
public class SayHiController {

    /**
     * 指定版本:version = "v2.0"
     * 超时:timeout = 3000  3秒
     * 重试:retries = 2  重试2次
     * 负载均衡模式: loadbalance = "roundrobin" 轮询加权方式 {@link org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance}
     *
     */
    @DubboReference(version = "v2.0", timeout = 3000, retries = 2, loadbalance = "roundrobin")
    private GreetingService greetingService;

    @GetMapping("/hi")
    public String hi(String name){
        return greetingService.sayHi(name);
    }
}
