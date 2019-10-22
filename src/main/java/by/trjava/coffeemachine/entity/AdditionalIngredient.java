package by.trjava.coffeemachine.entity;

import java.io.Serializable;

public class AdditionalIngredient extends Ingredient implements Serializable {
    private int idAdditionalIngredient;
    private double price;

    public AdditionalIngredient() {
    }

    public AdditionalIngredient(String nameIngredient, String type, String brand, int idAdditionalIngredient, double price) {
        super(nameIngredient, brand);
        this.idAdditionalIngredient = idAdditionalIngredient;
        this.price = price;
    }

    public int getIdAdditionalIngredient() {
        return idAdditionalIngredient;
    }

    public void setIdAdditionalIngredient(int idAdditionalIngredient) {
        this.idAdditionalIngredient = idAdditionalIngredient;
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
        if (idAdditionalIngredient != other.idAdditionalIngredient) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idAdditionalIngredient + price);

    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                " idAdditionalIngredient" + idAdditionalIngredient +
                " price" + price;
    }
}