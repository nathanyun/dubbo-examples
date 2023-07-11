## 项目介绍 
dubbo-generic 是 Dubbo泛化调用的特性, 是指在调用方没有服务方提供的 API（SDK）的情况下，对服务方进行调用，并且可以正常拿到调用结果.

## 使用场景
泛化调用主要用于实现一个通用的远程服务 Mock 框架，可通过实现 GenericService 接口处理所有服务请求。比如如下场景：
* 网关服务：如果要搭建一个网关服务，那么服务网关要作为所有 RPC 服务的调用端。但是网关本身不应该依赖于服务提供方的接口 API（这样会导致每有一个新的服务发布，就需要修改网关的代码以及重新部署），所以需要泛化调用的支持。
* 测试平台：如果要搭建一个可以测试 RPC 调用的平台，用户输入分组名、接口、方法名等信息，就可以测试对应的 RPC 服务。平台本身不应该依赖于服务提供方的接口 API。


## 服务端泛化调用
```java
public class MyGenericService implements GenericService {
 
    public Object $invoke(String methodName, String[] parameterTypes, Object[] args) throws GenericException {
        if ("sayHello".equals(methodName)) {
            return "Welcome " + args[0];
        }
    }
}
```

## 客户端泛化调用
```java
//引入泛化调用service接口
import org.apache.dubbo.rpc.service.GenericService;
//省略其他包导入

class A{
    /**
     * 以下代码是使用DUBBO API方式实现泛化调用, 若要参考spring方式见 {@code com.lb.dubbo.GenericConsumer.main}类中
     * @param args
     */
    public static void main(String[] args) {
        //应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig("generic-call-consumer");
        applicationConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //接口配置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("com.lb.dubbo.service.GreetingService");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGeneric("true");
        referenceConfig.setAsync(true);
        referenceConfig.setTimeout(4000);

        //获取远程调用接口
        genericService = referenceConfig.get();

        //泛化调用(异步接口案例)
        //这里使用$invokeAsync来实现异步调用, 若要同步调用可调用$invoke方法
        // 第一个参数是方法名
        // 第二个是请求参数类型数组
        // 第三个是参数数组
        CompletableFuture<Object> future = genericService.$invokeAsync("sayHi", new String[]{"java.lang.String"}, new Object[]{"world"});
        // 增加回调
        future.whenComplete(new BiConsumer<Object, Throwable>() {
            @Override
            public void accept(Object result, Throwable throwable) {
                if (throwable == null) {
                    System.out.println(LocalDateTime.now() + " - Response: " + result);
                } else {
                    throwable.printStackTrace();
                }
            }
        });
        // 早于结果输出
        System.out.println(LocalDateTime.now() + " - Executed before response return.");
        //阻塞等待5秒,以便获取响应结果(因为接口实现是异步实现,因此需要阻塞等待结果)
        LockSupport.parkNanos(5000000000L);
    }
}
```
控制台输出:
```text
2023-06-28T10:22:26.293 - Executed before response return.
2023-06-28T10:22:29.317 - Response: Hi, world ! Power by Async CompletableFuture
```

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/generic-reference/
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/generic-service/