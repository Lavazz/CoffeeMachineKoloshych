package by.trjava.kaloshych.service.validation;

public class CartAdditionalIngredientValidator {
    private  static  final int PORTION_MIN=1;

    private CartAdditionalIngredientValidator() {
    }
    private static final CartAdditionalIngredientValidator instance=new CartAdditionalIngredientValidator();

    public static CartAdditionalIngredientValidator getInstance(){
        return instance;
    }



    public boolean isSufficientPortion(int portion){

        return portion>=PORTION_MIN;
    }
}

