package com.eurochemix.webapp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ilya on 08.02.2016.
 */
public class Resume {
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private List<Contact> contacts = new LinkedList<>();
    private List<Section> sections = new LinkedList<>();

    public Resume(String uuid, String fullName, String location, String homePage) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.location = location;
        this.homePage = homePage;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
