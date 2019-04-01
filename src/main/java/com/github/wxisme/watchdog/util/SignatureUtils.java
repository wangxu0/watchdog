package com.github.wxisme.watchdog.util;

import com.github.wxisme.watchdog.client.HttpMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignatureUtils {

    private final static Charset CHARSET = Charset.forName("UTF-8");

    private final static String SIGNATURE_ALGORITHM = "HmacSHA256";


    public static String buildStringToSign(String appKey, String appSecret, HttpMethod method, Long timestamp) {
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(method.name()).append(appKey).append(appSecret).append(timestamp);
        return stringToSign.toString();
    }

    public static String buildStringToSign(String appKey, String appSecret, HttpMethod method, Long timestamp, Map<String, String> parameters) throws Exception {
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(method.name()).append(appKey).append(appSecret).append(timestamp);
        SortedMap<String, String> sortedParameters = new TreeMap<>();
        sortedParameters.putAll(parameters);
        for (Map.Entry<String, String> entry : sortedParameters.entrySet()) {
            stringToSign.append("&").append(urlEncode(entry.getKey())).append("=").append(urlEncode(entry.getValue()));
        }
        return stringToSign.toString();
    }


    public static String sign(String appSecret, String stringToSign)
            throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec signingKey = new SecretKeySpec(appSecret.getBytes(CHARSET), SIGNATURE_ALGORITHM);
        Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);
        mac.init(signingKey);
        byte[] data = mac.doFinal(stringToSign.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(data);
    }

    public static String urlEncode(String value) throws Exception {
        return URLEncoder.encode(value, CHARSET.name()).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

}
