package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountValidatorTest {

   private static final double MONEY_VALID=4;
    private static final double MONEY_INVALID=1;

    @Test
    public void validateValid() {
        assertTrue(AccountValidator.getInstance().validate(MONEY_VALID));
    }

    @Test
    public void validateInvalid() {
        assertFalse(AccountValidator.getInstance().validate(MONEY_INVALID));
    }
}