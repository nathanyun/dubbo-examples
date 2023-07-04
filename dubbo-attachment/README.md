## 特性说明
Dubbo调用链路传递隐式参数,利用`RpcContext`的`setAttachment`和`getAttachement`实现调用方和提供方的参数的隐式传递.

## 应用场景
分布式链路追踪,在全链路的上下文维护一个traceId, Consumer 和 Provider 通过传递 traceId 来连接一次RPC调用，分别上报日志后可以在追踪系统中串联并展示完整的调用流程。这样可以更方便地发现异常，定位问题。

## 核心代码
> - 隐式参数传递案例共分为4个服务,分别是消费端服务, 订单服务, 支付服务, 物流服务
> - 调用链路为: StandardConsumer --> OrderService --> PaymentService --> DeliverService
1. 消费端核心代码
```java
//设置隐式参数
RpcContextAttachment attachment = RpcContext.getClientAttachment();
attachment.setAttachment("traceId", UUID.randomUUID().toString());
System.err.println("客户端发起调用...");
//创建订单
String result = orderService.create("HUAWEI P60 ART");
System.err.println("客户端收到响应... result="+result);
```
2. 服务端核心代码
```java
//获取隐式参数
RpcContextAttachment attachment = RpcContext.getServerAttachment();
String traceId = attachment.getAttachment("traceId");
```
## 案例测试流程
- 先启动 `OrderServer`, 再分别启动 `PaymentServer`, `DeliverServer`
- 再执行 `StandardConsumer`

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/attachment/