package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class CartAdditionalIngredient implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCartAdditionalIngredient;
    private Cart cart;
    private AdditionalIngredient additionalIngredient;

    public CartAdditionalIngredient() {
    }

    public CartAdditionalIngredient(int idCartAdditionalIngredient, Cart cart, AdditionalIngredient additionalIngredient) {
        this.idCartAdditionalIngredient = idCartAdditionalIngredient;
        this.cart = cart;
        this.additionalIngredient = additionalIngredient;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdCartAdditionalIngredient() {
        return idCartAdditionalIngredient;
    }

    public void setIdCartAdditionalIngredient(int idCartAdditionalIngredient) {
        this.idCartAdditionalIngredient = idCartAdditionalIngredient;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public AdditionalIngredient getAdditionalIngredient() {
        return additionalIngredient;
    }

    public void setAdditionalIngredient(AdditionalIngredient additionalIngredient) {
        this.additionalIngredient = additionalIngredient;
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
        CartAdditionalIngredient other = (CartAdditionalIngredient) obj;
        if (idCartAdditionalIngredient != other.idCartAdditionalIngredient) {
            return false;
        }
        if (cart == null){
            if(other.cart!=null) {
                return false;
            }
            }else if(!cart.equals(other.cart)){
                return false;
        }
        if (additionalIngredient == null){
            if(other.additionalIngredient!=null) {
                return false;
            }
            }else if(!additionalIngredient.equals(other.additionalIngredient)){
                return false;
            }
               return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idCartAdditionalIngredient + (cart==null?0:cart.hashCode()) + (additionalIngredient==null?0:additionalIngredient.hashCode()) );
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idCartAdditionalIngredient=" + idCartAdditionalIngredient +
                ", cart=" + cart +
                ", additionalIngredient=" + additionalIngredient;
    }
}

