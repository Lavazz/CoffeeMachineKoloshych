package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionalIngredientValidatorTest {

    private static final String COMPONENT_VALID="coffee";
    private static final String COMPONENT_INVALID="co";
    private static final int CALORIES_VALID=2;
    private static final int CALORIES_INVALID=1200;


    @Test
    public void validateValid() {
        assertTrue(AdditionalIngredientValidator.getInstance().validate(COMPONENT_VALID, CALORIES_VALID));
    }

    @Test
    public void validateInvalidName() {
        assertFalse(AdditionalIngredientValidator.getInstance().validate(COMPONENT_INVALID, CALORIES_VALID));
    }

    @Test
    public void validateInvalidPrice() {
        assertFalse(AdditionalIngredientValidator.getInstance().validate(COMPONENT_VALID, CALORIES_INVALID));
    }
}