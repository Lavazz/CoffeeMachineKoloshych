package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartValidatorTest {


    private static final String PORTION_STRING_VALID="1";
    private static final String PORTION_STRING_INVALID="120";
    private static final String PORTION_STRING_INVALID_ZERO="0";
    private static final int PORTION_VALID=1;
    private static final int PORTION_INVALID=120;


    @Test
    public void validateStringValid() {
        assertTrue(CartValidator.getInstance().validate(PORTION_STRING_VALID));
    }

    @Test
    public void validateStringInvalid() {
        assertFalse(CartValidator.getInstance().validate(PORTION_STRING_INVALID));
    }

    @Test
    public void validateStringInvalidZero() {
        assertFalse(CartValidator.getInstance().validate(PORTION_STRING_INVALID_ZERO));
    }

    @Test
    public void validateValid() {
        assertTrue(CartValidator.getInstance().validate(PORTION_VALID));
    }

    @Test
    public void validateInvalid() {
        assertFalse(CartValidator.getInstance().validate(PORTION_INVALID));
    }
}