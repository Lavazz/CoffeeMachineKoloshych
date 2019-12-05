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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) obj;
        if (idPaymentMethod!=other.idPaymentMethod) {
            return  false;
        }
        if (namePaymentMethod == null) {
            if (other.namePaymentMethod != null) {
                return false;
            } else if (!namePaymentMethod.equals(other.namePaymentMethod)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idPaymentMethod + (namePaymentMethod == null ? 0 : namePaymentMethod.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idPaymentMethod='" + idPaymentMethod + '\'' +
                ", namePaymentMethod='" + namePaymentMethod;
    }
}
