package com.lb.dubbo;

import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcServiceContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

public class GenericConsumer {

    /**
     * 泛化调用消费端
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();

        //将spring容器中的greetingService强转为泛化调用service
        GenericService genericService = (GenericService) context.getBean("greetingService");

        //同步调用
        invokeRpcContextGenericCase(genericService);

        //异步调用
        invokeAsyncGenericCase(genericService);
    }

    private static void invokeRpcContextGenericCase(GenericService genericService){
        RpcServiceContext serviceContext = RpcContext.getServiceContext();
        CompletableFuture<Object> future = serviceContext.asyncCall(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"Nathan"});
                System.out.println("syncResult = " + result);
                return result;
            }
        });

        future.whenComplete(new BiConsumer<Object, Throwable>() {
            @Override
            public void accept(Object o, Throwable throwable) {
                if (throwable != null){
                    throwable.printStackTrace();
                }else{
                    System.out.println("realResult = " + o);
                }
            }
        });
    }

    private static void invokeAsyncGenericCase(GenericService genericService) throws ExecutionException, InterruptedException {
        //异步调用,指定方法名和参数进行调用
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHi", new String[]{"java.lang.String"}, new Object[]{"Jack"});
        //future.get()会阻塞等待响应结果
        Object asyncResult = future.get();
        System.out.println("asyncResult = " + asyncResult);
    }
}
