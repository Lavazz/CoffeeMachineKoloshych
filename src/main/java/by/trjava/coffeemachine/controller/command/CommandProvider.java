package by.trjava.coffeemachine.controller.command;

import by.trjava.coffeemachine.controller.command.impl.*;
import by.trjava.coffeemachine.controller.command.impl.gotopage.GoToLoginPageCommand;
import by.trjava.coffeemachine.controller.command.impl.gotopage.GoToMakeOrderCommand;
import by.trjava.coffeemachine.controller.command.impl.gotopage.GoToRegistrationPageCommand;
import by.trjava.coffeemachine.controller.command.impl.gotopage.GoToShowAllDrinksPageCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.GOTOMAKEORDERCOMMAND, new GoToMakeOrderCommand());
        commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPageCommand());
        commands.put(CommandName.CHANGELOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.UPDATEPASSWORD, new UpdatePasswordCommand());
        commands.put(CommandName.SHOWALLDRINKS, new SelectionAllDrinksCommand());
//        commands.put(CommandName.SHOWNEXTPAGE, new NextPageCommand());
//        commands.put(CommandName.SHOWPREVIOUSPAGE, new PreviousPageCommand());
        commands.put(CommandName.GOTOSHOWALLDRINKSPAGECOMMAND, new GoToShowAllDrinksPageCommand());
        commands.put(CommandName.CREATEACCOUNT, new CreateAccountCommand());
        commands.put(CommandName.INCREASEBALANCE, new IncreaseBalanceCommand());
      //  commands.put(CommandName.ADDITIONALINGREDIENT, new AdditionalIngredientCommand());
        commands.put(CommandName.LOGOUTCOMMAND, new LogOutCommand());
   //     commands.put(CommandName.GETDRINK, new GetDrinkCommand());
        commands.put(CommandName.DELETEORDER, new LogOutCommand());
        commands.put(CommandName.LOGOUT, new LogOutCommand());
        commands.put(CommandName.MAKEORDER, new MakeOrderCommand());
        commands.put(CommandName.NOSUCHCOMMAND, new NoSuchCommand());
        commands.put(CommandName.PERSONALCABINET, new PersonalCabinetCommand());
      //  commands.put(CommandName.INCREASEBALANCE, new IncreaseBalanceCommand());
        commands.put(CommandName. ORDERJOURNAL, new OrderJournalCommand());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {

        CommandName name;
        Command command;

            name = CommandName.valueOf(commandName.toUpperCase());
            if(name!=null){
            command = commands.get(name);
        } else  {
            command = commands.get(CommandName.NOSUCHCOMMAND);
        }

        return command;
    }
}