package com.github.wxisme.watchdog.client;

import com.github.wxisme.watchdog.exception.InvalidParameterException;

import java.util.Map;

public class WatchdogClient {

    public static Map<String, String> getSignatureHeader(String appKey, String appSecret, HttpMethod method) {
        if (appKey == null || appSecret == null) {
            throw new InvalidParameterException();
        }

        return null;
    }

}
