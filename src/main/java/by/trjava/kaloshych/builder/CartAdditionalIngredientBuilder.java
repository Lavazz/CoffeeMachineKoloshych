package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;

public interface CartAdditionalIngredientBuilder {
    CartAdditionalIngredient build();

    CartAdditionalIngredientBuilder withIdCartAdditionalIngredient(int idCartAdditionalIngredient);

    CartAdditionalIngredientBuilder withCart(Cart cart);

    CartAdditionalIngredientBuilder withAdditionalIngredient(AdditionalIngredient additionalIngredient);
}
