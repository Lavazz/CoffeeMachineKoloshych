package by.trjava.kaloshych.builder;

import by.trjava.kaloshych.entity.PaymentMethod;

public interface PaymentMethodBuilder {

    PaymentMethodBuilder withidPaymentMethod(int idPaymentMethod);

    PaymentMethodBuilder withNamePaymentMethod(String namePaymentMethod);

    PaymentMethod build();
}

