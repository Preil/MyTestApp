package com.eurochemix.webapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ilya on 10.02.2016.
 */
public class Organization implements Serializable {

    private  Link link;
    List<OrganizationPeriod> periods;
}
