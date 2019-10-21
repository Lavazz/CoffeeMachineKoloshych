package by.trjava.coffeemachine.controller.command.impl;//package by.trjava.by.trjava.coffeemachine.controller.command.impl;
//
//import by.trjava.by.trjava.coffeemachine.controller.command.Command;
//import by.trjava.by.trjava.coffeemachine.controller.command.CreatorFullURL;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class NextPageCommand implements Command {
//
//        private static final String PARAMETER_FIRST_ROW = "first_row_parameter_name";
//        private static final String PARAMETER_LAST_ROW = "last_row_parameter_name";
//        private static final String PARAMETER_CURRENT_PAGE = "current_page_URL";
//        private static final String PARAMETER_CURRENT_PAGE_NUMBER = "current_page_number_parameter_name";
//        private static final String PARAMETER_NUMBER_OF_PAGES = "number_of_pages_parameter_name";
//        private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//
//        HttpSession session = request.getSession();
//            String url = CreatorFullURL.create(request);
//
//            String parameterFirstRowName = request.getParameter(PARAMETER_FIRST_ROW);
//            String parameterLastRowName = request.getParameter(PARAMETER_LAST_ROW);
//            String currentPageNumberParameterName = request.getParameter(PARAMETER_CURRENT_PAGE_NUMBER);
//            String numberOfPagesParameterName = request.getParameter(PARAMETER_NUMBER_OF_PAGES);
//
//            int firstIndex = Integer.parseInt(session.getAttribute(parameterLastRowName).toString());
//            int lastIndex = firstIndex + SelectionAllDrinksCommand.rowsByPage;
//            int numberOfPages = Integer.parseInt(session.getAttribute(numberOfPagesParameterName).toString());
//            int currentPageNumber = Integer.parseInt(session.getAttribute(currentPageNumberParameterName).toString());
//
//            if(numberOfPages > currentPageNumber) {
//                session.setAttribute(currentPageNumberParameterName, (++currentPageNumber));
//                session.setAttribute(parameterFirstRowName, firstIndex);
//                session.setAttribute(parameterLastRowName, lastIndex);
//            }
//
//            session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter(PARAMETER_CURRENT_PAGE));
//            dispatcher.forward(request, response);
//
//        }
//
//
//    }
