package by.trjava.kaloshych.service.validation;

public class FillingOperationValidator {
    private final static int MIN_PORTION=0;
    private final static int MAX_PORTION=400;

    private static final FillingOperationValidator instance=new FillingOperationValidator();
    private FillingOperationValidator(){};

//    public  boolean validate(Ingredient ingredient, int amountPortion){
//        boolean basicIngredient=DrinkValidator.getInstance().validate(ingredient.getNameIngredient());
//        boolean additionalIngredient=AdditionalIngredientValidator.getInstance().validate(ingredient.getNameIngredient());
//        return (basicIngredient||additionalIngredient)&&(amountPortion>MIN_PORTION&&amountPortion<MAX_PORTION);
//    }

    public static FillingOperationValidator getInstance(){
        return instance;
    }
}
