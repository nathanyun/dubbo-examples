package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dubbo-consumer.xml"})
public class GenericAppTest {

    /**
     * 服务端定义异步接口, 消费端异步调用
     */
    @Test
    public void simpleAsyncCallTest(){
        // 调用直接返回CompletableFuture


        CompletableFuture<String> future = greetingService.sayHi("async call");
        // 增加回调
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println(LocalDateTime.now() + " - Response: " + result);
            } else {
                exception.printStackTrace();
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果
        LockSupport.parkNanos(5000000000L);
    }

    /**
     * 利用RpcContext实现消费端异步
     */
    @Test
    public void rpcContextTest(){
        // 此调用会立即返回null
        String result = asyncService.sayHello("RpcContext");
        System.out.println(LocalDateTime.now() + " - Executed first response: " + result);

        // 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future
        CompletableFuture<String> helloFuture = RpcContext.getServiceContext().getCompletableFuture();
        // 为Future添加回调
        helloFuture.whenComplete((retValue, exception) -> {
            if (exception == null) {
                System.out.println(LocalDateTime.now() + " - Response: " + retValue);
            } else {
                exception.printStackTrace();
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒
        LockSupport.parkNanos(5000000000L);
    }

    /**
     * 另一种消费端异步写法
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void rpcContextTest2() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> future = RpcContext.getServiceContext().asyncCall(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // 此调用会立即返回null
                String result = asyncService.sayHello("RpcContext");
                System.out.println(LocalDateTime.now() + " - Executed first response: " + result);
                return result;
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return." );
        //阻塞等待结果
        System.out.println(LocalDateTime.now() + " - Response: " + future.get());
    }
}
