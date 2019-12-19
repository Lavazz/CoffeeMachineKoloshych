package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.CartUserBuilder;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;

public class CartUserBuilderImpl implements CartUserBuilder {
    private int idCartUser;
    private User user;

    public CartUserBuilderImpl() {
    }

    @Override
    public CartUser build() {
        CartUser cartUser = new CartUser();
        cartUser.setIdCartUser(idCartUser);
        cartUser.setUser(user);
        return cartUser;
    }

    @Override
    public CartUserBuilder withIdCartUser(int idCartUser) {
        this.idCartUser = idCartUser;
        return this;
    }

    @Override
    public CartUserBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public int getIdCartUser() {
        return idCartUser;
    }

    public User getUser() {
        return user;
    }
}
