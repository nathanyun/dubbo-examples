## 项目介绍 
dubbo-service-downgrade 是 Dubbo的服务降级,是指服务在非正常情况下进行降级应急处理。

## 使用场景
* 某服务或接口负荷超出最大承载能力范围，避免系统崩溃
* 调用的某非关键服务或接口暂时不可用时，返回模拟数据或空，业务还能继续可用
* 降级非核心业务的服务或接口，腾出系统资源，尽量保证核心业务的正常运行
* 某上游基础服务超时或不可用时，执行能快速响应的降级预案，避免服务整体雪崩

## 配置说明
- `mock="[fail  force]return|throw xxxException"`
- `fail` 或 `force` 关键字可选，表示调用失败或不调用强制执行 mock 方法，如果不指定关键字默认为 `fail`
- `return` 表示指定返回结果，`throw` 表示抛出指定异常

## 三 测试部署
### 3.1 测试 `fail:return null`
1. 启动 `ServiceDowngradeClient`
2. `dubbo-consumer.xml`配置如下:
    ```xml
    <dubbo:reference check="false" interface="com.lb.dubbo.service.GreetingService" mock="fail:return null"/>
    ```
3. 观察控制台即可, (别启动服务提供者!!!因为我们测试调用失败的情况)
    ```text
    18:29:40.272 [main] WARN org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker -  [DUBBO] fail-mock: sayHi fail-mock enabled , url : consumer://172.19.166.180/com.lb.dubbo.service.GreetingService?application=springXML-consumer&background=false&check=false&dubbo=2.0.2&executor-management-mode=isolation&file-cache=true&interface=com.lb.dubbo.service.GreetingService&methods=sayHi&mock=fail%3Areturn+null&pid=45976&register.ip=172.19.166.180&release=3.2.2&side=consumer&sticky=false&timeout=2000&timestamp=1686824977442&unloadClusterRelated=false, dubbo version: 3.2.2, current host: 172.19.166.180, error code: 2-17. This may be caused by failed to mock invoke, go to https://dubbo.apache.org/faq/2/17 to find instructions. 
    org.apache.dubbo.rpc.RpcException: No provider available from registry 127.0.0.1:2181 for service com.lb.dubbo.service.GreetingService on consumer 172.19.166.180 use dubbo version 3.2.2, please check status of providers(disabled, not registered or in blacklist).
        at org.apache.dubbo.registry.integration.DynamicDirectory.doList(DynamicDirectory.java:199)
        at org.apache.dubbo.rpc.cluster.directory.AbstractDirectory.list(AbstractDirectory.java:221)
        at org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker.list(AbstractClusterInvoker.java:411)
        at org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker.invoke(AbstractClusterInvoker.java:333)
        at org.apache.dubbo.rpc.cluster.router.RouterSnapshotFilter.invoke(RouterSnapshotFilter.java:46)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java:108)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.rpc.cluster.filter.support.MetricsClusterFilter.invoke(MetricsClusterFilter.java:50)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.rpc.protocol.dubbo.filter.FutureFilter.invoke(FutureFilter.java:52)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.spring.security.filter.ContextHolderParametersSelectedTransferFilter.invoke(ContextHolderParametersSelectedTransferFilter.java:41)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.rpc.cluster.filter.support.ConsumerClassLoaderFilter.invoke(ConsumerClassLoaderFilter.java:40)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.rpc.cluster.filter.support.ConsumerContextFilter.invoke(ConsumerContextFilter.java:118)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CopyOfFilterChainNode.invoke(FilterChainBuilder.java:331)
        at org.apache.dubbo.rpc.cluster.filter.FilterChainBuilder$CallbackRegistrationInvoker.invoke(FilterChainBuilder.java:194)
        at org.apache.dubbo.rpc.cluster.support.wrapper.AbstractCluster$ClusterFilterInvoker.invoke(AbstractCluster.java:91)
        at org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.invoke(MockClusterInvoker.java:113)
        at org.apache.dubbo.rpc.cluster.support.wrapper.ScopeClusterInvoker.invoke(ScopeClusterInvoker.java:155)
        at org.apache.dubbo.registry.client.migration.MigrationInvoker.invoke(MigrationInvoker.java:284)
        at org.apache.dubbo.rpc.proxy.InvocationUtil.invoke(InvocationUtil.java:57)
        at org.apache.dubbo.rpc.proxy.InvokerInvocationHandler.invoke(InvokerInvocationHandler.java:75)
        at com.lb.dubbo.service.GreetingServiceDubboProxy0.sayHi(GreetingServiceDubboProxy0.java)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:208)
        at com.sun.proxy.$Proxy20.sayHi(Unknown Source)
        at com.lb.dubbo.ServiceDowngradeClient.main(ServiceDowngradeClient.java:23)
    18:29:40.273 [main] INFO org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker -  [DUBBO] Exception when try to invoke mock. Get mock invokers error for service:com.lb.dubbo.service.GreetingService, method:sayHi, will construct a new mock with 'new MockInvoker()'., dubbo version: 3.2.2, current host: 172.19.166.180
    org.apache.dubbo.rpc.RpcException: No provider available from registry 127.0.0.1:2181 for service com.lb.dubbo.service.GreetingService on consumer 172.19.166.180 use dubbo version 3.2.2, please check status of providers(disabled, not registered or in blacklist).
        at org.apache.dubbo.registry.integration.DynamicDirectory.doList(DynamicDirectory.java:199)
        at org.apache.dubbo.rpc.cluster.directory.AbstractDirectory.list(AbstractDirectory.java:221)
        at org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.selectMockInvoker(MockClusterInvoker.java:197)
        at org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.doMockInvoke(MockClusterInvoker.java:147)
        at org.apache.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.invoke(MockClusterInvoker.java:133)
        at org.apache.dubbo.rpc.cluster.support.wrapper.ScopeClusterInvoker.invoke(ScopeClusterInvoker.java:155)
        at org.apache.dubbo.registry.client.migration.MigrationInvoker.invoke(MigrationInvoker.java:284)
        at org.apache.dubbo.rpc.proxy.InvocationUtil.invoke(InvocationUtil.java:57)
        at org.apache.dubbo.rpc.proxy.InvokerInvocationHandler.invoke(InvokerInvocationHandler.java:75)
        at com.lb.dubbo.service.GreetingServiceDubboProxy0.sayHi(GreetingServiceDubboProxy0.java)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:208)
        at com.sun.proxy.$Proxy20.sayHi(Unknown Source)
        at com.lb.dubbo.ServiceDowngradeClient.main(ServiceDowngradeClient.java:23)
        
    ## 打印的响应结果, 调用失败返回空值
    Receive message ===> null
    ```
   
### 3.2 测试指定mock代理类方式 
场景:调用远程服务超时, 触发mock

1. 消费端编写并配置mock代理类
   ```xml 
   <dubbo:reference id="greetingService"
                    interface="com.lb.dubbo.service.GreetingService"
                    mock="com.lb.dubbo.service.GreetingServiceMock" />
   ```
   (一般mock实现建议在消费端编写)
2. 运行测试用例
   ```java
       @Autowired
       @Qualifier("greetingService")
       private GreetingService greetingService;
       @Test
       public void testMock() {
           String response = greetingService.sayHi("world");
           System.out.println("response = " + response);
       }
   ```
3. 观察控制台
   ```text
   response = Hi, world ! Power by Consumer Service Downgrade Mock
   ```
   

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/service-downgrade/