package com.eurochemix.webapp.model;

/**
 * Created by Ilya on 11.02.2016.
 */
public enum ContactType {
    PHONE("Контактный телефон"),
    MOBILE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    SKYPE("Skype"),
    MAIL("Почта"),
    ICQ("ICQ"),
    WEB_PAGE("Web page");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
