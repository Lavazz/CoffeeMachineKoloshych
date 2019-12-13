package by.trjava.kaloshych.command.configuration;

/**
 * This class contains messages from the command
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class Message {

    //User
    public static final String MESSAGE_WRONG_AUTHORIZATION = "message.unsuccessful_authorization";
    public static final String MESSAGE_SUCCESSFUL_AUTHORIZATION = "message.successful_authorization";
    public static final String MESSAGE_LOGIN_USED = "message.login_used";
    public static final String MESSAGE_NOT_MATH = "message.passwords_not-match";
    public static final String MESSAGE_UNSUCCESSFUL_REGISTRATION = "message.unsuccessful_registration";
    public static final String MESSAGE_SUCCESSFUL_REGISTRATION = "message.successful_registration";
    public static final String MESSAGE_ACCESS = "message.not_permission";
    public static final String MESSAGE_INVALID_LOGIN = "message.authorization.invalid_login";
    public static final String MESSAGE_INVALID_PASSWORD = "message.authorization.invalid_password";
    public static final String MESSAGE_CHANGE_PASSWORD_SUCCESSFUL = "message.password_changed";
    public static final String MESSAGE_CHANGE_PASSWORD_INVALID = "message.invalid_password";

    //Account
    public static final String MESSAGE_INSUFFICIENT_MONEY = "message.order.insufficient_money";
    public static final String MESSAGE_SMALL_AMOUNT = "message.balance.small_amount";

    //Cart
    public static final String MESSAGE_DRINK_INSUFFICIENT_PORTION = "message.cart_drink.insufficient_portion";
    public static final String MESSAGE_ADDITIONAL_INGREDIENT_INSUFFICIENT_PORTION = "message.cart_additional_ingredient.insufficient_portion";

    //Component
    public static final String MESSAGE_EMPTY_DRINK = "message.empty_drink";
    public static final String MESSAGE_WRONG_PORTION = "message.cart.wrong_portion";
    public static final String MESSAGE_DUPLICATE = "message.duplicate";

    //Order
    public static final String MESSAGE_SUCCESSFUL_ADD_ORDER = "message.order.successful";

    //Common
    public static final String MESSAGE_EMPTY_DATA = "message.empty_data";
    public static final String MESSAGE_EMPTY_CHECKBOX = "message.cart.wrong_checkbox";
    public static final String MESSAGE_INCORRECT_DATA = "message.incorrect_data";
    public static final String MESSAGE_ACCESS_PROHIBITED = "message.access_prohibited";

}
