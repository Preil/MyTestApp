package com.eurochemix.webapp;

/**
 * Created by Ilya on 22.02.2016.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Запускаем все тесты

@RunWith(Suite.class)
@Suite.SuiteClasses({ArrayStorageTest.class, MapStorageTest.class, DataStreamFileStorageTest.class,SerializeFileStorageTest.class})
public class AllStorageTest{

}