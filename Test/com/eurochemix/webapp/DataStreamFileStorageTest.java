package com.eurochemix.webapp;

/**
 * Created by Ilya on 17.02.2016.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest {
    {
        storage = new DataStreamFileStorage("./file_storage/");
    }
}