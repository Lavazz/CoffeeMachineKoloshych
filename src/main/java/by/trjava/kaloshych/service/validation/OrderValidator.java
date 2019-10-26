package by.trjava.kaloshych.service.validation;

public class OrderValidator {
    private final static int MAX_PORTION=100;

    private static final OrderValidator instance=new OrderValidator();
    private OrderValidator(){}

    public boolean validate(int user, int drink, int additionalIngredient, int portion){
        return UserValidator.getInstance().validateIdUser(user)
                &&DrinkValidator.getInstance().validateIdDrink(drink)
                &&AdditionalIngredientValidator.getInstance().validate(additionalIngredient)
                &&(portion<MAX_PORTION);
    }

//    public boolean validate(int idOrder){
//        OrderService orderService= ServiceFactory.getInstance().getOrderService();
//       return orderService.checkIdOrder(idOrder);
//    }

    public static OrderValidator getInstance(){
        return instance;
    }
}
