package by.trjava.kaloshych.command.configuration;


/**
 * This class contains parameters used in Commands
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class Parameter {

    //User
    public static final String PARAMETER_CONFIRM_PASSWORD = "confirmedPassword";
    public static final String PARAMETER_MESSAGE_REGISTRATION = "messageRegistration";
    public static final String PARAMETER_USER_NAME = "userName";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_ID_USER_STATUS = "idUserStatus";
    public static final String PARAMETER_ID_USER = "idUser";
    public static final String PARAMETER_CURRENT_PASSWORD = "currentPassword";
    public static final String PARAMETER_NEW_PASSWORD = "newPassword";
    public static final String PARAMETER_CONFIRMED_NEW_PASSWORD = "confirmedPassword";
    public static final String PARAMETER_MESSAGE_CHANGE_PASSWORD = "messageChangePassword";

    //Account
    public static final String PARAMETER_MESSAGE_BALANCE = "messageBalance";
    public static final String PARAMETER_ID_ACCOUNT_USER = "idAccountUser";
    public static final String PARAMETER_BALANCE = "balance";
    public static final String PARAMETER_MESSAGE_REPLENISH = "replenishBalanceMessage";
    public static final String PARAMETER_AMOUNT_OF_MONEY = "amountOfMoney";

    //Cart
    public static final String PARAMETER_SIGN = "sign";
    public static final String PARAMETER_MESSAGE_CART = "messageCart";
    public static final String PARAMETER_CARTS = "carts";
    public static final String PARAMETER_CART_ADDITIONAL_INGREDIENTS = "cartAdditionalIngredients";
    public static final String PARAMETER_ID_CART = "idCart";
    public static final String PARAMETER_ID_CART_USER = "idCartUser";

    //Component
    public static final String PARAMETER_NAME_COMPONENT = "nameComponent";
    public static final String PARAMETER_CALORIES = "calories";
    public static final String PARAMETER_TOTAL_COST = "totalCost";
    public static final String PARAMETER_DRINK_RADIO_BUTTON = "radioButtonDrink";
    public static final String PARAMETER_MESSAGE_DRINKS = "messageDrinks";
    public static final String PARAMETER_MESSAGE_ADD_AI = "messageAddAdditionalIngredient";
    public static final String PARAMETER_MESSAGE_ADD_DRINK = "messageAddDrink";
    public static final String PARAMETER_ID_COMPONENTS = "idComponents";
    public static final String PARAMETER_COMPONENTS = "components";
    public static final String PARAMETER_PRICE = "price";
    public static final String PARAMETER_DESCRIPTION = "description";
    public static final String PARAMETER_ID_ADDITIONAL_INGREDIENT = "idAdditionalIngredient";
    public static final String PARAMETER_ADDITIONAL_INGREDIENTS = "additionalIngredients";
    public static final String PARAMETER_DRINKS = "drinks";

    //PaymentMethod
    public static final String PARAMETER_PAYMENT_METHODS = "paymentMethods";
    public static final String PARAMETER_RADIO_PAYMENT_METHOD = "radioIdPaymentMethod";

    //FillingOperation
    public static final String PARAMETER_MESSAGE_FILLING = "fillingMessage";

    //Order
    public static final String PARAMETER_MESSAGE_ORDER = "messageOrder";
    public static final String PARAMETER_ORDERS = "orders";
    public static final String PARAMETER_ORDERS_FIRST_ROW = "ordersFirstRow";
    public static final String PARAMETER_ORDERS_LAST_ROW = "ordersLastRow";
    public static final String PARAMETER_ORDERS_NUMBER_OF_PAGES = "ordersNumberOfPages";
    public static final String PARAMETER_ORDERS_CURRENT_PAGE_NUMBER = "ordersCurrentPageNumber";

    //Common
    public static final String PARAMETER_REFERER = "referer";
    public static final String PARAMETER_SESSION_LOCALE = "locale";
    public static final String PARAMETER_MESSAGE_DELETE = "messageDelete";
    public static final String PARAMETER_WRONG_MESSAGE = "wrongMessage";
    public static final String PARAMETER_MAIN_MESSAGE = "mainMessage";
    public static final String PARAMETER_COMMAND = "command";
    public static final String PARAMETER_PERMISSION = "permission";
    public static final String PARAMETER_PAGE = "page";
    public static final String PARAMETER_MESSAGE_ACCESS = "messageAccess";
    public static final String PARAMETER_FIRST_ROW = "firstRow";
    public static final String PARAMETER_LAST_ROW = "lastRow";
    public static final String PARAMETER_CURRENT_PAGE_NUMBER = "currentPageNumber";
    public static final String PARAMETER_NUMBER_OF_PAGES = "numberOfPages";
    public static final String PARAMETER_PREVIOUS_REQUEST = "prevRequest";
    public static final String PARAMETER_NEXT_REQUEST = "nextRequest";
    public static final String REDIRECT_COMMAND = "redirectToCommand";

    public static final boolean PARAMETER_TRUE = true;
    public static final boolean PARAMETER_FALSE = false;

    public static final int ROWS_BY_PAGE = 5;
    public static final int FIRST_PAGE_NUMBER = 1;
    public static final int DEFAULT_NUMBER_OF_PAGE = 1;


}
