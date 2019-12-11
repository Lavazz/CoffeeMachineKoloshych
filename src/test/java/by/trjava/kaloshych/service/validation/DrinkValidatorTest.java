package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class DrinkValidatorTest {

    private static final String COMPONENT_VALID="coffee";
    private static final String COMPONENT_INVALID="co";
    private static final double PRICE_VALID=2;
    private static final double PRICE_INVALID=12;
    private static final String DESCRIPTION="Tasty coffee";



    @Test
    public void validateValid() {
        assertTrue(DrinkValidator.getInstance().validate(COMPONENT_VALID, PRICE_VALID, DESCRIPTION));
    }

    @Test
    public void validateInvalidName() {
        assertFalse(DrinkValidator.getInstance().validate(COMPONENT_INVALID, PRICE_VALID, DESCRIPTION));
    }

    @Test
    public void validateInvalidPrice() {
        assertFalse(DrinkValidator.getInstance().validate(COMPONENT_VALID, PRICE_INVALID, DESCRIPTION));
    }
}