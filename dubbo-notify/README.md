# 调用触发事件通知
> 说明: 官方案例仅有注解方式, 这里补充了XML, API方式的实现
## 特性说明
在调用之前、调用之后、出现异常时，会触发 oninvoke、onreturn、onthrow 三个事件，可以配置当事件发生时，通知哪个类的哪个方法。

## 应用场景
调用服务方法前我们可以记录开始时间，调用结束后统计整个调用耗费，发生异常时我们可以告警或打印错误日志或者调用服务前后记录请求日志、响应日志等。

## 使用方式
- 1.定义触发通知接口
  ```java
  public interface NotifyService {
      //调用之前执行，如果被调用的服务有参数，那么oninvoke也必要有和被调用服务一样的参数
      void onInvoke(String name);
      //调用之后, 至少有一个入参，第一个入参是返回值，其余是调用服务的参数
      void onReturn(String result, String name);
      //出现异常时,至少一个参数，类型为被抛出服务异常的父类或其本身，其余是调用服务的参数
      void onThrow(Throwable ex, String name);
  }
  
  //接口实现
  @Service("notifyServiceImpl")
  public class NotifyServiceImpl implements NotifyService{}
  ```
  
- 2.消费端远程调用
  - 2.1 注解方式
  ```java
  @DubboReference(timeout = 6000, methods = @Method(name = "sayHi", oninvoke = "notifyServiceImpl.onInvoke", onreturn = "notifyServiceImpl.onReturn", onthrow = "notifyServiceImpl.onThrow"))
  private GreetingService greetingService;
  ```
  - 2.2 springXML 配置
  ```xml
  <!--触发通知bean定义-->
  <bean id ="notifyServiceImpl" class = "com.lb.dubbo.service.impl.NotifyServiceImpl" />
  <!--dubbo远程接口配置-->
  <dubbo:reference id="greetingService" interface="com.lb.dubbo.service.GreetingService">
      <!--指定sayHi方法的回调地址-->
      <dubbo:method name="sayHi" async="true" oninvoke="notifyServiceImpl.onInvoke" onreturn = "notifyServiceImpl.onReturn" onthrow="notifyServiceImpl.onThrow" />
  </dubbo:reference>
  ```
  - 2.3 api方式
  ```java
  //通知服务
  NotifyService notifyService = new NotifyServiceImpl();
  //方法配置
  ArrayList<MethodConfig> methodConfigs = new ArrayList<>();
  MethodConfig methodConfig = new MethodConfig();
  methodConfig.setName("sayHi");
  methodConfig.setTimeout(6000);
  //指定通知方法
  methodConfig.setOninvoke(notifyService);
  methodConfig.setOnreturn(notifyService);
  methodConfig.setOnthrow(notifyService);
  methodConfig.setOninvokeMethod("onInvoke");
  methodConfig.setOnreturnMethod("onReturn");
  methodConfig.setOnthrowMethod("onThrow");
  methodConfigs.add(methodConfig);
  reference.setMethods(methodConfigs);
  ```

## 测试流程
1.测试onInvoke,onReturn
  - 先启动 `NotifyProviderApp`, 再启动 `NotifyConsumerApp`
  - 访问URL `http://localhost:8802/test/sayHi?name=jack`
  - 观察日志
      ```text
      2023-07-04 18:06:52.611  准备请求远程调用...
      2023-07-04 18:06:52.613  onInvoke name = jack
      2023-07-04 18:06:52.628  onReturn result = Hi, jack, name = jack
      2023-07-04 18:06:52.629  sayHi result = Hi, jack
      ```
2.测试onThrow
  - 访问URL `http://localhost:8802/test/sayHi?name=jack2`
  - 观察日志
  ```text
    2023-07-04 18:34:07.544  准备请求远程调用...
    2023-07-04 18:34:07.544  onInvoke name = Jack2
    2023-07-04 18:34:07.575  onThrow ex = java.lang.IllegalArgumentException: mock error, name = Jack2
    2023-07-04 18:34:07.588 ERROR 32323 --- [nio-8802-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalArgumentException: mock error] with root cause
    
    java.lang.IllegalArgumentException: mock error
    at com.lb.dubbo.service.impl.GreetingServiceImpl.sayHi(GreetingServiceImpl.java:15) ~[na:na]
    at com.lb.dubbo.service.impl.GreetingServiceImplDubboWrap0.invokeMethod(GreetingServiceImplDubboWrap0.java) ~[na:na]
  ```

## 官方手册
https://cn.dubbo.apache.org/en/docs3-v2/java-sdk/advanced-features-and-usage/service/events-notify/