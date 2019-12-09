package by.trjava.kaloshych.command.util;

/**
 * final class with all commands from jsp
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see by.trjava.kaloshych.command.Command
 * @since JDK1.0
 */

public class CommandName {
    private CommandName() {
    }

    public static final String ADD_ORDER = "addOrder";
    public static final String ADD_NEW_DRINK = "addNewDrink";
    public static final String ADD_NEW_ADDITIONAL_INGREDIENT = "addNewAdditionalIngredient";
    public static final String ADD_TO_CART = "addToCart";

    public static final String DELETE_CART_FROM_ORDER = "deleteCartFromOrder";
    public static final String DELETE_COMPONENT = "deleteComponent";
    public static final String CANCEL_ORDER = "cancelOrder";
    public static final String CLEAN_CART = "cleanCart";

    public static final String CHANGE_LOCALE = "changeLocale";
    public static final String CHANGE_PORTION = "changePortion";
    public static final String CHANGE_PASSWORD = "changePassword";

    public static final String REPLENISH_BALANCE = "replenishBalance";
    public static final String EDIT_ORDER = "editOrder";

    public static final String FILL_TO_MAX = "fillToMax";

    public static final String REGISTRATION = "registration";
    public static final String LOG_OUT = "logOut";
    public static final String AUTHORIZATION = "authorization";

    public static final String PERSONAL_CABINET = "personalCabinet";

    public static final String GO_TO_EDIT_COMPONENT_PAGE = "goToEditComponentPage";
    public static final String GO_TO_FILLING_OPERATION_PAGE = "goToFillingOperationPage";
    public static final String GO_TO_FILLING_OPERATION = "fillingOperation";
    public static final String GO_TO_REGISTRATION_PAGE = "goToRegistrationPage";
    public static final String GO_TO_AUTHORIZATION_PAGE = "goToAuthorizationPage";
    public static final String GO_TO_INDEX_PAGE = "goToIndexPage";
    public static final String GO_TO_MAIN_PAGE = "goToMainPage";
    public static final String GO_TO_REPLENISH_BALANCE = "goToReplenishBalancePage";
    public static final String GO_TO_DELETE_COMPONENT_PAGE = "goToDeleteComponentPage";
    public static final String GO_TO_ADD_NEW_DRINK_PAGE = "goToAddNewDrinkPage";
    public static final String GO_TO_ADD_NEW_ADDITIONAL_INGREDIENT_PAGE = "goToAddNewAdditionalIngredientPage";
    public static final String GO_TO_ORDER_HISTORY_PAGE = "goToOrderHistoryPage";
    public static final String GO_TO_CHANGE_PASSWORD_PAGE = "goToChangePasswordPage";

    public static final String SHOW_REPLENISH_BALANCE_FORM = "showReplenishBalanceForm";
    public static final String SHOW_ADMIN_CABINET = "showAdminCabinet";
    public static final String SHOW_DRINKS = "showDrinks";
    public static final String SHOW_ORDER_HISTORY = "showOrderHistory";
    public static final String SHOW_ADDITIONAL_INGREDIENTS = "showAdditionalIngredients";
    public static final String SHOW_CART = "showCart";
    public static final String SHOW_NEXT_PAGE = "showNextPage";
    public static final String SHOW_PREVIOUS_PAGE = "showPreviousPage";
}
