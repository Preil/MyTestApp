package com.eurochemix.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * Created by Ilya on 11.02.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public enum ContactType implements Serializable{


    PHONE("Контактный телефон"),
    MOBILE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    SKYPE("Skype"){
        @Override
        public String toHtml(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtml(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    ICQ("ICQ"),
    WEB_PAGE("Web page");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // создаем массив элементов тип "ТипКонтакта" и заполняем его значениями типов
    public static ContactType[] VALUES = ContactType.values();

    public String toHtml(String value) {
        return title + ": " + value;
    }
}
