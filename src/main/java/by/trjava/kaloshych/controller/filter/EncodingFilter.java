package by.trjava.kaloshych.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Filter is used to sets encoding for pages in UTF-8.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see Filter
 * @since JDK1.0
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestEncoding = servletRequest.getCharacterEncoding();
        if (!encoding.equals(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}