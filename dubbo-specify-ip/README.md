## 项目介绍 
使用 Dubbo 的扩展，实现指定 IP 调用。

## 使用场景
发起请求的时候需要指定本次调用的服务端，如消息回调、流量隔离等。

## 使用方式
dubbo3版本依赖
```xml
<dependency>
  <groupId>org.apache.dubbo.extensions</groupId>
  <artifactId>dubbo-cluster-specify-address-dubbo3</artifactId>
  <version>1.0.0</version>
</dependency>
```
dubbo2版本依赖
```xml
<dependency>
  <groupId>org.apache.dubbo.extensions</groupId>
  <artifactId>dubbo-cluster-specify-address-dubbo2</artifactId>
  <version>1.0.0</version>
</dependency>
```
核心代码
```java
public class Consumer {
    public static void main(String[] args) {
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setApplication(new ApplicationConfig("consumer"));
        //获取远程接口
        GreetingService greetingService = referenceConfig.get();

        //指定IP+端口调用
        UserSpecifiedAddressUtil.setAddress(new Address("127.0.0.1", 20881, true));
        greetingService.sayHi("jack");

        //指定IP+端口调用
        UserSpecifiedAddressUtil.setAddress(new Address("127.0.0.1", 20882, true));
        greetingService.sayHi("tony");

    }
}
```

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/specify-ip/