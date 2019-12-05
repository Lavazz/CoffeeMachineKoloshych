package by.trjava.kaloshych.controller.filter;

import by.trjava.kaloshych.command.util.CommandName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static by.trjava.kaloshych.command.configuration.Message.MESSAGE_ACCESS;
import static by.trjava.kaloshych.command.configuration.Parameter.*;
import static by.trjava.kaloshych.command.configuration.PathToJSP.PATH_INDEX;
import static by.trjava.kaloshych.entity.UserStatus.*;


@WebFilter(urlPatterns = {"*"}, servletNames = {"main"})
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    private static final List<String> ADMINISTRATION_COMMANDS = Arrays.asList(
            CommandName.SHOW_ADMIN_CABINET,
            CommandName.ADD_NEW_ADDITIONAL_INGREDIENT,
            CommandName.ADD_NEW_DRINK,
            CommandName.CHANGE_LOCALE,
            CommandName.DELETE_COMPONENT,
            CommandName.EDIT_ORDER,
            CommandName.FILL_TO_MAX,
            CommandName.LOG_OUT,
            CommandName.GO_TO_ADD_NEW_ADDITIONAL_INGREDIENT_PAGE,
            CommandName.GO_TO_ADD_NEW_DRINK_PAGE,
            CommandName.GO_TO_FILLING_OPERATION,
            CommandName.GO_TO_FILLING_OPERATION_PAGE,
            CommandName.GO_TO_DELETE_COMPONENT_PAGE,
            CommandName.GO_TO_INDEX_PAGE,
            CommandName.GO_TO_MAIN_PAGE,
            CommandName.SHOW_ADDITIONAL_INGREDIENTS,
            CommandName.SHOW_DRINKS
    );

    private final static List<String> CUSTOMER_COMMANDS = Arrays.asList(
            CommandName.ADD_ORDER,
            CommandName.ADD_TO_CART,

            CommandName.CHANGE_LOCALE,
            CommandName.CHANGE_PORTION,
            CommandName.CHANGE_PASSWORD,

            CommandName.CANCEL_ORDER,
            CommandName.CLEAN_CART,
            CommandName.DELETE_CART_FROM_ORDER,
            CommandName.PERSONAL_CABINET,
            CommandName.LOG_OUT,
            CommandName.REPLENISH_BALANCE,

            CommandName.SHOW_NEXT_PAGE,
            CommandName.SHOW_PREVIOUS_PAGE,
            CommandName.SHOW_DRINKS,
            CommandName.SHOW_ADDITIONAL_INGREDIENTS,
            CommandName.SHOW_CART,
            CommandName.SHOW_ORDER_HISTORY,
            CommandName.SHOW_REPLENISH_BALANCE_FORM,

            CommandName.GO_TO_CHANGE_PASSWORD_PAGE,
            CommandName.GO_TO_INDEX_PAGE,
            CommandName.GO_TO_MAIN_PAGE,
            CommandName.GO_TO_REPLENISH_BALANCE,
            CommandName.GO_TO_ORDER_HISTORY_PAGE
    );

    private final static List<String> GUEST_COMMANDS = Arrays.asList(
            CommandName.AUTHORIZATION,
            CommandName.REGISTRATION,
            CommandName.CHANGE_LOCALE,
            CommandName.SHOW_DRINKS,
            CommandName.SHOW_ADDITIONAL_INGREDIENTS,
            CommandName.GO_TO_MAIN_PAGE,
            CommandName.GO_TO_INDEX_PAGE,
            CommandName.GO_TO_AUTHORIZATION_PAGE,
            CommandName.GO_TO_REGISTRATION_PAGE
    );


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String command = request.getParameter(PARAMETER_COMMAND);

        if (command != null) {

            int idUserStatus = GUEST.getIdUserStatus();

            if (session.getAttribute(PARAMETER_ID_USER) != null) {
                idUserStatus = (int) session.getAttribute(PARAMETER_ID_USER_STATUS);
            }

            if ((idUserStatus == GUEST.getIdUserStatus() && isGuestCommand(command))
                    || (idUserStatus == ADMIN.getIdUserStatus() && isAdministrationCommand(command))
                    || (idUserStatus == CUSTOMER.getIdUserStatus() && isCustomerCommand(command))) {
                request.setAttribute(PARAMETER_PERMISSION, true);
                System.out.println(" PERMISSION true");
            } else {
                request.setAttribute(PARAMETER_PERMISSION, false);
                request.setAttribute(PARAMETER_PAGE, PATH_INDEX);
                session.setAttribute(PARAMETER_MESSAGE_ACCESS, MESSAGE_ACCESS);
                System.out.println(" PERMISSION false");
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * @param command to be checked if the administration authorised to access it or not
     * @return true if the admin authorised or false if it is not authorised
     */
    private boolean isAdministrationCommand(String command) {
        return ADMINISTRATION_COMMANDS.contains(command);
    }

    /**
     * @param command to be checked if the user authorised to access on of this command or not
     * @return true if the user authorised or false if it is not authorised
     */
    private boolean isCustomerCommand(String command) {
        return CUSTOMER_COMMANDS.contains(command);
    }

    /**
     * @param command that are common for all the users registered or not
     * @return true if it is for all the users or false.
     */
    private boolean isGuestCommand(String command) {
        return GUEST_COMMANDS.contains(command);
    }
}

