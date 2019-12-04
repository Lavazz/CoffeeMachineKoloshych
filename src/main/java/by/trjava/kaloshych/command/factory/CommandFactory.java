package by.trjava.kaloshych.command.factory;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandFactory {

    Command createCommand(String commandName) throws CommandException;
}
