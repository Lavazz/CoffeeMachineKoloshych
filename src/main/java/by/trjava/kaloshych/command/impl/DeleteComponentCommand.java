package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.service.ComponentService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.trjava.kaloshych.configuration.Message.MESSAGE_EMPTY_CHECKBOX;
import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_ID_COMPONENTS;
import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_MESSAGE_DELETE;
import static by.trjava.kaloshych.configuration.PathToJSP.*;

public class DeleteComponentCommand implements Command {

   private final ComponentService componentService=ServiceFactory.getInstance().getComponentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
       String path=request.getContextPath()+PATH_COMMAND_ADMIN_CABINET;

        try {
            String[] idComponents = request.getParameterValues(PARAMETER_ID_COMPONENTS);
            componentService.deleteComponent(idComponents);
        } catch (EmptyDataException e) {
            request.setAttribute(PARAMETER_MESSAGE_DELETE, MESSAGE_EMPTY_CHECKBOX);
            path=PATH_DELETE_COMPONENT;
        } catch (ServiceException e) {
            throw  new CommandException(e);
        }

        return  path;
    }
}
