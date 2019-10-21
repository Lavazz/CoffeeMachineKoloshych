package by.trjava.coffeemachine.service.validation;

public class DrinkValidator {
    private static final String DRINK_LATTE_MACCHIATO="Латте макиато";
    private static final String DRINK_AMERICANO="Американо";
    private static final String DRINK_ESPRESSO="Эспрессо";
    private static final String DRINK_CAPPUCCINO="Капучино";
    private static final String DRINK_IRISH_COFFEE="Ирландский кофе";
    private static final String DRINK_VENSKY_COFFEE="Венский кофе";
    private static final String DRINK_HOT_CHOCOLATE="Горячий шоколад";
    private static final String DRINK_FRUIT_TEA="Фруктовый чай";

    private static final DrinkValidator instance=new DrinkValidator();
    private DrinkValidator(){}

    public  boolean validate(String drinkName){
        return drinkName.equalsIgnoreCase(DRINK_LATTE_MACCHIATO)
                &&drinkName.equalsIgnoreCase(DRINK_AMERICANO)
                &&drinkName.equalsIgnoreCase(DRINK_ESPRESSO)
                &&drinkName.equalsIgnoreCase(DRINK_CAPPUCCINO)
                &&drinkName.equalsIgnoreCase(DRINK_IRISH_COFFEE)
                &&drinkName.equalsIgnoreCase(DRINK_VENSKY_COFFEE)
                &&drinkName.equalsIgnoreCase(DRINK_HOT_CHOCOLATE)
                &&drinkName.equalsIgnoreCase(DRINK_FRUIT_TEA);
    }

    public boolean validateIdDrink(int idDrink){
        return idDrink>0&&idDrink<50;
    }

    public static DrinkValidator getInstance(){
        return instance;
    }
}
