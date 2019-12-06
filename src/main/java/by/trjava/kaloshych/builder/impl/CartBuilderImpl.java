package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.CartBuilder;
import by.trjava.kaloshych.builder.CartUserBuilder;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;

public class CartBuilderImpl implements CartBuilder {
    private int idCart;
    private CartUser cartUser;
    private Drink drink;
    private int portion;


    public CartBuilderImpl(){}

    public CartBuilderImpl(int idCart){
        this.idCart=idCart;
    }

    @Override
    public Cart build(){
        Cart cart=new Cart();
        cart.setIdCart(idCart);
        cart.setCartUser(cartUser);
        cart.setDrink(drink);
        cart.setPortion(portion);
        return cart;
    }

    @Override
    public CartBuilder withCartUser(CartUser cartUser){
        this.cartUser=cartUser;
        return this;
    }

    @Override
    public CartBuilder withDrink(Drink drink){
        this.drink=drink;
        return this;
    }

    @Override
    public CartBuilder withPortion(int portion){
        this.portion=portion;
        return this;
    }

    public int getIdCart() {
        return idCart;
    }

    public CartUser getCartUser() {
        return cartUser;
    }

    public Drink getDrink() {
        return drink;
    }

    public int getPortion() {
        return portion;
    }
}
