package by.trjava.kaloshych.command.factory.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.impl.*;
import by.trjava.kaloshych.command.impl.transition.*;

import static by.trjava.kaloshych.command.util.CommandName.*;

public class CommandFactoryImpl implements CommandFactory {
    private static final CommandFactoryImpl instance = new CommandFactoryImpl();

    public static CommandFactoryImpl getInstance() {
        return instance;
    }

    private CommandFactoryImpl() {

    }

    @Override
    public Command createCommand(String commandName)
            throws CommandException {
        switch (commandName) {
            case AUTHORIZATION:
                return new AuthorizationCommand();
            case CANCEL_ORDER:
                return new CancelOrderCommand();
            case SHOW_DRINKS:
                return new ShowDrinksCommand();
            case CHANGE_LOCALE:
                return new ChangeLocaleCommand();
            case CHANGE_PORTION:
                return new ChangePortionCommand();
            case CHANGE_PASSWORD:
                return new ChangePasswordCommand();
            case REGISTRATION:
                return new RegistrationCommand();
            case LOG_OUT:
                return new LogOutCommand();
            case GO_TO_REGISTRATION_PAGE:
                return new GoToRegistrationPageCommand();
            case SHOW_ADMIN_CABINET:
                return new ShowAdminCabinetCommand();
            case SHOW_ORDER_HISTORY:
                return new ShowOrderHistoryCommand();
            case SHOW_REPLENISH_BALANCE_FORM:
                return new ShowReplenishBalanceFormCommand();
            case GO_TO_AUTHORIZATION_PAGE:
                return new GoToAuthorizationPageCommand();
            case GO_TO_REPLENISH_BALANCE:
                return new GoToReplenishBalancePageCommand();
            case GO_TO_MAIN_PAGE:
                return new GoToMainPageCommand();
            case GO_TO_CHANGE_PASSWORD_PAGE:
                return new GoToChangePasswordPageCommand();
            case GO_TO_ADD_NEW_ADDITIONAL_INGREDIENT_PAGE:
                return new GoToAddNewAdditionalIngredientPage();
            case GO_TO_ADD_NEW_DRINK_PAGE:
                return new GoToAddNewDrinkPageCommand();
            case GO_TO_FILLING_OPERATION_PAGE:
                return new GoToFillingOperationPageCommand();
            case GO_TO_DELETE_COMPONENT_PAGE:
                return new GoToDeleteComponentPageCommand();
            case PERSONAL_CABINET:
                return new PersonalCabinetCommand();
            case ADD_ORDER:
                return new AddOrderCommand();
            case ADD_TO_CART:
                return new AddToCartCommand();
            case FILL_TO_MAX:
                return new FillToMaxCommand();
            case ADD_NEW_DRINK:
                return new AddNewDrinkCommand();
            case REPLENISH_BALANCE:
                return new ReplenishBalanceCommand();
            case SHOW_CART:
                return new ShowCartCommand();
            case DELETE_COMPONENT:
                return new DeleteComponentCommand();
            case SHOW_ADDITIONAL_INGREDIENTS:
                return new ShowAdditionalIngredientsCommand();
            case ADD_NEW_ADDITIONAL_INGREDIENT:
                return new AddNewAdditionalIngredientCommand();
            case DELETE_CART_FROM_ORDER:
                return new DeleteCartFromOrderCommand();
            case CLEAN_CART:
                return new CleanCartCommand();
            case SHOW_NEXT_PAGE:
                return new ShowNextPageCommand();
            case SHOW_PREVIOUS_PAGE:
                return new ShowPreviousPageCommand();

        }

        throw new CommandException("No command with name " + commandName);
    }
}
