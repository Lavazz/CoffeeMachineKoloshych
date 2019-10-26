package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class AdditionalIngredient extends Ingredient implements Serializable {
    private double price;

    public AdditionalIngredient() {
    }

    public AdditionalIngredient(int idIngredient, String nameIngredient, double price, int portion) {
        super(idIngredient, nameIngredient, portion);
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        if (!super.equals(obj)) {
            return false;
        }
        AdditionalIngredient other = (AdditionalIngredient) obj;
        if (price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * price);

    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                " price" + price;
    }
}