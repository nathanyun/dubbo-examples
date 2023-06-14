package com.lb.dubbo.reactor;

import java.io.Serializable;

/**
 * 响应参数
 */
public class GreeterReply implements Serializable {
    private String message;

    GreeterReply(String message) {
        this.message = message;
    }

    public static GreeterReplyBuilder newBuilder() {
        return new GreeterReplyBuilder();
    }

    public static class GreeterReplyBuilder {
        private String message;

        GreeterReplyBuilder() {
        }

        public GreeterReplyBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public GreeterReply build() {
            return new GreeterReply(this.message);
        }

        public String toString() {
            return "GreeterReply.GreeterReplyBuilder(message=" + this.message + ")";
        }
    }
}
