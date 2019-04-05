package com.github.wxisme.watchdog.util;

import java.nio.charset.Charset;

public class Constants {

    public final static Charset CHARSET = Charset.forName("UTF-8");

    public final static String SIGNATURE_ALGORITHM = "HmacSHA256";


    public final static String APP_KEY_HEADER = "Watchdog-AppKey";

    public final static String TIMESTAMP_HEADER = "Watchdog-Timestamp";

    public final static String SIGNATURE_HEADER = "Watchdog-Signature";

    public final static String HTTP_BODY = "Watchdog-Body";

}
