package by.trjava.coffeemachine.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private int idAccount;
    private int idUser;
    private String paymentMethod;
    private Date paymentDate;
    private double amountOfMoney;

    public Account(int idAccount, int idUser, String paymentMethod, Date paymentDate, double amountOfMoney) {
        this.idAccount = idAccount;
        this.idUser = idUser;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.amountOfMoney = amountOfMoney;
    }

    public Account() {
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
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
        Account other = (Account) obj;
        if (idUser != other.idUser) {
            return false;
        }
        if (idAccount != other.idAccount) {
            return false;
        }
        if (paymentMethod == null) {
            if (other.paymentMethod != null) {
                return false;
            } else if (!paymentMethod.equals(other.paymentMethod)) {
                return false;
            }
        }
        if (paymentDate == null) {
            if (other.paymentDate != null) {
                return false;
            } else if (!paymentDate.equals(other.paymentDate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idAccount + idUser + (paymentMethod == null ? 0 : paymentMethod.hashCode())
                + (paymentDate == null ? 0 : paymentDate.hashCode()) + amountOfMoney);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idAccount=" + idAccount +
                ", idUser=" + idUser +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                ", amountOfMoney=" + amountOfMoney;
    }
}

