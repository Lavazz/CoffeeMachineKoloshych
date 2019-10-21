package by.trjava.coffeemachine.entity;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String nameIngredient;
    private String brand;

    public Ingredient() {
    }

    public Ingredient(String nameIngredient, String brand) {
        this.nameIngredient = nameIngredient;
        this.brand = brand;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
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
        Ingredient other = (Ingredient) obj;
        if (nameIngredient == null) {
            if (other.nameIngredient != null) {
                return false;
            } else if (!nameIngredient.equals(other.nameIngredient)) {
                return false;
            }
        }
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
        return (int) (31 * (nameIngredient == null ? 0 : nameIngredient.hashCode() + (brand == null ? 0 : nameIngredient.hashCode())));
    }

    @Override
    public String toString() {
        return getClass().getName()+"@"+
                "nameIngredient='" + nameIngredient + '\'' +
                ", brand='" + brand ;
    }
}
