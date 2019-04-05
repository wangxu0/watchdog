package com.github.wxisme.watchdog.client;

import com.github.wxisme.watchdog.exception.InvalidParameterException;
import com.github.wxisme.watchdog.util.Constants;
import com.github.wxisme.watchdog.util.DateUtils;
import com.github.wxisme.watchdog.util.SignatureUtils;

import java.util.HashMap;
import java.util.Map;

public class WatchdogClient {

    public static Map<String, String> getSignatureHeaders(String appKey, String appSecret, HttpMethod method) throws Exception {
        if (appKey == null || appSecret == null || method == null) {
            throw new InvalidParameterException();
        }
        String timestamp = DateUtils.getStandardDateString();
        String signature = SignatureUtils.sign(appSecret, method, timestamp);
        return buildSignatureHeaders(appKey, timestamp, signature);
    }

    public static Map<String, String> getSignatureHeaders(String appKey, String appSecret, HttpMethod method, Map<String, String> params) throws Exception {
        if (appKey == null || appSecret == null || method == null) {
            throw new InvalidParameterException();
        }
        String timestamp = DateUtils.getStandardDateString();
        String signature = SignatureUtils.sign(appSecret, method, timestamp, params);
        return buildSignatureHeaders(appKey, timestamp, signature);
    }

    public static Map<String, String> getSignatureHeaders(String appKey, String appSecret, HttpMethod method, String body) throws Exception {
        if (appKey == null || appSecret == null || method == null) {
            throw new InvalidParameterException();
        }
        Map<String, String> params = null;
        if (body != null && body.length() > 0) {
            params = new HashMap<>();
            params.put(Constants.HTTP_BODY, body);
        }

        String timestamp = DateUtils.getStandardDateString();
        String signature = SignatureUtils.sign(appSecret, method, timestamp, params);
        return buildSignatureHeaders(appKey, timestamp, signature);
    }

    public static Map<String, String> getSignatureHeaders(String appKey, String appSecret, HttpMethod method, Map<String, String> params, String body) throws Exception {
        if (appKey == null || appSecret == null || method == null) {
            throw new InvalidParameterException();
        }
        if (body != null && body.length() > 0) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(Constants.HTTP_BODY, body);
        }

        String timestamp = DateUtils.getStandardDateString();
        String signature = SignatureUtils.sign(appSecret, method, timestamp, params);
        return buildSignatureHeaders(appKey, timestamp, signature);
    }

    private static Map<String, String> buildSignatureHeaders(String appKey, String timestamp, String signature) {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.APP_KEY_HEADER, appKey);
        headers.put(Constants.TIMESTAMP_HEADER, timestamp);
        headers.put(Constants.SIGNATURE_HEADER, signature);
        return headers;
    }

}
