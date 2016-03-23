package com.eurochemix.webapp.web;

import com.eurochemix.webapp.model.ContactType;
import com.eurochemix.webapp.model.Organization;
import com.eurochemix.webapp.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ilya on 23.03.2016.
 */
public class HtmlUtil {
    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContact(type);
        return contact == null ? "&nbsp" : type.toHtml(contact);
    }

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public static String format(LocalDate date) {
        return date.equals(Organization.Period.NOW) ? "Now" : date.format(DATE_FORMATTER);
    }

}
