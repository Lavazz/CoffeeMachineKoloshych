package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class CartUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCartUser;
    private User user;

    public CartUser() {
    }

    public CartUser(int idCartUser, User user) {
        this.idCartUser = idCartUser;
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdCartUser() {
        return idCartUser;
    }

    public void setIdCartUser(int idCartUser) {
        this.idCartUser = idCartUser;
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
        CartUser other = (CartUser) obj;
        if (idCartUser != other.idCartUser) {
            return false;
        }
        if (user == null) {
            return other.user == null;
        } else return user.equals(other.user);
    }

    @Override
    public int hashCode() {
        return 31 * idCartUser + (user == null ? 0 : user.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idCartUser=" + idCartUser +
                ", user=" + user;
    }
}
