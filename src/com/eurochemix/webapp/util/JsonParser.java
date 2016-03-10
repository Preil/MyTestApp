package com.eurochemix.webapp.util;

import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;

/**
 * Created by Ilya on 10.03.2016.
 */
public class JsonParser {
    private static Gson GSON = new Gson();
    public static <T> T read (Reader reader, Class<T> clazz){return GSON.fromJson(reader,clazz);}
    public static <T> void write(T object, Writer writer){GSON.toJson(object,writer);}
}
