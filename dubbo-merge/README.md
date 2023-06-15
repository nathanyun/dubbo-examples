## 项目介绍
dubbo-merge是 Dubbo的一个高级特性:分组聚合,整体项目结构如下:
```text
.
├── README.md
├── dubbo-merge-consumer //基于springboot的Dubbo消费者应用
├── dubbo-merge-consumer2//基于springXML的Dubbo消费者应用
├── dubbo-merge-interface//接口定义
├── dubbo-merge-provider1//基于springboot的Dubbo提供者
├── dubbo-merge-provider2//基于springXML的Dubbo提供者
└── pom.xml
```

## 特性说明
通过分组对结果进行聚合并返回聚合后的结果，比如菜单服务，用 group 区分同一接口的多种实现，现在消费方需从每种 group 中调用一次并返回结果，对结果进行合并之后返回，这样就可以实现聚合菜单项。

## 使用场景
将多个服务提供者分组作为一个提供者进行访问。应用程序能够像访问一个服务一样访问多个服务，并允许更有效地使用资源。


## 官方手册
https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/advanced-features-and-usage/service/group-merger/