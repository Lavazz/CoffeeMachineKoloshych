package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.Drink;

public interface DrinkBuilder {
    Drink build();

    DrinkBuilder withPortion(int portion);

    DrinkBuilder withPicturePath(String picturePath);

    DrinkBuilder withPrice(double price);

    DrinkBuilder withNameComponent(String nameComponent);

    DrinkBuilder withDescription(String description);
}
