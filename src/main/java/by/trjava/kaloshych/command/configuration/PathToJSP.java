package by.trjava.kaloshych.command.configuration;

/**
 * This class contains path to jsp used in Commands
 * some forms are abbreviated as these paths are spelled out in web.xml
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @since JDK1.0
 */
public class PathToJSP {

    // User
    public static final String PATH_COMMAND_ADMIN_CABINET = "/main?command=showAdminCabinet";
    public static final String PATH_AUTHORIZATION = "authorization";
    public static final String PATH_PERSONAL_CABINET = "personalCabinet";
    public static final String PATH_REGISTRATION = "registration";
    public static final String PATH_SHOW_ADMIN_CABINET = "adminCabinet";
    public static final String PATH_COMMAND_PERSONAL_CABINET = "/main?command=personalCabinet";
    public static final String PATH_CHANGE_PASSWORD = "changePassword";

    //Account
    public static final String PATH_REPLENISH_BALANCE = "replenishBalance";
    public static final String PATH_COMMAND_REPLENISH_BALANCE = "/main?command=showReplenishBalanceForm";

    //Cart
    public static final String PATH_COMMAND_SHOW_CART = "/main?command=showCart";
    public static final String PATH_SHOW_CART = "showCart";

    //Components
    public static final String PATH_SHOW_DRINKS = "showDrinks";
    public static final String PATH_SHOW_ADDITIONAL_INGREDIENTS = "showAdditionalIngredients";
    public static final String PATH_ADD_NEW_DRINK = "addNewDrink";
    public static final String PATH_ADD_NEW_ADDITIONAL_INGREDIENT = "addNewAdditionalIngredient";
    public static final String PATH_DELETE_COMPONENT = "deleteComponent";

    //fillingOperation
    public static final String PATH_FILLING_OPERATION = "fillingOperation";

    //Order
    public static final String PATH_TAKE_ORDER = "takeOrder";
    public static final String PATH_ORDER_HISTORY = "orderHistory";

    //Common
    public static final String PATH_ERROR = "error-page";
    public static final String PATH_MAIN_PAGE = "mainPage";

}