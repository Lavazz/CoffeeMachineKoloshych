package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.entity.UserStatus;

public interface UserBuilder {

    User build();

    UserBuilder withId(int id);

    UserBuilder withPassword(String password);

    UserBuilder withLogin(String login);

    UserBuilder withName(String name);

    UserBuilder withEmail(String email);

    UserBuilder withUserStatus(UserStatus userStatus);


}
