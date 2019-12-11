package by.trjava.kaloshych.service.validation;

public class DrinkValidator {

    private static final String NAME_COMPONENT_FORMAT_REGEX = ".{3,30}";
    private static final String DESCRIPTION_FORMAT_REGEX = ".{0,255}";
    private static final double PRICE_MIN = 0.5;
    private static final double PRICE_MAX = 10;


    private static final DrinkValidator instance = new DrinkValidator();

    private DrinkValidator() {
    }

    public static DrinkValidator getInstance() {
        return instance;
    }

    public boolean validate(String drinkName, double price, String description) {
        return drinkName.matches(NAME_COMPONENT_FORMAT_REGEX) &&
                (price > PRICE_MIN && price < PRICE_MAX) &&
                description.matches(DESCRIPTION_FORMAT_REGEX);

    }
}
