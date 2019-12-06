package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.CartAdditionalIngredientBuilder;
import by.trjava.kaloshych.entity.AdditionalIngredient;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartAdditionalIngredient;

public class CartAdditionalIngredientBuilderImpl implements CartAdditionalIngredientBuilder {

    private int idCartAdditionalIngredient;
    private Cart cart;
    private AdditionalIngredient additionalIngredient;

    public CartAdditionalIngredientBuilderImpl(){}

    public CartAdditionalIngredientBuilderImpl(int idCartAdditionalIngredient){
        this.idCartAdditionalIngredient=idCartAdditionalIngredient;
    }

    @Override
    public CartAdditionalIngredient build(){
        CartAdditionalIngredient cartAdditionalIngredient=new CartAdditionalIngredient();
        cartAdditionalIngredient.setIdCartAdditionalIngredient(idCartAdditionalIngredient);
        cartAdditionalIngredient.setCart(cart);
        cartAdditionalIngredient.setAdditionalIngredient(additionalIngredient);
        return cartAdditionalIngredient;
    }

    @Override
    public CartAdditionalIngredientBuilder withCart(Cart cart){
        this.cart=cart;
        return this;
    }

    @Override
    public CartAdditionalIngredientBuilder withAdditionalIngredient(AdditionalIngredient additionalIngredient){
        this.additionalIngredient=additionalIngredient;
        return this;
    }

    public int getIdCartAdditionalIngredient() {
        return idCartAdditionalIngredient;
    }

    public Cart getCart() {
        return cart;
    }

    public AdditionalIngredient getAdditionalIngredient() {
        return additionalIngredient;
    }
}
