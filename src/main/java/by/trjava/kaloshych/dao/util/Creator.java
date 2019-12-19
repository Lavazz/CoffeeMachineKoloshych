package by.trjava.kaloshych.dao.util;

import by.trjava.kaloshych.builder.impl.*;
import by.trjava.kaloshych.dao.*;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.trjava.kaloshych.dao.util.configuration.ConfigurationManager.*;

/**
 * This class creates entity objects from {@seeResultSet}
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class Creator {

    private static final Creator instance = new Creator();
    private final CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
    private final AdditionalIngredientDAO additionalIngredientDAO = DAOFactory.getInstance().getAdditionalIngredientDAO();
    private final CartUserDAO cartUserDAO = DAOFactory.getInstance().getCartUserDAO();
    private final PaymentMethodDAO paymentMethodDAO = DAOFactory.getInstance().getPaymentMethodDAO();
    private final AccountUserDAO accountUserDAO = DAOFactory.getInstance().getAccountUserDAO();

    private Creator() {
    }

    public static Creator getInstance() {
        return instance;
    }

    public User createUser(ResultSet rs) throws SQLException {
        int idUser = rs.getInt(PARAMETER_ID_USER);
        String name = rs.getString(PARAMETER_NAME);
        String email = rs.getString(PARAMETER_EMAIL);
        String login = rs.getString(PARAMETER_LOGIN);
        String password = rs.getString(PARAMETER_PASSWORD);
        String status = rs.getString(PARAMETER_STATUS);
        UserStatus userStatus = UserStatus.valueOf(status.toUpperCase());
        return
                new UserBuilderImpl()
                        .withId(idUser)
                        .withName(name)
                        .withEmail(email)
                        .withLogin(login)
                        .withPassword(password)
                        .withUserStatus(userStatus)
                        .build();
    }


    public AccountUser createAccountUser(ResultSet rs) throws SQLException, DAOException {
        int idAccountUser = rs.getInt(PARAMETER_ID_ACCOUNT_USER);
        int idUser = rs.getInt(PARAMETER_ID_USER);
        User user = userDAO.getUserById(idUser);
        return
                new AccountUserBuilderImpl()
                        .withIdAccountUser(idAccountUser)
                        .withUser(user)
                        .build();

    }

    public AdditionalIngredient createAdditionalIngredient(ResultSet rs) throws SQLException {
        int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
        String nameComponent = rs.getString(PARAMETER_ADDITIONAL_INGREDIENT);
        int calories = rs.getInt(PARAMETER_CALORIES);
        String picturePath = rs.getString(PARAMETER_PICTURE_PATH);
        int portion = rs.getInt(PARAMETER_PORTION);
        return
                new AdditionalIngredientBuilderImpl()
                        .withIdComponent(idAdditionalIngredient)
                        .withNameComponent(nameComponent)
                        .withCalories(calories)
                        .withPicturePath(picturePath)
                        .withPortion(portion)
                        .build();
    }

    public CartAdditionalIngredient createCartAdditionalIngredient(ResultSet rs) throws SQLException, DAOException {
        int idCartAdditionalIngredient = rs.getInt(PARAMETER_ID_CART_ADDITIONAL_INGREDIENT);
        int idCart = rs.getInt(PARAMETER_ID_CART);
        Cart cart = cartDAO.getCartById(idCart);
        int idAdditionalIngredient = rs.getInt(PARAMETER_ID_ADDITIONAL_INGREDIENT);
        AdditionalIngredient additionalIngredient = additionalIngredientDAO.getAdditionalIngredient(idAdditionalIngredient);
        return new CartAdditionalIngredientBuilderImpl()
                .withIdCartAdditionalIngredient(idCartAdditionalIngredient)
                .withCart(cart)
                .withAdditionalIngredient(additionalIngredient)
                .build();
    }

    public Cart createCart(ResultSet rs) throws SQLException, DAOException {
        int idCart = rs.getInt(PARAMETER_ID_CART);
        int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
        CartUser cartUser = cartUserDAO.getCartUser(idCartUser);
        int idDrink = rs.getInt(PARAMETER_ID_DRINK);
        Drink drink = drinkDAO.getDrink(idDrink);
        int portion = rs.getInt(PARAMETER_PORTION);

        return new CartBuilderImpl()
                .withIdCart(idCart)
                .withCartUser(cartUser)
                .withDrink(drink)
                .withPortion(portion)
                .build();
    }

    public CartUser createCartUser(ResultSet rs) throws SQLException, DAOException {
        int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
        int idUser = rs.getInt(PARAMETER_ID_USER);
        User user = userDAO.getUserById(idUser);
        return new CartUserBuilderImpl()
                .withIdCartUser(idCartUser)
                .withUser(user)
                .build();
    }

    public Drink createDrink(ResultSet rs) throws SQLException {
        int idDrink = rs.getInt(PARAMETER_ID_DRINK);
        String nameDrink = rs.getString(PARAMETER_DRINK);
        String description = rs.getString(PARAMETER_DESCRIPTION);
        String picturePath = rs.getString(PARAMETER_PICTURE_PATH);
        int portion = rs.getInt(PARAMETER_PORTION);
        double price = rs.getDouble(PARAMETER_PRICE);
        return new DrinkBuilderImpl()
                .withIdComponent(idDrink)
                .withNameComponent(nameDrink)
                .withPicturePath(picturePath)
                .withDescription(description)
                .withPortion(portion)
                .withPrice(price)
                .build();
    }


    public Order createOrder(ResultSet rs) throws SQLException, DAOException {
        int idOrder = rs.getInt(PARAMETER_ID_ORDER);
        int idCartUser = rs.getInt(PARAMETER_ID_CART_USER);
        Date dateOrder = rs.getDate(PARAMETER_DATE_ORDER);
        double totalCost = rs.getDouble(PARAMETER_TOTAL_COST);
        CartUser cartUser = cartUserDAO.getCartUser(idCartUser);
        return new OrderBuilderImpl()
                .withIdOrder(idOrder)
                .withCartUser(cartUser)
                .withDateOrder(dateOrder)
                .withDateOrder(dateOrder)
                .withTotalCost(totalCost)
                .build();
    }

    public PaymentMethod createPaymentMethod(ResultSet rs) throws SQLException {
        int idPaymentMethod = rs.getInt(PARAMETER_ID_PAYMENT_METHOD);
        String namePaymentMethod = rs.getString(PARAMETER_NAME_PAYMENT_METHOD);

        return new PaymentMethodBuilderImpl()
                .withidPaymentMethod(idPaymentMethod)
                .withNamePaymentMethod(namePaymentMethod)
                .build();
    }

    public Account createAccount(ResultSet rs) throws SQLException, DAOException {
        int idAccount = rs.getInt(PARAMETER_ID_ACCOUNT);
        int idAccountUser = rs.getInt(PARAMETER_ID_ACCOUNT_USER);
        AccountUser accountUser = accountUserDAO.getAccountUser(idAccountUser);
        int idPaymentMethod = rs.getInt(PARAMETER_ID_PAYMENT_METHOD);
        PaymentMethod paymentMethod = paymentMethodDAO.getPaymentMethod(idPaymentMethod);
        Date paymentDate = rs.getDate(PARAMETER_PAYMENT_DATE);
        int amountOfMoney = rs.getInt(PARAMETER_MONEY);
        return new AccountBuilderImpl()
                .withIdAccount(idAccount)
                .withAccountUser(accountUser)
                .withPaymentMethod(paymentMethod)
                .withPaymentDate(paymentDate)
                .withAmountOfMoney(amountOfMoney)
                .build();
    }
}