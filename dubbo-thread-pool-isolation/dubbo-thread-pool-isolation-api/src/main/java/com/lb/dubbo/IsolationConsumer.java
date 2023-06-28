package com.lb.dubbo;

import com.lb.dubbo.service.GreetingService;
import com.lb.dubbo.service.OrderService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiFunction;

public class IsolationConsumer {

    /**
     * 线程池隔离消费端
     * @param args
     */
    public static void main(String[] args){
        //应用配置
        ApplicationConfig application = new ApplicationConfig("isolation-consumer-app");
        //注册中心
        RegistryConfig registry = new RegistryConfig("zookeeper://127.0.0.1:2181");

        //远程接口配置1
        ReferenceConfig<OrderService> reference1 = new ReferenceConfig<>();
        reference1.setInterface(OrderService.class);
        reference1.setProtocol("dubbo");
        reference1.setTimeout(5000);
        reference1.setRegistry(registry);
        reference1.setApplication(application);

        //远程接口配置2
        ReferenceConfig<GreetingService> reference2 = new ReferenceConfig<>();
        reference2.setInterface(GreetingService.class);
        reference2.setProtocol("tri");
        reference2.setTimeout(5000);
        reference2.setRegistry(registry);
        reference2.setApplication(application);

        //获取接口
        OrderService orderService = reference1.get();
        GreetingService greetingService = reference2.get();
        //并发调用接口方法
        for (int i = 0; i < 3; i++) {
            greeting(greetingService);
            //order(orderService);
        }
        //阻塞主线程
        //LockSupport.park();
    }

    private static void order(OrderService service){
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompletableFuture<String> future = service.create("HUAWEI MATE 60 PRO");
                //获取异步结果
                future.handleAsync(new BiFunction<String, Throwable, Object>() {
                    @Override
                    public Object apply(String s, Throwable throwable) {
                        if (throwable != null){
                            throwable.printStackTrace();
                        }else {
                            System.out.println("order handleAsync result = " + s);
                        }
                        return null;
                    }
                });
            }
        }).start();
    }

    private static void greeting(GreetingService greetingService){
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompletableFuture<String> future = greetingService.sayHi("XIAOMI");
                //获取异步结果
                future.handleAsync(new BiFunction<String, Throwable, Object>() {
                    @Override
                    public Object apply(String s, Throwable throwable) {
                        if (throwable != null){
                            throwable.printStackTrace();
                        }else {
                            System.out.println("greeting handleAsync result = " + s);
                        }
                        return null;
                    }
                });
            }
        }).start();
    }
}
