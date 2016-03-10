package com.eurochemix.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ilya on 11.02.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section implements Serializable {

    private List<Organization> values;

    public OrganizationSection(Organization... values){
        this.values = new LinkedList<>(Arrays.asList(values));
    }

}
