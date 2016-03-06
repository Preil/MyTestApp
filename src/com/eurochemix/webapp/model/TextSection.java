package com.eurochemix.webapp.model;

import java.io.Serializable;

/**
 * Created by Ilya on 11.02.2016.
 */
public class TextSection extends Section implements Serializable {
    static final long serialVersionUID = 1L;
    private String value;

    public TextSection(){
    }

    public TextSection (String value) {this.value=value;}

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
