package by.trjava.kaloshych.service;

import by.trjava.kaloshych.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final DrinkService drinkService = new DrinkServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final AdditionalIngredientService additionalIngredientService = new AdditionalIngredientServiceImpl();

    private final FillingOperationService fillingOperationService = new FillingOperationServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final OrderJournalService orderJournalService = new OrderJournalServiceImpl();

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

    public OrderJournalService getOrderJournalService(){
        return orderJournalService;
    }
}