package com.lb.dubbo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GreeterService {


    public abstract Mono<GreeterReply> greetOneToOne(Mono<GreeterRequest> request);

    public abstract Flux<GreeterReply> greetOneToMany(Mono<GreeterRequest> request);

    public abstract Mono<GreeterReply> greetManyToOne(Flux<GreeterRequest> request);

    public abstract Flux<GreeterReply> greetManyToMany(Flux<GreeterRequest> request);
}
