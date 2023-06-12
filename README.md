# 基于springboot集成dubbo
本项目是基于zk作为注册中心集成Dubbo的一个springboot项目，有需要的同学可以clone参考。
## 环境要求
 :tw-2705: JDK 1.8及以上

 :tw-2705: Apache dubbo 3.2.2

 :tw-2705: ZooKeeper 3.6.2

 :tw-2705: SpringBoot 2.7.8

## 项目介绍
项目是通过使用Apache官方提供的 `dubbo-spring-boot-starter` 中提供的注解：`@DubboService`、`@DubboReference` 整合快速入门案例.
整体项目结构如下:
```text
.
├── README.md
├── pom.xml
├── springboot-dubbo-zk-consumer
├── springboot-dubbo-zk-interface
└── springboot-dubbo-zk-provider
```
* springboot-dubbo-zk-interface //公共接口定义
* springboot-dubbo-zk-provider //提供者服务
* springboot-dubbo-zk-consumer //消费者服务

## 快速部署
1.启动注册中心zk
`./zkServer.sh start`

> 提示: 注意zk地址和端口, 要与项目中注册中心地址端口保持一致

2.获取工程代码
`git clone https://gitee.com/yunnasheng/springboot-dubbo-zk.git`

3.通过Maven编译项目
`mvn clean install`

4.启动provider `java -jar springboot-dubbo-zk-provider-1.0.0.jar --server-port=8601`
```text
2023-06-12 13:32:07.408  INFO 6600 --- [           main] o.a.d.c.deploy.DefaultModuleDeployer     :  [DUBBO] Dubbo Module[1.1.1] is starting., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:32:07.408  INFO 6600 --- [           main] o.a.d.c.d.DefaultApplicationDeployer     :  [DUBBO] Dubbo Application[1.1](springboot-dubbo-zk-provider) is starting., dubbo version: 3.2.2, current host: 172.19.166.139
```

5.启动consumer观察日志
```text
2023-06-12 13:12:26.730  INFO 5107 --- [           main] o.a.d.r.c.m.ServiceInstanceMetadataUtils :  [DUBBO] Start registering instance address to registry., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:12:26.734  INFO 5107 --- [           main] o.a.d.c.d.DefaultApplicationDeployer     :  [DUBBO] Dubbo Application[1.1](springboot-dubbo-zk-consumer) is ready., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:12:26.738  INFO 5107 --- [           main] com.lb.springboot.ConsumerApplication    : Started ConsumerApplication in 3.106 seconds (JVM running for 3.581)
Receive result ======> Hi, world
Mon Jun 12 13:12:27 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:28 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:29 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:30 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:31 CST 2023 Receive result ======> Hi, world
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
3. 为什么要用使用 Java Config 代替注解?
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
