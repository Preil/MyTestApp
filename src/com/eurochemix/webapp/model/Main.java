package com.eurochemix.webapp.model;

import java.lang.reflect.Field;

/**
 * Created by Ilya on 09.02.2016.
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field f = Link.class.getDeclaredField("url");
        f.setAccessible(true);
        Link l = new Link("Eurochemix", "url:eurochemix.com");
        System.out.println(f.get(l));

        StringBuilder fill = new StringBuilder() ;
        for (int i=0;i<=100;i++){
            fill.append("~");
        }
        System.out.println(fill.toString());
   }
}