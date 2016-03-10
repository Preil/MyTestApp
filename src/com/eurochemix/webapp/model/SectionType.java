package com.eurochemix.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Created by Ilya on 11.02.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
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
