package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.AccountUserBuilder;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;


public class AccountUserBuilderImpl implements AccountUserBuilder {

    private int idAccountUser;
    private User user;

    public AccountUserBuilderImpl() {
    }


    @Override
    public AccountUser build() {
        final AccountUser accountUser = new AccountUser();
        accountUser.setIdAccountUser(idAccountUser);
        accountUser.setUser(user);
        return accountUser;
    }

    @Override
    public AccountUserBuilder withIdAccountUser(int idAccountUser) {
        this.idAccountUser = idAccountUser;
        return this;
    }

    @Override
    public AccountUserBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public int getIdAccountUser() {
        return idAccountUser;
    }

    public User getUser() {
        return user;
    }

}
