package com.lb.dubbo.reactor;


import com.lb.dubbo.reactor.impl.GreeterServiceImpl;
import com.lb.dubbo.util.EmbeddedZooKeeper;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MetadataReportConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * 响应式编程服务端
 */
public class ReactorServer {

    private static final int PORT = 50052;

    public static void main(String[] args) {
        //注意: 启动服务端前, 先启动本地zookeeper注册中心
        new EmbeddedZooKeeper(2181, false).start();
        //服务提供者
        ServiceConfig<GreeterService> service = new ServiceConfig<>();
        service.setInterface(GreeterService.class);
        service.setRef(new GreeterServiceImpl());

        //启动Dubbo服务
        DubboBootstrap.getInstance().application(new ApplicationConfig("reactorServer"))
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .protocol(new ProtocolConfig(CommonConstants.TRIPLE, PORT))
                .service(service)
                .start();
    }
}
