package by.trjava.kaloshych.command.factory.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.impl.*;
import by.trjava.kaloshych.command.impl.components.AddNewDrinkCommand;
import by.trjava.kaloshych.command.impl.components.ShowDrinksCommand;
import by.trjava.kaloshych.command.impl.order.AddOrderCommand;
import by.trjava.kaloshych.command.impl.user.AuthorizationCommand;
import by.trjava.kaloshych.command.impl.user.ChangePasswordCommand;
import by.trjava.kaloshych.command.impl.user.RegistrationCommand;
import org.junit.Test;

import static by.trjava.kaloshych.command.util.CommandName.*;
import static org.junit.Assert.*;

public class CommandFactoryImplTest {

    private CommandFactory factory = CommandFactoryImpl.getInstance();


    @Test
            (expected = CommandException.class)
    public void testCreateCommandInvalidCommand() throws CommandException {
        factory.createCommand("noSuchCommand");
    }

    @Test
    public void testCreateCommandAuthorizationCommand() throws CommandException {
        Command command = factory.createCommand(AUTHORIZATION);

        assertTrue(command instanceof AuthorizationCommand);
    }

    @Test
    public void testCreateCommandRegistrationCommand() throws CommandException {
        Command command = factory.createCommand(REGISTRATION);

        assertTrue(command instanceof RegistrationCommand);
    }


    @Test
    public void testCreateCommandChangePasswordCommand() throws CommandException {
        Command command = factory.createCommand(CHANGE_PASSWORD);

        assertTrue(command instanceof ChangePasswordCommand);
    }

    @Test
    public void testCreateCommandAddNewDrink() throws CommandException {
        Command command = factory.createCommand(ADD_NEW_DRINK);

        assertTrue(command instanceof AddNewDrinkCommand);
    }

    @Test
    public void testCreateCommandAddOrder() throws CommandException {
        Command command = factory.createCommand(ADD_ORDER);

        assertTrue(command instanceof AddOrderCommand);
    }


    @Test
    public void createCommandShowDrinks() throws CommandException {
        Command command = factory.createCommand(SHOW_DRINKS);

        assertTrue(command instanceof ShowDrinksCommand);
    }
}
