package by.trjava.kaloshych.entity;

import java.io.Serializable;
import java.util.Objects;

public class AccountUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAccountUser;
    private User user;

    public AccountUser(){}

    public AccountUser(int idAccountUser, User user) {
        this.idAccountUser = idAccountUser;
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdAccountUser() {
        return idAccountUser;
    }

    public void setIdAccountUser(int idAccountUser) {
        this.idAccountUser = idAccountUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountUser that = (AccountUser) o;
        return idAccountUser == that.idAccountUser &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccountUser, user);
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "idAccountUser=" + idAccountUser +
                ", user=" + user +
                '}';
    }
}
