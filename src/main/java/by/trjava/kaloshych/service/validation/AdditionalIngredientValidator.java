package by.trjava.kaloshych.service.validation;

public class AdditionalIngredientValidator {

    private static final AdditionalIngredientValidator instance = new AdditionalIngredientValidator();
    private static final String NAME_COMPONENT_FORMAT_REGEX = ".{3,30}";
    private static final int CALORIES_MIN = 0;
    private static final int CALORIES_MAX = 600;

    private AdditionalIngredientValidator() {
    }

    public static AdditionalIngredientValidator getInstance() {
        return instance;
    }

    public boolean validate(String additionalIngredientName, int calories) {
        return additionalIngredientName.matches(NAME_COMPONENT_FORMAT_REGEX) &&
                (calories > CALORIES_MIN && calories < CALORIES_MAX);

    }
}
