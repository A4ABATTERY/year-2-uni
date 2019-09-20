package tests;

import org.junit.Test;

import tests.mockObjects.cfilteringDriver.mockCfilteringDriver;

import static org.junit.Assert.*;

public class CfilteringDriverTest {

    @Test
    public void getStringFromUser() {
        assertTrue("some".equals(mockCfilteringDriver.getStringFromUser()));
    }
}