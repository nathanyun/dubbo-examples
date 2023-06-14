package com.lb.dubbo.reactor;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * 客户端程序
 */
public class ReactorClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactorClient.class);

    private final GreeterService greeterService;

    public ReactorClient() {
        ReferenceConfig<GreeterService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(GreeterService.class);
        referenceConfig.setProtocol(CommonConstants.TRIPLE);
        referenceConfig.setProxy(CommonConstants.NATIVE_STUB);
        referenceConfig.setTimeout(10000);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(new ApplicationConfig("reactor-client"))
                .registry(new RegistryConfig("zookeeper://127.0.0.1:2181"))
                .reference(referenceConfig)
                .start();
        greeterService = referenceConfig.get();
    }

    public static void main(String[] args) throws IOException {
        ReactorClient reactorConsumer = new ReactorClient();
        reactorConsumer.consumeManyToMany();
        //挂起进程
        System.in.read();
    }

    private void consumeOneToOne() {
        greeterService.greetOneToOne(Mono.just(GreeterRequest.newBuilder().setName("request-1").build())
                        .doOnNext(req -> LOGGER.info("consumeOneToOne request data: {}", req)))
                .subscribe(res -> LOGGER.info("consumeOneToOne get response: {}", res));
    }

    private void consumeOneToMany() {
        greeterService.greetOneToMany(Mono.just(GreeterRequest.newBuilder().setName("1,2,3,4,5,6,7,8,9,10").build())
                        .doOnNext(req -> LOGGER.info("consumeOneToMany request data: {}", req)))
                .subscribe(res -> LOGGER.info("consumeOneToMany get response: {}", res));
    }

    private void consumeManyToOne() {
        greeterService.greetManyToOne(Flux.range(1, 10)
                        .map(num -> GreeterRequest.newBuilder().setName(String.valueOf(num)).build())
                        .doOnNext(req -> LOGGER.info("consumeManyToOne request data: {}", req)))
                .subscribe(res -> LOGGER.info("consumeManyToOne get response: {}", res));
    }

    private void consumeManyToMany() {
        greeterService.greetManyToMany(Flux.range(1, 10)
                        .map(num ->
                                GreeterRequest.newBuilder().setName(String.valueOf(num)).build())
                        .doOnNext(req -> LOGGER.info("consumeManyToMany request data: {}", req)))
                .subscribe(res -> LOGGER.info("consumeManyToMany get response: {}", res));
    }
}
