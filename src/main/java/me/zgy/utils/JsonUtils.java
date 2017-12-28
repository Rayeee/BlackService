package me.zgy.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Rayee on 2017/12/25.
 */
public class JsonUtils {

    private static final Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}
