package by.trjava.kaloshych.entity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStatusTest {

    @Test
    public void getUserStatusPass() {
        String actual = UserStatus.valueOf("admin".toUpperCase()).toString();
        String expected =  "ADMIN";

        Assert.assertEquals(actual, expected);
    }
}