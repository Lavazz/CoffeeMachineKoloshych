package by.trjava.kaloshych.service;

import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.service.impl.*;
import by.trjava.kaloshych.service.util.encrypting.PasswordEncrypting;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final DrinkService drinkService = new DrinkServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final AccountUserService accountUserService=new AccountUserServiceImpl();
    private final AdditionalIngredientService additionalIngredientService = new AdditionalIngredientServiceImpl();

    private final FillingOperationService fillingOperationService = new FillingOperationServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final CartService cartService = new CartServiceImpl();
    private final CartUserService cartUser=new CartUserServiceImpl();
    private final CartAdditionalIngredientService cartAdditionalIngredientService=new CartAdditionalIngredientServiceImpl();
private final ComponentService componentService=new ComponentServiceImpl();
    private final PaymentMethodService paymentMethodService=new PaymentMethodServiceImpl();


    private ServiceFactory() {
           }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public AdditionalIngredientService getAdditionalIngredientService() {
        return additionalIngredientService;
    }

    public DrinkService getDrinkService() {
        return drinkService;
    }

    public FillingOperationService getFillingOperationService() {
        return fillingOperationService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CartService getCartService(){
        return cartService;
    }

    public CartUserService getCartUser(){
        return cartUser;
    }

    public CartAdditionalIngredientService getCartAdditionalIngredient(){
        return cartAdditionalIngredientService;
    }

    public AccountUserService getAccountUserService(){
        return accountUserService;
    }

    public ComponentService getComponentService() {return componentService;}

    public  PaymentMethodService getPaymentMethodService(){return paymentMethodService;}

}