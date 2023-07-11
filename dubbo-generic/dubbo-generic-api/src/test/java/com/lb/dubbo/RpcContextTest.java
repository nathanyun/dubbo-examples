package com.lb.dubbo;

import org.apache.dubbo.rpc.RpcContext;

public class RpcContextTest {

    public static void main(String[] args) {
        //本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getServiceContext().isConsumerSide();
        //本端是否为提供端，这里会返回true
        boolean isProviderSide = RpcContext.getServiceContext().isProviderSide();

        //调用方host地址
        String remoteHost = RpcContext.getServiceContext().getRemoteHost();
        String application = RpcContext.getServiceContext().getUrl().getParameter("application");
        // 注意：每发起RPC调用，上下文状态会变化
        // demoService.sayHi("Jack");
        // 此时本端变成消费端，这里会返回false
        //boolean isProviderSide = RpcContext.getServiceContext().isProviderSide();
    }
}
