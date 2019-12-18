package by.trjava.kaloshych.service.validation;

public class AccountValidator {
    private static final String REGEX_DOUBLE = "^[1-9][0-9]{0,2}[.]?[0-9]?[0-9]?$";
    private static final double MAX_SUM = 100.00;

    private static final AccountValidator instance = new AccountValidator();

    private AccountValidator() {
    }

    public static AccountValidator getInstance() {
        return instance;
    }

    public boolean validate(String amountOfMoney) {
        return amountOfMoney.matches(REGEX_DOUBLE) && Double.parseDouble(amountOfMoney) <= MAX_SUM;
    }


}