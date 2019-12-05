package by.trjava.kaloshych.presentation.tag;

import by.trjava.kaloshych.entity.Drink;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

import static by.trjava.kaloshych.command.configuration.Parameter.PARAMETER_SESSION_LOCALE;

public class DrinkTranslator extends TagSupport {
    private static final long serialVersionUID = 1L;
    private Drink drink;

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String language = String.valueOf(pageContext.getSession().getAttribute(PARAMETER_SESSION_LOCALE));
        try {
            if (language.equals("en")) {
                switch (drink.getNameComponent()) {
                    case "Латте макиато":
                        out.write("Latte macchiato");
                        break;
                    case "Американо":
                        out.write("Americano");
                        break;
                    case "Эспрессо":
                        out.write("Espresso");
                        break;
                    case "Капучино":
                        out.write("Cappuccino");
                        break;
                    case "Ирландский кофе":
                        out.write("Irish coffee");
                        break;
                    case "Венский кофе":
                        out.write("Coffee vensky");
                        break;
                    case "Горячий шоколад":
                        out.write("Hot chocolate");
                        break;
                    case "Чай фруктовый":
                        out.write("Tea fruity");
                        break;
                    default:
                        out.write(drink.getNameComponent());
                        break;
                }
            } else {
                out.write(drink.getNameComponent());
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}

