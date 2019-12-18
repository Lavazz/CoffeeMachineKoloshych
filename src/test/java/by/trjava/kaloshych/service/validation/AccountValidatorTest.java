package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorTest {

    private static final String MONEY_VALID = "4";
    private static final String MONEY_INVALID = "0";

    @Test
    public void validateValid() {
        assertTrue(AccountValidator.getInstance().validate(MONEY_VALID));
    }

    @Test
    public void validateInvalid() {
        assertFalse(AccountValidator.getInstance().validate(MONEY_INVALID));
    }
}