package by.trjava.coffeemachine.filter;//package by.trjava.by.trjava.coffeemachine.filter;
//
//import by.trjava.by.trjava.coffeemachine.entity.User;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class AccessFilter implements Filter{
//
//	private static final String ADD_NEW_MOVIE = "addNewMovie";
//    private static final String GO_TO_ADD_NEW_MOVIE = "goToAddNewMovie";
//    private static final String ADD_NEW_RATE = "addNewRate";
//    private static final String GO_TO_ADD_NEW_RATE = "goToAddNewRate";
//    private static final String USER = "user";
//    private static final String USER_ROLE_USER = "USER";
//    private static final String COMMAND = "command";
//    private static final Integer ERROR_NUMBER = 403;
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//
//		String parameter = servletRequest.getParameter(COMMAND);
//		HttpSession session = ((HttpServletRequest) servletRequest).getSession();
//        User user = (User) session.getAttribute(USER);
//
//		if (parameter != null && user != null) {
//
//            if ((parameter.equals(ADD_NEW_MOVIE) ||
//                    parameter.equals(GO_TO_ADD_NEW_MOVIE)) && user.getUserRole().equals(USER_ROLE_USER)) {
//
//                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
//                    return;
//            }
//		} else if(parameter != null && user == null) {
//
//			if (parameter.equals(ADD_NEW_MOVIE) ||
//                    parameter.equals(GO_TO_ADD_NEW_MOVIE) ||
//                    parameter.equals(ADD_NEW_RATE) ||
//                    parameter.equals(GO_TO_ADD_NEW_RATE)) {
//
//                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
//                    return;
//            }
//		}
//
//        filterChain.doFilter(servletRequest, servletResponse);
//	}
//}
