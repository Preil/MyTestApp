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
public class MultiTextSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;

    private List<String> values = new LinkedList<>();

    public MultiTextSection(String... values) {
        this (new LinkedList<>(Arrays.asList(values)));
    }

    public MultiTextSection (List<String> values){this.values = values;}

    public MultiTextSection(){
    }


    public List<String> getValues(){return values;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTextSection that = (MultiTextSection) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}

