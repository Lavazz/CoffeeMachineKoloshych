package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCart;
    private CartUser cartUser;
    private Drink drink;
    private int portion;

    public Cart() {
    }

    public Cart(int idCart, CartUser cartUser, Drink drink, int portion) {
        this.idCart = idCart;
        this.cartUser = cartUser;
        this.drink = drink;
        this.portion = portion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public CartUser getCartUser() {
        return cartUser;
    }

    public void setCartUser(CartUser cartUser) {
        this.cartUser = cartUser;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
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
        Cart other = (Cart) obj;
        if (idCart != other.idCart) {
            return false;
        }
        if (drink == null) {
            if (other.drink != null) {
                return false;
            } else if (drink != other.drink) {
                return false;
            }
        }
        if (cartUser == null) {
            if (other.cartUser != null) {
                return false;
            } else if (cartUser != other.cartUser) {
                return false;
            }
        }

        if (portion != other.portion) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idCart+(cartUser==null?0:cartUser.hashCode()) + (drink==null?0:drink.hashCode()) +  portion);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idCart=" + idCart +
                ", cartUser =" + cartUser +
                ", drink=" + drink +
                ", portion=" + portion;
    }
}

