package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class BasicIngredient extends Ingredient implements Serializable {
    private String brand;

    public BasicIngredient() {
    }


    public BasicIngredient( int idIngredient, String nameIngredient, String type, String brand, int portion) {
        super(idIngredient, nameIngredient, portion);
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        if (!super.equals(obj)) return false;
        BasicIngredient other = (BasicIngredient) obj;
        if (brand == null) {
            if (other.brand != null) {
                return false;
            } else if (!brand.equals(other.brand)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int)(31*(brand == null ? 0 : brand.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                " brand='" + brand;
    }
}
