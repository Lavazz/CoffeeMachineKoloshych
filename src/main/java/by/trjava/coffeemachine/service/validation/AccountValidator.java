package by.trjava.coffeemachine.service.validation;

import by.trjava.coffeemachine.entity.Order;

public class AccountValidator {
    private final static String METHOD_ERIP="ЕРИП";
    private final static String METHOD_CARD="Карта";
    private final static String METHOD_BONUS="бонус за регистрацию";
    private final static String METHOD_QR_CODE="QR-код";
    private final static double MIN_PAYMENT_SUM=2;

    private static final AccountValidator instance=new AccountValidator();
    private AccountValidator(){}


    public boolean validate(String login, String paymentMethod, double amountOfMoney){
return UserValidator.getInstance().validateLogin(login)
        &&validatePaymentMethod(paymentMethod)
        &&validateMoney(amountOfMoney);
       }


   public boolean validate(int idUser, String paymentMethod, double amountOfMoney){
       return UserValidator.getInstance().validateIdUser(idUser)
       &&validatePaymentMethod(paymentMethod)
       &&validateMoney(amountOfMoney);
   }

//    public boolean validate(Order order){
//       return UserValidator.getInstance().validateIdUser(order.getIdUser())
//        &&validateMoney(order.getTotalCost())
//        &&DrinkValidator.getInstance().validateIdDrink(order.getIdDrink().getIdDrink());
//   }


    public boolean validateMoney(double amountOfMoney){
        return amountOfMoney>MIN_PAYMENT_SUM;
    }

    public boolean validatePaymentMethod(String paymentMethod){
        return paymentMethod.equalsIgnoreCase(METHOD_ERIP)
                ||paymentMethod.equalsIgnoreCase(METHOD_CARD)
                ||paymentMethod.equalsIgnoreCase(METHOD_QR_CODE)
                ||paymentMethod.equalsIgnoreCase(METHOD_BONUS);
    }

 public static AccountValidator getInstance(){
        return instance;
 }

}