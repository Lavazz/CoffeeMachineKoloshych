package by.trjava.kaloshych.entity;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAccount;
    private AccountUser accountUser;
    private PaymentMethod paymentMethod;
    private Date paymentDate;
    private double amountOfMoney;

    public Account(int idAccount, AccountUser accountUser, PaymentMethod paymentMethod, Date paymentDate, double amountOfMoney) {
        this.idAccount = idAccount;
        this.accountUser = accountUser;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.amountOfMoney = amountOfMoney;
    }

    public Account() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
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
        if (accountUser == null) {
            if (other.accountUser != null) {
                return false;
            } else if (!accountUser.equals(other.accountUser)) {
                return false;
            }
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
        return (int) (31 * idAccount + (accountUser == null ? 0 : accountUser.hashCode())  + (paymentMethod == null ? 0 : paymentMethod.hashCode())
                + (paymentDate == null ? 0 : paymentDate.hashCode()) + amountOfMoney);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idAccount=" + idAccount +
                ", accountUser=" + accountUser +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                ", amountOfMoney=" + amountOfMoney;
    }
}

