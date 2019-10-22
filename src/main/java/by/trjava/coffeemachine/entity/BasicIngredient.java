package by.trjava.coffeemachine.entity;

import java.io.Serializable;
import java.util.Objects;

public class BasicIngredient extends Ingredient implements Serializable {
    private int idBasicIngredient;

    public BasicIngredient() {
    }


    public BasicIngredient(String nameIngredient, String type, String brand, int idBasicIngredient) {
        super(nameIngredient, brand);
        this.idBasicIngredient = idBasicIngredient;
    }

    public int getIdBasicIngredient() {
        return idBasicIngredient;
    }

    public void setIdBasicIngredient(int idBasicIngredient) {
        this.idBasicIngredient = idBasicIngredient;
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
        if (idBasicIngredient != other.idBasicIngredient) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBasicIngredient);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idBasicIngredient=" + idBasicIngredient;
    }
}
