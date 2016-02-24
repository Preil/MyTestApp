package com.eurochemix.webapp.model;

import java.util.*;

/**
 * Created by Ilya on 08.02.2016.
 */
public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private List<Section> sections = new LinkedList<>();


    public Resume(String uuid, String fullName, String location, String homePage) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
        this.homePage = homePage;
    }

    public Resume(String fullName, String location, String homePage) {
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.location = location;
        this.homePage = homePage;
    }

    public Resume() {

    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }
    public String getContact(ContactType type) {
        return contacts.get(type);
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


    public List<Section> getSections() {
        return sections;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return uuid.equals(other.uuid);
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }
}
