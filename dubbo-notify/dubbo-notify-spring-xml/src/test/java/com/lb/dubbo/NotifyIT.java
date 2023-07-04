package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.rpc.RpcContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dubbo-consumer.xml")
public class NotifyIT {

    @Autowired
    private GreetingService greetingService;

    @Test
    public void testOnInvokeAndOnReturn() throws ExecutionException, InterruptedException {
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(start + "  准备请求远程调用...");

        //远程调用
        String syncResult = greetingService.sayHi("bonny");
        //同步响应必为Null ,因为方法配置了 async="true"
        Assert.assertNull( "syncResult must be null", syncResult);

        //获取异步响应
        Object asyncResult = RpcContext.getServiceContext().getFuture().get();
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(end + "  sayHi result = " + asyncResult);
    }

    @Test
    public void testOnThrow() throws ExecutionException, InterruptedException {
        String start = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(start + "  准备请求远程调用...");

        //远程调用
        String syncResult = greetingService.sayHi("bonny2");
        //同步响应必为Null ,因为方法配置了 async="true"
        Assert.assertNull( "syncResult must be null", syncResult);

        //获取异步响应
        Object asyncResult = RpcContext.getServiceContext().getFuture().get();
        String end = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(end + "  sayHi result = " + asyncResult);
    }
}
