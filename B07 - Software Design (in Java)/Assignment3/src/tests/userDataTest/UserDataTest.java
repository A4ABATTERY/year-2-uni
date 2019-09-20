package tests.userDataTest;

import org.junit.Test;

import static org.junit.Assert.*;

import tests.mockObjects.userData.mockUserData;

public class UserDataTest {


    @Test
    public void toStringTest() {
        assertTrue("String".equals(new mockUserData().toString()));
    }

    @Test
    public void getSimAndDisSimRatings() {
        assertTrue("String2".equals((new mockUserData()).getSimAndDisSimRatings()));

    }
}