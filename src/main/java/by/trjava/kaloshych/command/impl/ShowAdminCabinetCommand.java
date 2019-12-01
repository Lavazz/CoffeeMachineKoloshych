package by.trjava.kaloshych.command.impl;

import by.trjava.kaloshych.command.Command;
import by.trjava.kaloshych.command.exception.CommandException;
import by.trjava.kaloshych.entity.Component;
import by.trjava.kaloshych.service.FillingOperationService;
import by.trjava.kaloshych.service.ServiceFactory;
import by.trjava.kaloshych.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_COMPONENTS;
import static by.trjava.kaloshych.configuration.PathToJSP.PATH_SHOW_ADMIN_CABINET;

public class ShowAdminCabinetCommand implements Command {

    private final FillingOperationService fillingOperationService = ServiceFactory.getInstance().getFillingOperationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        final HttpSession session = request.getSession();

        List<Component> components;
        try {
            components = fillingOperationService.getAllComponents();
            session.setAttribute(PARAMETER_COMPONENTS, components);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PATH_SHOW_ADMIN_CABINET;
    }
}
