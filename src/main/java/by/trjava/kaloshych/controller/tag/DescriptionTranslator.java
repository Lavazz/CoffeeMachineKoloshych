package by.trjava.kaloshych.controller.tag;

import by.trjava.kaloshych.entity.Drink;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static by.trjava.kaloshych.configuration.Parameter.PARAMETER_SESSION_LOCALE;

public class DescriptionTranslator extends TagSupport {
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
                switch (drink.getDescription()) {
                    case "Горячий кофейный напиток, приготавливаемый путём вливания в молоко кофе-эспрессо в пропорции 3:1. " +
                            "Колорийность 29ккал":
                        out.write("Hot coffee drink prepared by pouring espresso coffee into milk in a ratio of 3:1." +
                                " Calories 29 kcal");
                        break;
                    case "Чёрный кофе, приготовленный в специальной кофеварке, " +
                            "где сквозь спрессованные молотые зёрна пропускают горячую воду под давлением. Калорийность 10ккал":
                        out.write("Black coffee, prepared in a special coffee maker, " +
                                "where hot water is passed through the compressed ground grains under pressure. Calories 10 kcal");
                        break;
                    case "Чёрный кофе, приготовленный в специальной кофеварке, где сквозь спрессованные молотые" +
                            " зёрна пропускают горячую воду под давлением. Концентрированее, чем американо. Калорийность 10ккал. ":
                        out.write("Black coffee, prepared in a special coffee maker, where hot water is passed " +
                                "through the compressed ground grains under pressure. More concentrated than the us. Calories 10 kcal.");
                        break;
                    case "Итальянский кофейный напиток содержит кофе с молоком в равных количествах и густой молочной пеной." +
                            " Каллорийность 40ккал":
                        out.write("Italian coffee drink contains coffee with milk in equal quantities and thick milk foam." +
                                " Calories 40 kcal");
                        break;
                    case "Кофейный напиток, на основе ароматизатора \"Ирландского виски\", " +
                            "чёрного кофе, взбитых сливок и коричневого сахара. Колорийность 150ккал.":
                        out.write("Coffee drink, based on the flavor of \"Irish whiskey\", black coffee, " +
                                "whipped cream and brown sugar. The calorie content of 150 calories.");
                        break;
                    case "Кофе со взбитыми сливками.  Калорийность 175 ккал":
                        out.write("Coffee with whipped cream.  Calories 175 kcal");
                        break;
                    case "Напиток, в состав которого обязательно входит какао, а также молоко и сахар. калорийность 77ккал":
                        out.write("Drink, which necessarily includes cocoa, as well as milk and sugar. calories 77 kcal");
                        break;
                    case "Чай из сочного букета ягод и фруктов. Калорийность 2 ккал.":
                        out.write("Tea from a juicy bouquet of berries and fruits. The caloric value of 2 kcal.");
                        break;
                    default:
                        out.write(drink.getDescription());
                        break;
                }
            } else {
                out.write(drink.getDescription());
            }
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}


