package com.aka.server.akaminiprogramserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

/**
 * <p>Title: JsonMapper</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019版权</p>
 * <p>Company: </p>
 *
 * @author Zwiebeln_Chan
 * @version V1.0
 * @date 2019/5/25 16:52
 */
public class GlobalComponent {
    private static ObjectMapper mapper = null;
    private static OkHttpClient okHttpClient = null;

    private GlobalComponent(){}

    public static ObjectMapper getJsonMapper() {
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    public static OkHttpClient getOkHttpClient() {
        if(okHttpClient == null) okHttpClient = new OkHttpClient();
        return okHttpClient;
    }
}
