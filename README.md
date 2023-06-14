## 快速开始
本项目包含多个子项目, 是基于Dubbo官方文档顺序提供的对应参考程序，建议认真看每个模块中的 README 文档说明.

> tips: 每个README都有链接到Dubbo官方的说明文档地址

## 版本说明
 :tw-2705: JDK 1.8及以上
 :tw-2705: Apache dubbo 3.2.2
 :tw-2705: ZooKeeper 3.6.2
 :tw-2705: SpringBoot 2.7.8

## 项目介绍
整体项目结构:
```text
dubbo-examples //根
├── dubbo-merge //分组聚合
├── dubbo-quickstart //快速入门
│   ├── dubbo-annotation //注解
│   ├── dubbo-api //DUBBO API开发
│   ├── dubbo-spring-boot //springboot开发
│   ├── dubbo-spring-xml //spring XML开发
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
