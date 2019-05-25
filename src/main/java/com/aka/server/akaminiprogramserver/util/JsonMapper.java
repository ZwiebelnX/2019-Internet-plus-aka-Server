package com.aka.server.akaminiprogramserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;

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
public class JsonMapper {
    private static ObjectMapper mapper = null;

    private JsonMapper(){}

    public static ObjectMapper getMapper() {
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}
