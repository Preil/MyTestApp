package com.eurochemix.webapp;

import com.eurochemix.webapp.Storage.SerializeFileStorage;

/**
 * Created by Ilya on 17.02.2016.
 */
public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("./file_storage/");
    }
}