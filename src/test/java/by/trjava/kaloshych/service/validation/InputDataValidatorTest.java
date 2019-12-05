package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputDataValidatorTest {
    private  InputDataValidator dataValidator=InputDataValidator.getInstance();


    @Test
    public void testIsEmptyObjectNull() {
        Object object = null;
        assertTrue(dataValidator.isEmpty(object));
    }

    @Test
    public void testIsEmptyStringEmpty() {
        String string = "";
        assertTrue(dataValidator.isEmpty(string));
    }

    @Test
    public void testIsEmptyNotEmpty() {
        String string = "aa";
        assertFalse(dataValidator.isEmpty(string));
    }
}