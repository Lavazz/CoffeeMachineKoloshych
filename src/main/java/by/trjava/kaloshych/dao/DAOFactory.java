package by.trjava.kaloshych.dao;

import by.trjava.kaloshych.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final AccountDAO accountDAO = new SQLAccountDAO();
    private final AccountUserDAO accountUserDAO = new SQLAccountUserDAO();
    private final AdditionalIngredientDAO additionalDAO = new SQLAdditionalIngredientDAO();
    private final DrinkDAO drinkDAO = new SQLDrinkDAO();
    private final FillingOperationDAO fillingDAO = new SQLFillingOperationDAO();
    private final OrderDAO orderDAO = new SQLOrderDAO();
    private final UserDAO userDAO = new SQLUserDAO();
    private final CartUserDAO cartUserDAO = new SQLCartUserDAO();
    private final CartDAO cartDAO = new SQLCartDAO();
    private final CartAdditionalIngredientDAO cartAdditionalIngredientDAO = new SQLCartAdditionalIngredientDAO();
    private final ComponentDAO componentDAO = new SQLComponentDAO();
    private final PaymentMethodDAO paymentMethodDAO = new SQLPaymentMethodDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }


    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public AccountUserDAO getAccountUserDAO() {
        return accountUserDAO;
    }

    public AdditionalIngredientDAO getAdditionalIngredientDAO() {
        return additionalDAO;
    }

    public DrinkDAO getDrinkDAO() {
        return drinkDAO;
    }

    public FillingOperationDAO getFillingOperationDAO() {
        return fillingDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CartUserDAO getCartUserDAO() {
        return cartUserDAO;
    }

    public CartDAO getCartDAO() {
        return cartDAO;
    }

    public CartAdditionalIngredientDAO getCartAdditionalIngredientDAO() {
        return cartAdditionalIngredientDAO;
    }

    public ComponentDAO getComponentDAO() {
        return componentDAO;
    }

    public PaymentMethodDAO getPaymentMethodDAO() {
        return paymentMethodDAO;
    }

}