package by.trjava.coffeemachine.entity;

import java.io.Serializable;

public class OrderJournal implements Serializable {
    private int idOrder;
    private int idDrink;
    private int idAdditionalIngredient;
    private int portion;

    public OrderJournal(int idOrder, int idDrink, int idAdditionalIngredient, int portion) {
        this.idOrder = idOrder;
        this.idDrink = idDrink;
        this.idAdditionalIngredient = idAdditionalIngredient;
        this.portion = portion;
    }

    public OrderJournal() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public int getIdAdditionalIngredient() {
        return idAdditionalIngredient;
    }

    public void setIdAdditionalIngredient(int idAdditionalIngredient) {
        this.idAdditionalIngredient = idAdditionalIngredient;
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
        OrderJournal other = (OrderJournal) obj;
        if (idOrder != other.idOrder) {
            return false;
        }
        if (idDrink != other.idDrink) {
            return false;
        }
        if (idAdditionalIngredient != other.idAdditionalIngredient) {
            return false;
        }
        if (portion != other.portion) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return  (int)(31*idOrder+idDrink+idAdditionalIngredient+portion);
    }

    @Override
    public String toString() {
        return getClass().getName()+"@"+
                "idOrder=" + idOrder +
                ", idDrink=" + idDrink +
                ", idAdditionalIngredient=" + idAdditionalIngredient +
                ", portion=" + portion;
    }
}
