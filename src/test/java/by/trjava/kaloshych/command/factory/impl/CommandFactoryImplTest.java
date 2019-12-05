package by.trjava.kaloshych.command.factory.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.command.factory.CommandFactory;
import by.trjava.kaloshych.command.impl.AddNewDrinkCommand;
import by.trjava.kaloshych.command.impl.AddOrderCommand;
import by.trjava.kaloshych.command.impl.AuthorizationCommand;
import by.trjava.kaloshych.command.impl.ShowDrinksCommand;
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
