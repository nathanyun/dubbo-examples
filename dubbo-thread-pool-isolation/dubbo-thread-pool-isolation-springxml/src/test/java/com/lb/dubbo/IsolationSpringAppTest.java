package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dubbo-consumer.xml"})
public class IsolationSpringAppTest {

    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;

    @Autowired
    @Qualifier("orderServiceV2")
    private OrderService orderServiceV2;

    @Autowired
    @Qualifier("greetingService")
    private GreetingService greetingService;

    @Test
    public void greetingTest() {
        CompletableFuture<String> future = greetingService.sayHi("jack");
        // 增加回调
        future.whenComplete((BiConsumer<Object, Throwable>) (result, throwable) -> {
            if (throwable == null) {
                System.out.println(LocalDateTime.now() + " - Response: " + result);
            } else {
                throwable.printStackTrace();
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果
        LockSupport.parkNanos(3000000000L);
    }
    @Test
    public void orderTest() {
        CompletableFuture<String> future = orderService.create("MEIZU20PRO");
        // 增加回调
        future.whenComplete((BiConsumer<Object, Throwable>) (result, throwable) -> {
            if (throwable == null) {
                System.out.println(LocalDateTime.now() + " - Response: " + result);
            } else {
                throwable.printStackTrace();
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果
        LockSupport.parkNanos(3000000000L);
    }

    @Test
    public void orderV2Test() {
        CompletableFuture<String> future = orderServiceV2.create("HUAWEIP60ART");
        // 增加回调
        future.whenComplete((BiConsumer<Object, Throwable>) (result, throwable) -> {
            if (throwable == null) {
                System.out.println(LocalDateTime.now() + " - Response: " + result);
            } else {
                throwable.printStackTrace();
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果
        LockSupport.parkNanos(3000000000L);
    }

}
