package by.trjava.kaloshych.command;

import by.trjava.kaloshych.command.impl.*;
import by.trjava.kaloshych.command.impl.transition.*;


import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandName.SHOW_DRINKS, new ShowDrinksCommand());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.CHANGE_PORTION, new ChangePortionCommand());
        commands.put(CommandName.CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.SHOW_ADMIN_CABINET, new ShowAdminCabinetCommand());
        commands.put(CommandName.SHOW_ORDER_HISTORY, new ShowOrderHistoryCommand());
        commands.put(CommandName.SHOW_REPLENISH_BALANCE_FORM, new ShowReplenishBalanceFormCommand());
        commands.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPageCommand());
        commands.put(CommandName.GO_TO_REPLENISH_BALANCE, new GoToReplenishBalancePageCommand());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.GO_TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
        commands.put(CommandName.GO_TO_ADD_NEW_ADDITIONAL_INGREDIENT_PAGE, new GoToAddNewAdditionalIngredientPage());
        commands.put(CommandName.GO_TO_ADD_NEW_DRINK_PAGE, new GoToAddNewDrinkPageCommand());
        commands.put(CommandName.GO_TO_DELETE_COMPONENT_PAGE, new GoToDeleteComponentPageCommand());
        commands.put(CommandName.PERSONAL_CABINET, new PersonalCabinetCommand());
        commands.put(CommandName.GO_TO_FILLING_OPERATION_PAGE, new GoToFillingOperationPageCommand());
        commands.put(CommandName.FILL_TO_MAX, new FillToMaxCommand());
        commands.put(CommandName.ADD_TO_CART, new AddToCartCommand());
        commands.put(CommandName.ADD_ORDER, new AddOrderCommand());
        commands.put(CommandName.SHOW_CART, new ShowCartCommand());
        commands.put(CommandName.REPLENISH_BALANCE, new ReplenishBalanceCommand());
        commands.put(CommandName.ADD_NEW_DRINK, new AddNewDrinkCommand());
        commands.put(CommandName.ADD_NEW_ADDITIONAL_INGREDIENT, new AddNewAdditionalIngredientCommand());
        commands.put(CommandName.SHOW_ADDITIONAL_INGREDIENTS, new ShowAdditionalIngredientsCommand());
        commands.put(CommandName.DELETE_COMPONENT, new DeleteComponentCommand());
        commands.put(CommandName.DELETE_CART_FROM_ORDER, new DeleteCartFromOrderCommand());
        commands.put(CommandName.CLEAN_CART, new CleanCartCommand());
        commands.put(CommandName.SHOW_NEXT_PAGE, new ShowNextPageCommand());
        commands.put(CommandName.SHOW_PREVIOUS_PAGE, new ShowPreviousPageCommand());


    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        Command command;

        CommandName name = CommandName.get(commandName);
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }

        return command;
    }
}