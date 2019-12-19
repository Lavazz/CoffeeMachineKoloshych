package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.User;

public interface AccountUserBuilder {

    AccountUser build();

    AccountUserBuilder withIdAccountUser(int idAccountUser);

    AccountUserBuilder withUser(User user);
}
