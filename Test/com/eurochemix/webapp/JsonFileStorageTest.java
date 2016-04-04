package com.eurochemix.webapp;

import com.eurochemix.webapp.Storage.JsonFileStorage;

/**
 * Created by Ilya on 09.03.2016.
 */
public class JsonFileStorageTest extends AbstractStorageTest {
    {
        storage = new JsonFileStorage("./file_storage/");
    }

}