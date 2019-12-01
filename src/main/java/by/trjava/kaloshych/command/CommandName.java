package by.trjava.kaloshych.command;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum CommandName {

    ADD_ORDER("addOrder"),
    ADD_NEW_DRINK("addNewDrink"),
    ADD_NEW_ADDITIONAL_INGREDIENT("addNewAdditionalIngredient"),
    ADD_TO_CART("addToCart"),

    DELETE_CART_FROM_ORDER("deleteCartFromOrder"),
    DELETE_COMPONENT("deleteComponent"),
    CANCEL_ORDER("cancelOrder"),

    CHANGE_LOCALE("changeLocale"),
    CHANGE_PORTION("changePortion"),
    CHANGE_PASSWORD("changePassword"),

    REPLENISH_BALANCE("replenishBalance"),
    EDIT_ORDER("editOrder"),
    NO_SUCH_COMMAND("noSuchCommand"),

    FILL_TO_MAX("fillToMax"),
    CLEAN_CART("cleanCart"),

    REGISTRATION("registration"),
    LOG_OUT("logOut"),
    AUTHORIZATION("authorization"),

    PERSONAL_CABINET("personalCabinet"),

    GO_TO_EDIT_COMPONENT_PAGE("goToEditComponentPage"),
    GO_TO_FILLING_OPERATION_PAGE("goToFillingOperationPage"),
    GO_TO_FILLING_OPERATION("fillingOperation"),
    GO_TO_REGISTRATION_PAGE("goToRegistrationPage"),
    GO_TO_AUTHORIZATION_PAGE("goToAuthorizationPage"),
    GO_TO_INDEX_PAGE("goToIndexPage"),
    GO_TO_MAIN_PAGE("goToMainPage"),
    GO_TO_REPLENISH_BALANCE("goToReplenishBalancePage"),
    GO_TO_DELETE_COMPONENT_PAGE("goToDeleteComponentPage"),
    GO_TO_ADD_NEW_DRINK_PAGE("goToAddNewDrinkPage"),
    GO_TO_ADD_NEW_ADDITIONAL_INGREDIENT_PAGE("goToAddNewAdditionalIngredientPage"),
    GO_TO_ORDER_HISTORY_PAGE("goToOrderHistoryPage"),
    GO_TO_CHANGE_PASSWORD_PAGE("goToChangePasswordPage"),

    SHOW_REPLENISH_BALANCE_FORM("showReplenishBalanceForm"),
    SHOW_ADMIN_CABINET("showAdminCabinet"),
    SHOW_DRINKS("showDrinks"),
    SHOW_ORDER_HISTORY("showOrderHistory"),
    SHOW_ADDITIONAL_INGREDIENTS("showAdditionalIngredients"),
    SHOW_CART("showCart"),
    SHOW_NEXT_PAGE("showNextPage"),
 SHOW_PREVIOUS_PAGE("showPreviousPage");

    private String name;
    private static final Map<String, CommandName> ENUM_MAP;

    CommandName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static {
        Map<String, CommandName> map = new ConcurrentHashMap<>();
        for (CommandName instance : CommandName.values()) {
            map.put(instance.getName(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static CommandName get(String name) {
        return ENUM_MAP.get(name);
    }
}
