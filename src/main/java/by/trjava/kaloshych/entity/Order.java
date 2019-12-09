package by.trjava.kaloshych.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idOrder;
  private CartUser cartUser;
    private Date dateOrder;
    private double totalCost;

    public Order() {
    }

    public Order(int idOrder, CartUser cartUser, Date dateOrder, double totalCost) {
        this.idOrder = idOrder;
        this.cartUser = cartUser;
        this.dateOrder = dateOrder;
        this.totalCost = totalCost;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public CartUser getCartUser() {
        return cartUser;
    }

    public void setCartUser(CartUser cartUser) {
        this.cartUser = cartUser;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
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
        if (idOrder != other.idOrder) {
            return false;
        }
        if (cartUser != other.cartUser) {
            return false;
        }
        if (dateOrder == null) {
            if (other.dateOrder != null) {
                return false;
            }
            } else if (!dateOrder.equals(other.dateOrder)) {
                return false;
            }
        return  Double.compare(totalCost, other.totalCost) == 0;
    }



    @Override
    public int hashCode() {
        return (int) (31 *idOrder+(cartUser==null?0:cartUser.hashCode())+(dateOrder==null?0: dateOrder.hashCode())+totalCost);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idOrder=" + idOrder +
                ", cartUser=" + cartUser+
                ", dateOrder="+ dateOrder+
                ", totalCost="+totalCost;
    }
}
