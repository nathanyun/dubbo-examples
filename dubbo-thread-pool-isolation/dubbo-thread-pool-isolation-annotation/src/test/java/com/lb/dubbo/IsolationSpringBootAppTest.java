package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;

@SpringBootTest
public class IsolationSpringBootAppTest {
//    private static AnnotationConfigApplicationContext context;
//    @Before
//    public void startContext(){
//        context = new AnnotationConfigApplicationContext(IsolationAnnotationApplication.class);
//        context.refresh();
//    }

    @Autowired
    private GreetingService greetingService;

    @Test
    public void greetingTest() {
        //GreetingService greetingService = (GreetingService) context.getBean("greetingService");

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
//    @Test
//    public void orderTest() {
//        OrderService orderService = (OrderService) context.getBean("orderService");
//
//        CompletableFuture<String> future = orderService.create("MEIZU20PRO");
//        // 增加回调
//        future.whenComplete((BiConsumer<Object, Throwable>) (result, throwable) -> {
//            if (throwable == null) {
//                System.out.println(LocalDateTime.now() + " - Response: " + result);
//            } else {
//                throwable.printStackTrace();
//            }
//        });
//        // 早于结果输出
//        System.out.println(LocalDateTime.now() + " - Executed before response return.");
//        //阻塞等待5秒,以便获取响应结果
//        LockSupport.parkNanos(3000000000L);
//    }
//
//    @Test
//    public void orderV2Test() {
//        OrderService orderServiceV2 = (OrderService) context.getBean("orderServiceV2");
//        CompletableFuture<String> future = orderServiceV2.create("HUAWEIP60ART");
//        // 增加回调
//        future.whenComplete((BiConsumer<Object, Throwable>) (result, throwable) -> {
//            if (throwable == null) {
//                System.out.println(LocalDateTime.now() + " - Response: " + result);
//            } else {
//                throwable.printStackTrace();
//            }
//        });
//        // 早于结果输出
//        System.out.println(LocalDateTime.now() + " - Executed before response return.");
//        //阻塞等待5秒,以便获取响应结果
//        LockSupport.parkNanos(3000000000L);
//    }

}
