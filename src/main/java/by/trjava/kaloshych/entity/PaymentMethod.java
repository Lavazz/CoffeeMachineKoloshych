package by.trjava.kaloshych.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idPaymentMethod;
    private String namePaymentMethod;

    public PaymentMethod() {
    }

    public PaymentMethod(int idPaymentMethod, String namePaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
        this.namePaymentMethod = namePaymentMethod;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(idPaymentMethod, that.idPaymentMethod) &&
                Objects.equals(namePaymentMethod, that.namePaymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPaymentMethod, namePaymentMethod);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idPaymentMethod='" + idPaymentMethod + '\'' +
                ", namePaymentMethod='" + namePaymentMethod + '\'' +
                '}';
    }
}
