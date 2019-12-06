package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.UserBuilder;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.entity.UserStatus;

public class UserBuilderImpl implements UserBuilder {

    private int id;
    private String login;
    private String password;
    private String email;
    private String name;
    private UserStatus userStatus;

    public UserBuilderImpl() {
    }

    public UserBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public User build() {
        final User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setUserStatus(userStatus);
        return user;
    }

    @Override
    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public UserBuilder withUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }


}
