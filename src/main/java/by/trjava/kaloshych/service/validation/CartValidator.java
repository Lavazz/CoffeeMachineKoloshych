package by.trjava.kaloshych.service.validation;

import by.trjava.kaloshych.entity.Cart;

public class CartValidator {
    private  static  final int PORTION_MIN=1;
    private  static  final int PORTION_MAX=100;


   private CartValidator() {
    }
 private static final CartValidator instance=new CartValidator();

   public static CartValidator getInstance(){
       return instance;
   }



   public boolean validate(int portion){
       return portion>=PORTION_MIN&&portion<PORTION_MAX;
   }

   public boolean isSufficientPortion(int portion){
       return portion>=PORTION_MIN;
   }
}
