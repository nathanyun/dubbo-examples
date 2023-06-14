package com.lb.dubbo.reactor.impl;

import com.lb.dubbo.reactor.DubboGreeterServiceTriple;
import com.lb.dubbo.reactor.GreeterReply;
import com.lb.dubbo.reactor.GreeterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 定义问候服务具体实现
 */
public class GreeterServiceImpl extends DubboGreeterServiceTriple.GreeterServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GreeterServiceImpl.class);

    @Override
    public Mono<GreeterReply> greetOneToOne(Mono<GreeterRequest> request) {
        return request.doOnNext(new Consumer<GreeterRequest>() {
            @Override
            public void accept(GreeterRequest req) {
                LOGGER.info("greetOneToOne get data: {}", req);
            }
        }).map(new Function<GreeterRequest, GreeterReply>() {
            @Override
            public GreeterReply apply(GreeterRequest req) {
                return GreeterReply.newBuilder().setMessage(req.getName() + "-> server get").build();
            }
        }).doOnNext(new Consumer<GreeterReply>() {
            @Override
            public void accept(GreeterReply res) {
                LOGGER.info("greetOneToOne response data: {}", res);
            }
        });
    }

    @Override
    public Flux<GreeterReply> greetOneToMany(Mono<GreeterRequest> request) {
        return request.doOnNext(req-> LOGGER.info("greetOneToMany get data: {}", req))
                .flatMapMany( req ->
                    Flux.<String> create(emitter ->{
                        Arrays.stream(req.getName().split(",")).forEach(emitter::next);
                        emitter.complete();
                    })
                ).map(n -> GreeterReply.newBuilder().setMessage(n).build())
                .doOnNext(res -> LOGGER.info("greetOneToMany resp data: {}", res));
    }

    @Override
    public Mono<GreeterReply> greetManyToOne(Flux<GreeterRequest> request) {
        return request.doOnNext(req -> LOGGER.info("greetManyToOne get data: {}", req))
                .map(req -> Integer.valueOf(req.getName()))
                .reduce(Integer::sum)
                .map(sum -> GreeterReply.newBuilder().setMessage(String.valueOf(sum)).build())
                .doOnNext(res -> LOGGER.info("greetManyToOne response data: {}", res));
    }

    @Override
    public Flux<GreeterReply> greetManyToMany(Flux<GreeterRequest> request) {
        return request.doOnNext(req -> LOGGER.info("greetManyToMany get data: {}", req))
                .map(req -> GreeterReply.newBuilder().setMessage(req.getName() + " -> server get").build())
                .doOnNext(res -> LOGGER.info("greetManyToMany response data: {}", res));
    }
}
