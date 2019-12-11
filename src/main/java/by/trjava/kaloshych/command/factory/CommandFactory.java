package by.trjava.kaloshych.command.factory;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

public interface CommandFactory {

    Command createCommand(String commandName) throws CommandException;
}
