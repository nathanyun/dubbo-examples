## 项目介绍
dubbo-triple-reactor 是 Dubbo的一个高级特性:响应式编程,整体项目结构如下:

```text
### 此模块还未验证通过!!!!!
```
## 版本要求
* dubbo.version=[ >=3.1.0 ]
* compiler.version=[ >=3.1.0 ]
* java.version= [<= 11]


## 特性说明
1. 此特性基于 Triple 协议和 Project Reactor 实现，3.1.0 版本以上支持。用户仅需编写 IDL 文件，并指定 protobuf 插件的相应 Generator，即可生成并使用支持响应式API的 Stub 代码。
2. 有四种调用模式，分别是 OneToOne、OneToMany、ManyToOne、ManyToMany，分别对应 Unary调用、服务端流、客户端流、双向流。在 Reactor 的实现中，One 对应 Mono，Many 对应 Flux。

> * IDL是Interface description language的缩写，指接口描述语言，是CORBA规范的一部分，是跨平台开发的基础。
> * IDL通常用于远程调用软件。 在这种情况下，一般是由远程客户终端调用不同操作系统上的对象组件，并且这些对象组件可能是由不同计算机语言编写的。IDL建立起了两个不同操作系统间通信的桥梁。

## 使用场景
1. 系统需要处理大量并发请求而不会使任何服务器过载。大量用户提供实时数据的系统，希望确保系统能够处理负载而不会崩溃或变慢。
2. 可实现上层包括大文件传输和推送机制的业务需求。


## 官方手册
https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/advanced-features-and-usage/service/reactive/