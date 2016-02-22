package com.eurochemix.webapp.Examples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ilya on 22.02.2016.
 */
public class CalculatorTest {

    static Calculator calc;
    static {
        calc = new Calculator();
    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAbs() throws Exception {
        Assert.assertEquals(5, calc.abs(-5));
        System.out.println(calc.abs(-5));
    }
}