## 项目介绍
基于zk作为注册中心集成Dubbo的一个springboot项目

项目是通过使用Apache官方提供的 `dubbo-spring-boot-starter` 中提供的注解：`@DubboService`、`@DubboReference` 整合快速入门案例.
整体项目结构如下:
```text
.
├── dubbo-spring-boot-consumer //springboot消费者服务
├── dubbo-spring-boot-interface//公共接口定义
├── dubbo-spring-boot-provider//springboot提供者服务
└── pom.xml
```

## 快速部署
1.启动注册中心zk
`./zkServer.sh start`

> 提示: 注意zk地址和端口, 要与项目中注册中心地址端口保持一致

2.获取工程代码
`git clone https://gitee.com/yunnasheng/dubbo-examples.git`

3.通过Maven编译项目
`cd /dubbo-examples/dubbo-quickstart/dubbo-spring-boot`
`mvn clean install`

4.启动provider
```text
2023-06-12 13:32:07.408  INFO 6600 --- [           main] o.a.d.c.deploy.DefaultModuleDeployer     :  [DUBBO] Dubbo Module[1.1.1] is starting., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:32:07.408  INFO 6600 --- [           main] o.a.d.c.d.DefaultApplicationDeployer     :  [DUBBO] Dubbo Application[1.1](dubbo-zk-springboot-provider) is starting., dubbo version: 3.2.2, current host: 172.19.166.139
```

5.启动consumer观察日志
```text
2023-06-12 13:12:26.730  INFO 5107 --- [           main] o.a.d.r.c.m.ServiceInstanceMetadataUtils :  [DUBBO] Start registering instance address to registry., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:12:26.734  INFO 5107 --- [           main] o.a.d.c.d.DefaultApplicationDeployer     :  [DUBBO] Dubbo Application[1.1](dubbo-zk-springboot-consumer) is ready., dubbo version: 3.2.2, current host: 172.19.166.139
2023-06-12 13:12:26.738  INFO 5107 --- [           main] com.lb.springboot.ConsumerApplication    : Started ConsumerApplication in 3.106 seconds (JVM running for 3.581)
Receive result ======> Hi, world
Mon Jun 12 13:12:27 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:28 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:29 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:30 CST 2023 Receive result ======> Hi, world
Mon Jun 12 13:12:31 CST 2023 Receive result ======> Hi, world
```

