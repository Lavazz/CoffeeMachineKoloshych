package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.PaymentMethodBuilder;
import by.trjava.kaloshych.entity.PaymentMethod;

public class PaymentMethodBuilderImpl implements PaymentMethodBuilder {

    private int idPaymentMethod;
    private String namePaymentMethod;

    public PaymentMethodBuilderImpl(){}

    public PaymentMethodBuilderImpl(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    @Override
    public PaymentMethodBuilder withNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
        return this;
    }

    @Override
    public PaymentMethod build() {
        final PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setIdPaymentMethod(idPaymentMethod);
        paymentMethod.setNamePaymentMethod(namePaymentMethod);
        return paymentMethod;
    }


    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }
}
