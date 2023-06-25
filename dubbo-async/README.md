## 项目介绍 
dubbo-async 是 Dubbo异步调用的特性, 整体项目结构如下:
```text
.
├── README.md
├── dubbo-async-provider
├── dubbo-async-simple//提供CompletableFuture和RpcContext方式的异步调用案例
└── pom.xml
```

## 使用场景
将用户请求内容发送到目标请求，当目标请求遇到高流量或需要长时间处理，异步调用功能将允许立即向用户返回响应，同时目标请求继续后台处理请求，当目标请求返回结果时，将内容显示给用户。

> 需要服务提供者事先定义 CompletableFuture 签名的服务，接口定义指南如下：
> - Provider端异步执行将阻塞的业务从Dubbo内部线程池切换到业务自定义线程，避免Dubbo线程池的过度占用，有助于避免不同服务间的互相影响。
> - 异步执行无异于节省资源或提升RPC响应性能，因为如果业务执行需要阻塞，则始终还是要有线程来负责执行。


## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/async-call/