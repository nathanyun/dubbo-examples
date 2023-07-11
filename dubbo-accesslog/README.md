# dubbo调用日志 
在 dubbo3 日志分为日志适配和访问日志，如果想记录每一次请求信息，可开启访问日志，类似于 apache 的访问日志。
> - 此日志量比较大，请注意磁盘容量。
## 使用场景
类似 nginx accesslog 输出等。

## 使用方式
将访问日志输出到当前应用的 log4j 日志
```xml
<dubbo:protocol accesslog="true" />
```

将访问日志输出到指定文件
```xml
<dubbo:protocol accesslog="http://10.20.160.198/wiki/display/dubbo/foo/bar.log" />
```

## 引入sl4j
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.7</version>
</dependency>
<!--sl4j实现, 绑定System.err日志,仅打印INFO级别的日志-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>2.0.7</version>
    <scope>compile</scope>
</dependency>
```

## 日志形式
```text
[Dubbo-framework-shared-scheduler-thread-1] INFO dubbo.accesslog.com.lb.dubbo.service.GreetingService -  [DUBBO] [2023-07-11 09:09:43.99100] -> [2023-07-11 09:09:44.99500] 172.19.166.121:60804 -> 172.19.166.121:20880 - com.lb.dubbo.service.GreetingService sayHi(java.lang.String) ["testing"], dubbo version: 3.2.2, current host: 172.19.166.121
[Dubbo-framework-shared-scheduler-thread-1] INFO dubbo.accesslog.com.lb.dubbo.service.GreetingService -  [DUBBO] [2023-07-11 09:09:47.00700] -> [2023-07-11 09:09:48.01100] 172.19.166.121:60804 -> 172.19.166.121:20880 - com.lb.dubbo.service.GreetingService sayHi(java.lang.String) ["testing"], dubbo version: 3.2.2, current host: 172.19.166.121
```

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/accesslog/