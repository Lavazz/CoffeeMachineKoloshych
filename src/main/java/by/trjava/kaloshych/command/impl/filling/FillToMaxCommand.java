package by.trjava.kaloshych.command.impl.filling;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.EmptyDataException;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.trjava.kaloshych.command.configuration.Message.MESSAGE_EMPTY_CHECKBOX;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_COMMAND_ADMIN_CABINET;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_FILLING_OPERATION;

public class FillToMaxCommand implements Command {

    private final FillingOperationService fillingOperationService = ServiceFactory.getInstance().getFillingOperationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String[] idComponents = request.getParameterValues(PARAMETER_ID_COMPONENTS);
        String path = request.getContextPath() + PATH_COMMAND_ADMIN_CABINET;
        try {
            fillingOperationService.fillingOperation(idComponents);
            List<Component> components = fillingOperationService.getAllComponents();
            request.setAttribute(PARAMETER_COMPONENTS, components);
        } catch (EmptyDataException e) {
            request.setAttribute(PARAMETER_MESSAGE_FILLING, MESSAGE_EMPTY_CHECKBOX);
            path = PATH_FILLING_OPERATION;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return path;
    }
}


