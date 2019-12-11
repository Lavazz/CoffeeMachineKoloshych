package by.trjava.kaloshych.service.validation;

public class CartValidator {
    private static final String IS_DOUBLE_REGEX = "^[1-9][0-9]?$";
    private static final int PORTION_MIN = 1;
    private static final int PORTION_MAX = 100;
    private static final CartValidator instance = new CartValidator();

    private CartValidator() {
    }

    public static CartValidator getInstance() {
        return instance;
    }


    public boolean validate(String portionString) {
        return portionString.matches(IS_DOUBLE_REGEX);
    }

    public boolean validate(int portion) {
        return portion >= PORTION_MIN && portion < PORTION_MAX;
    }

    public boolean isSufficientPortion(int currentPortion, int portion) {
        return currentPortion > portion;
    }

}
