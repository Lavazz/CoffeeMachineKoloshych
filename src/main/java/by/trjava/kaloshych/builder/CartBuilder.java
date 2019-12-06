package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;

public interface CartBuilder {
    Cart build();

    CartBuilder withCartUser(CartUser cartUser);

    CartBuilder withDrink(Drink drink);

    CartBuilder withPortion(int portion);
}
