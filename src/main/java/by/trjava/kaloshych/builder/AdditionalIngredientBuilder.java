package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.AdditionalIngredient;

public interface AdditionalIngredientBuilder {
    AdditionalIngredient build();

    AdditionalIngredientBuilder withNameComponent(String nameComponent);

    AdditionalIngredientBuilder withPortion(int portion);

    AdditionalIngredientBuilder withPicturePath(String picturePath);

    AdditionalIngredientBuilder withCalories(int calories);
}
