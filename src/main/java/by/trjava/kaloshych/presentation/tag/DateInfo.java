package by.trjava.kaloshych.presentation.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The class is used to represent the date in the required format.
 *
 * @author Katsiaryna Kaloshych
 * @version 1.0
 * @see java.util.Date
 * @since JDK1.0
 */

public class DateInfo extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final Date date = new Date();
        final String dateNow = dateFormat.format(date);
        final String time = "<b>" + dateNow + " </b>";

        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}