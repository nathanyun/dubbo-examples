## 快速开始
本项目包含多个子项目, 是基于Dubbo官方文档顺序提供的对应参考程序，建议认真看每个模块中的 README 文档说明.

> Tips: 每个README详细介绍了Dubbo特性说明, 使用场景, 以及相关部署测试的说明, 另外还附有Dubbo官方手册地址

## 版本说明
-  JDK 1.8及以上
-  Apache dubbo 3.2.2
-  ZooKeeper 3.6.2
-  SpringBoot 2.7.8

## 项目介绍
整体项目结构:
```text
.
├── README.md
├── dubbo-async //异步调用
├── dubbo-cluster-fault-tolerance//集群容错
├── dubbo-merge//分组聚合
├── dubbo-quickstart//快速入门(DubboAPI,注解,springboot,springXML)
├── dubbo-service-downgrade//服务降级
├── dubbo-thread-pool-isolation//线程池隔离
├── dubbo-triple-reactor//响应式编程
├── dubbo-validation//参数校验
├── dubbo-version//服务版本
└── pom.xml

```

## FAQ
1. 为什么把Dubbo端口配置成-1?
```text
设置端口为 -1 表示 dubbo 自动扫描并使用可用端口（从20880开始递增），避免了端口冲突的问题。
 (扩展:dubbo 默认端口是20880，网络中端口范围：1-65535)
```
2. 为什么使用@DubboService和@DubboReference注解?
```text
@Service 注解从 3.0 版本开始就已经废弃，改用 @DubboService，以区别于 Spring 的 @Service 注解,
@Reference 注解从 3.0 版本开始就已经废弃，改用 @DubboReference，以区别于 Spring 的 @Reference 注解
```
3. 在API模式下, 为什么要用使用 Java Config 代替注解?
```java
//Java Config 是 DubboService 或 DubboReference 的替代方式，对于有复杂配置需求的服务建议使用这种方式。
//但是要注意!!! ServiceConfig为重对象，内部封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
@Configuration
public class ProviderConfiguration {
    @Bean
    public ServiceConfig demoService() {
        ServiceConfig service = new ServiceConfig();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.setGroup("dev");
        service.setVersion("1.0.0");
        Map<String, String> parameters = new HashMap<>();
        service.setParameters(parameters);
        // 暴露及注册服务
        service.export();
        return service;
    }
}
```


> 要学习关于dubbo更多的内容，推荐阅读Dubbo官方文档 : http://dubbo.apache.org/ 
