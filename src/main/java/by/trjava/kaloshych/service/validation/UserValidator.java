package by.trjava.kaloshych.service.validation;

public class UserValidator {
    private static final String LOGIN_FORMAT_REGEX = "^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$";

    private static final String PASSWORD_FORMAT_REGEX = "^[a-zA-Z0-9-_.]{4,12}$";

    private static final String NAME_FORMAT_REGEX = "^[a-zA-ZА-Яа-я][a-zA-ZА-Яа-я0-9-_.]{3,12}$";
    private static final String EMAIL_FORMAT_REGEX =
            "[a-z][[a-z][0-9][-][_]]{3,12}[@][a-z]{2,10}[.][a-z]{2,4}";

    private static final UserValidator instance = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance() {
        return instance;
    }

    public boolean validate(String login, String password) {
        return validateLogin(login) && validatePassword(password);
    }

    public boolean validate(String login, String password, String email, String name) {
        return validate(login, password) && validateEmail(email) && validateName(name);
    }

    public boolean validatePasswords(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean validateLogin(String login) {
        return login.matches(LOGIN_FORMAT_REGEX);
    }

    public boolean validateName(String name) {
        return name.matches(NAME_FORMAT_REGEX);
    }

    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_FORMAT_REGEX);
    }

    public boolean validateEmail(String email) {
        return email.matches(EMAIL_FORMAT_REGEX);
    }
}
