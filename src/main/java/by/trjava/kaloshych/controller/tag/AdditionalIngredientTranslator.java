package by.trjava.kaloshych.controller.tag;

import by.trjava.kaloshych.entity.AdditionalIngredient;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_SESSION_LOCALE;

public class AdditionalIngredientTranslator extends TagSupport {
    private static final long serialVersionUID = 1L;
    private AdditionalIngredient additionalIngredient;

    public void setAdditionalIngredient(AdditionalIngredient additionalIngredient) {
        this.additionalIngredient = additionalIngredient;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String language = String.valueOf(pageContext.getSession().getAttribute(PARAMETER_SESSION_LOCALE));
        try {
            if (language.equals("en")) {
                switch (additionalIngredient.getNameComponent()) {
                    case "Сахар":
                        out.write("Sugar");
                        break;
                    case "Сливки":
                        out.write("Cream");
                        break;
                    case "Шоколадная крошка":
                        out.write("Chocolate chip");
                        break;
                    case "Капучино":
                        out.write("Cappuccino");
                        break;
                    default:
                        out.write(additionalIngredient.getNameComponent());
                        break;
                }
            } else {
                out.write(additionalIngredient.getNameComponent());
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
