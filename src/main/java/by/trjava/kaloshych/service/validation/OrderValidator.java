package by.trjava.kaloshych.service.validation;

public class OrderValidator {

    private static final OrderValidator instance=new OrderValidator();
    private OrderValidator(){}

    public static OrderValidator getInstance(){
        return instance;
    }
}
