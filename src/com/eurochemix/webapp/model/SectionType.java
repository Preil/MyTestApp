package com.eurochemix.webapp.model;

import java.io.Serializable;

/**
 * Created by Ilya on 11.02.2016.
 */
public enum SectionType implements Serializable {

    OBJECTIVE("Позиция"),
    ACHIVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPIRIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;


    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
