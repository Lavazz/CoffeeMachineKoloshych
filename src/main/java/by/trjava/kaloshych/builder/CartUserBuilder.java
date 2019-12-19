package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.User;

public interface CartUserBuilder {
    CartUser build();

    CartUserBuilder withIdCartUser(int idCartUser);

    CartUserBuilder withUser(User User);
}
