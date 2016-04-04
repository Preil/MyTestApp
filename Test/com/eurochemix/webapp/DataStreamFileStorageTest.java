package com.eurochemix.webapp;

import com.eurochemix.webapp.Storage.DataStreamFileStorage;

/**
 * Created by Ilya on 17.02.2016.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest {
    {
        storage = new DataStreamFileStorage("./file_storage/");
    }
}