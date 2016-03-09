package com.eurochemix.webapp.Lesson07;

import java.util.Date;

/**
 * Created by Ilya on 07.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(Long.MAX_VALUE));
    }
}
