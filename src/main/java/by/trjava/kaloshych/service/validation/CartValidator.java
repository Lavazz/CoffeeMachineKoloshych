package by.trjava.kaloshych.service.validation;

import by.trjava.kaloshych.entity.Cart;

public class CartValidator {
    private static final String IS_DOUBLE_REGEX="^[1-9][0-9]?$";
    private  static  final int PORTION_MIN=1;
    private  static  final int PORTION_MAX=100;


   private CartValidator() {
    }
 private static final CartValidator instance=new CartValidator();

   public static CartValidator getInstance(){
       return instance;
   }



   public boolean validate(String portionString){
       double portion=0;
       if(portionString.matches(IS_DOUBLE_REGEX)){
           portion=Integer.parseInt(portionString);
       }else{
           return false;
       }
       return portion>=PORTION_MIN&&portion<PORTION_MAX;
   }

    public boolean validate(int portion){
        return portion>=PORTION_MIN&&portion<PORTION_MAX;
    }

    public boolean isSufficientPortion(int currentPortion, int portion){
        return currentPortion>portion;
    }

}
