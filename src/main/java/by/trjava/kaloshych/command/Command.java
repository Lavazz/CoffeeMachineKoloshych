package by.trjava.kaloshych.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import  by.trjava.kaloshych.command.exception.CommandException;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}