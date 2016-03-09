package com.eurochemix.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Created by Ilya on 10.02.2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private Link link;
    List<Period> periods;

    public Organization() {
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period {

        private LocalDate startDate;
        private LocalDate endDate;
        private String position;
        private String content;

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        public Period(int yearStart, Month monthStart, int yearEnd, Month monthEnd, String position, String content) {

            new Period(LocalDate.of(yearStart, monthStart, 1), LocalDate.of(yearEnd, monthEnd, 1), position,content);

        }
    }

}
