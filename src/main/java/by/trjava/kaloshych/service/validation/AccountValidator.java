package by.trjava.kaloshych.service.validation;

public class AccountValidator {

    private static final double MIN_PAYMENT_SUM=2.0;
    private static final double MAX_PAYMENT_SUM=1000.0;

    private static final AccountValidator instance=new AccountValidator();
    private AccountValidator(){}


    public boolean validate(double amountOfMoney) {
        return amountOfMoney>=MIN_PAYMENT_SUM&&amountOfMoney<MAX_PAYMENT_SUM;
    }


 public static AccountValidator getInstance(){
        return instance;
 }

}