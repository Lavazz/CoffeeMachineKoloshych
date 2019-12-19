package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.Account;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.PaymentMethod;

import java.util.Date;

public interface AccountBuilder {
    Account build();

    AccountBuilder withIdAccount(int idAccount);

    AccountBuilder withAccountUser(AccountUser accountUser);

    AccountBuilder withPaymentMethod(PaymentMethod paymentMethod);

    AccountBuilder withPaymentDate(Date paymentDate);

    AccountBuilder withAmountOfMoney(double amountOfMoney);
}
