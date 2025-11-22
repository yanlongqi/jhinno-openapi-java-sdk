package com.jhinno.sdk.openapi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jhinno.sdk.openapi.CommonConstant;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(CommonConstant.NORM_DATETIME_PATTERN));
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @SneakyThrows
    public static <T> T stringToObject(String str, Class<T> cls) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return OBJECT_MAPPER.readValue(str, cls);
    }

    @SneakyThrows
    public static <T> T stringToObject(String str, TypeReference<T> type) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return OBJECT_MAPPER.readValue(str, type);
    }


    @SneakyThrows
    public static <T> T isToObject(InputStream is, Class<T> cls) {
        if (is == null) {
            return null;
        }
        return OBJECT_MAPPER.readValue(is, cls);
    }

    @SneakyThrows
    public static String objectToString(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

}
