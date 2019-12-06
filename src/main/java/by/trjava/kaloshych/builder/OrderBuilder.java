package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Order;

import java.util.Date;

public interface OrderBuilder {
    Order build();

    OrderBuilder withDateOrder(Date dateOrder);

    OrderBuilder withCartUser(CartUser cartUser);

    OrderBuilder withTotalCost(double totalCost);
}
