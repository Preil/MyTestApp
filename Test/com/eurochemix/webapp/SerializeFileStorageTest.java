package com.eurochemix.webapp;

/**
 * Created by Ilya on 17.02.2016.
 */
public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("./file_storage/");
    }
}