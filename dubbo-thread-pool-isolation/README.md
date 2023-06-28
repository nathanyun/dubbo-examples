## 特性说明
Dubbo线程池隔离的特性, 可以使提供者应用内各个接口服务的线程池隔离开来，互相独立，某个接口服务的线程池资源耗尽不会影响其他接口服务。支持线程池可配置化，由用户手动指定。

## 使用方式
dubboAPI, XML, annotation

### 配置参数
1. ApplicationConfig 新增 String executor-management-mode 参数，配置值为 default 和 isolation ，默认为 default。
   * executor-management-mode = default 使用原有 以协议端口为粒度、服务间共享 的线程池管理方式
   * executor-management-mode = isolation 使用新增的 以服务三元组为粒度、服务间隔离 的线程池管理方式
2. ServiceConfig 新增 Executor executor 参数，用以服务间隔离的线程池，可以由用户配置化、提供自己想要的线程池，若没有指定，则会根据协议配置(ProtocolConfig)信息构建默认的线程池用以服务隔离。
> ServiceConfig 新增 Executor executor 配置参数只有指定executor-management-mode = isolation 才生效。

详细案例,请参考实际代码.

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/isolation-executor/