package by.trjava.coffeemachine.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int idOrder;
    private int idUser;
    public Order(){}


    public Order(int idUser) {
        this.idUser = idUser;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        Order other = (Order) obj;
        if (idUser != other.idUser) {
            return false;
        }
        if (idOrder != other.idOrder) {
            return false;
        }
        return  true;
    }

    @Override
    public int hashCode() {
        return (int) (31*idUser+idOrder);
    }

    @Override
    public String toString() {
        return getClass().getName()+"@"+
                "idOrder=" + idOrder +
                ", idUser=" + idUser;
    }
}
