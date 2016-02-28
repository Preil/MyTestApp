package com.eurochemix.webapp.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ilya on 09.02.2016.
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Resume R1 = new Resume("Полное имя1", "Локация1", "www-ленинрад-спб.ру");
        R1.addContact(ContactType.MAIL, "mailme.gmail.com");
        R1.addContact(ContactType.PHONE, "777-77-77");

        Resume R2 = new Resume("Полное имя2", "Локация2", "www.yandex.ua");
        R2.addContact(ContactType.MAIL, "mailme.yandex.com");
        R2.addContact(ContactType.SKYPE, "preil200");

        Resume R3 = new Resume("Полное имя3", "Локация3", "www.google.ру");
        R3.addContact(ContactType.MAIL, "mailme.hotbox.com");
        R3.addContact(ContactType.PHONE, "222-55-11");

        List<Resume> resumes = Arrays.asList(R1, R2, R3);
        print(resumes);
    }

    class R2 extends Resume {

    }


    public static <T extends Resume> void print(List<T> list) {
        list.forEach(r -> System.out.println(r.toString()));
    }
}