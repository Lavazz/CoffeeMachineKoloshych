package by.trjava.kaloshych.service.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidatorTest {


    @Test
    public void validateLoginInvalid() {
        String login = "11";
        assertFalse(UserValidator.getInstance().validateLogin(login));
    }

    @Test
    public void validateLoginCorrect() {
        String login = "Java";
        assertTrue(UserValidator.getInstance().validateLogin(login));
    }


    @Test
    public void testValidateEmailCorrect() {
        String email = "email@gmail.by";
        assertTrue(UserValidator.getInstance().validateEmail(email));
    }

    @Test
    public void testValidateEmailInvalid() {
        String email = "email";
        assertFalse(UserValidator.getInstance().validateEmail(email));
    }

    @Test
    public void testValidateNameCorrect() {
        String name = "Name";
        assertTrue(UserValidator.getInstance().validateName(name));
    }

    @Test
    public void testValidateNameInvalid() {
        String name = "1n";
        assertFalse(UserValidator.getInstance().validateName(name));
    }

}