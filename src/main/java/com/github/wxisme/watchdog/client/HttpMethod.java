package com.github.wxisme.watchdog.client;

public enum HttpMethod {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS");

    private final String name;
    private HttpMethod(String name) {
        this.name = name;
    }

}
