package com.lb.dubbo.service;

/**
 * 本地存根
 * <pre>Stub 必须有可传入 Proxy 的构造函数</pre>
 * <pre>在 interface 旁边放一个 Stub 实现，它实现 BarService 接口，并有一个传入远程 BarService 实例的构造函数。</pre>
 *
 */
public class GreetingServiceStub implements GreetingService{

    private final GreetingService greetingService;

    // 构造函数传入真正的远程代理对象
    public GreetingServiceStub(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public String sayHi(String name) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        System.out.printf("About to execute stub: [%s] \n", GreetingServiceStub.class.getSimpleName());
        try {
            //远程接口调用
            return greetingService.sayHi(name);
        }catch (Exception e){
            // 你可以容错，可以做任何AOP拦截事项
            return "容错数据";
        }
    }
}
