package by.trjava.kaloshych.service.validation;

public class AdditionalIngredientValidator {
   private static final AdditionalIngredientValidator instance=new AdditionalIngredientValidator();
    private AdditionalIngredientValidator(){};

    private static final String ADDITIONAL_INGREDIENT_SUGAR="сахар";
    private static final String ADDITIONAL_INGREDIENT_CREME="сливки";
    private static final String ADDITIONAL_INGREDIENT_MARZIPAN="марципан";
    private static final String ADDITIONAL_INGREDIENT_CHOCOLATE_CHIPS="шоколадная крошка";

    public boolean validate(int additionalIngredient){
        return true;
//        return additionalIngredient.equalsIgnoreCase(ADDITIONAL_INGREDIENT_SUGAR)
//                &&additionalIngredient.equalsIgnoreCase(ADDITIONAL_INGREDIENT_CREME)
//                &&additionalIngredient.equalsIgnoreCase(ADDITIONAL_INGREDIENT_MARZIPAN)
//                &&additionalIngredient.equalsIgnoreCase(ADDITIONAL_INGREDIENT_CHOCOLATE_CHIPS);
    }
 public static AdditionalIngredientValidator getInstance(){
        return  instance;
 }
}
