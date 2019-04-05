package com.github.wxisme.watchdog.util;

import com.github.wxisme.watchdog.client.HttpMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignatureUtils {

    public static String sign(String appSecret, HttpMethod httpMethod, String timestamp, Map<String, String> params) throws Exception {
        if (params == null || params.size() <= 0) {
            return sign(appSecret, httpMethod, timestamp);
        }
        SortedMap<String, String> sortedParameters = new TreeMap<>();
        sortedParameters.putAll(params);
        StringBuilder sortedQueryString = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParameters.entrySet()) {
            sortedQueryString.append("&").append(urlEncode(entry.getKey())).append("=").append(urlEncode(entry.getValue()));
        }
        if (sortedQueryString.length() > 0) {
            sortedQueryString.deleteCharAt(0);
        }

        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod.getName()).append("&").append(timestamp).append("&")
                .append(urlEncode("/")).append("&").append(urlEncode(sortedQueryString.toString()));

        return sign(appSecret, stringToSign.toString());
    }

    public static String sign(String appSecret, HttpMethod httpMethod, String timestamp) throws Exception {
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(httpMethod.getName()).append("&").append(timestamp);

        return sign(appSecret, stringToSign.toString());
    }

    private static String sign(String appSecret, String stringToSign)
            throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(appSecret.getBytes(Constants.CHARSET), Constants.SIGNATURE_ALGORITHM);
        Mac mac = Mac.getInstance(Constants.SIGNATURE_ALGORITHM);
        mac.init(signingKey);
        byte[] data = mac.doFinal(stringToSign.getBytes(Constants.CHARSET));
        return Base64.getEncoder().encodeToString(data);
    }

    private static String urlEncode(String value) throws Exception {
        return URLEncoder.encode(value, Constants.CHARSET.name()).replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~");
    }

}
