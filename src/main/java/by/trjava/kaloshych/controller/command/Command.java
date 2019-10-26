package by.trjava.kaloshych.controller.command;

import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.service.exception.ServiceException;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, DAOException, SQLException;
}