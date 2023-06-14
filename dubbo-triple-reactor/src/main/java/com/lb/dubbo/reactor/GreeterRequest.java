package com.lb.dubbo.reactor;


import java.io.Serializable;

/**
 * 请求参数定义
 */
public class GreeterRequest implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public GreeterRequest(String name) {
        this.name = name;
    }

    public static GreeterRequestBuilder newBuilder(){
        return new GreeterRequestBuilder();
    }

    public static class GreeterRequestBuilder{
        private String name;

        public String getName() {
            return name;
        }

        public GreeterRequestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GreeterRequest build(){
            return new GreeterRequest(this.name);
        }
    }

}
