package com.eurochemix.webapp;

import com.eurochemix.webapp.Storage.XmlFileStorage;

/**
 * Created by Ilya on 09.03.2016.
 */
public class XmlFileStorageTest extends AbstractStorageTest {
    {
        storage = new XmlFileStorage("./file_storage/");
    }

}