package com.eurochemix.webapp.model;

/**
 * Created by Ilya on 09.02.2016.
 */
public class Main {

    enum Season {WINTER, SPRING, SUMMER, AUTUMN}

    public static void main(String[] args) {


        System.out.println("Hello world!!");
        Link l1 = new Link("Eurochemix", "eurochemix.com");
        Link l2 = l1;
        Link l3 = new Link(l1);

        System.out.println(l1.equals(l2));
        System.out.println(l1.equals(l3));
        System.out.println(l1);

        System.out.println(Link.EMPTY);
        System.out.println(l1.empty());

        Integer obj = new Integer(5);
        printInt(5);




    private static void printInt(Integer obj) {
        System.out.println("Object: " + obj);

    }

    private static void printInt(int i) {
        System.out.println("Integer: " + i);

    }
}