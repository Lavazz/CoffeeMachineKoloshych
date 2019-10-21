package by.trjava.coffeemachine.controller.command;

import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException;
}