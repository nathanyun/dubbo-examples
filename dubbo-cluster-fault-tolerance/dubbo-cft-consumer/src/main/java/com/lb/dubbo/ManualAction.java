package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.common.constants.ClusterRules;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/action")
@RestController
public class ManualAction {

    @DubboReference
    private GreetingService greetingService;

    /**
     * 消费端调用指定集群容错模式
     */
    @DubboReference(cluster = ClusterRules.FAIL_FAST, loadbalance = LoadbalanceRules.CONSISTENT_HASH)
    private OrderService orderService;

    @GetMapping("/hi")
    public String hi(String name, int second){
        return greetingService.sayHi(name, second);
    }

    @GetMapping("save")
    public long save(int second){
        return orderService.save(second);
    }
}
