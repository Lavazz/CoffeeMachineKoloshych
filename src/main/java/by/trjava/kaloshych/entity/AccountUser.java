package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class AccountUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idAccountUser;
    private User user;

    public AccountUser() {
    }

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
        AccountUser other = (AccountUser) obj;
        return idAccountUser != other.idAccountUser
                && (user == null ? user == other.user : user.equals(other.user));
    }


    @Override
    public int hashCode() {
        return 31 * idAccountUser + (user == null ? 0 : user.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idAccountUser=" + idAccountUser +
                ", user=" + user;
    }
}
