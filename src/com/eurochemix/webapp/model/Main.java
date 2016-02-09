package com.eurochemix.webapp.model;

/**
 * Created by Ilya on 09.02.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!!");
        Link l1 = new Link("Eurochemix","eurochemix.com");
        Link l2 = l1;
        Link l3 = new Link(l1);

        System.out.println(l1.equals(l2));
        System.out.println(l1.equals(l3));

    }
}