package com.eurochemix.webapp;

/**
 * Created by Ilya on 09.03.2016.
 */
public class SqlStorageTest extends AbstractStorageTest {
    {
        storage = WebAppConfig.get().getStorage();
    }

}