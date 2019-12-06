package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.PaymentMethod;

public interface PaymentMethodBuilder {
    PaymentMethodBuilder withNamePaymentMethod(String namePaymentMethod);

    PaymentMethod build();
}

