package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private int idIngredient;
    private String nameIngredient;
    int portion;

    public Ingredient() {
    }

    public Ingredient(int idIngredient, String nameIngredient, int portion) {
        this.nameIngredient = nameIngredient;
        this.portion=portion;
        this.idIngredient = idIngredient;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idBasicIngredient) {
        this.idIngredient = idIngredient;
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
        Ingredient other = (Ingredient) obj;
        if(idIngredient!=other.idIngredient){
            return false;
        }
        if (nameIngredient == null) {
            if (other.nameIngredient != null) {
                return false;
            } else if (!nameIngredient.equals(other.nameIngredient)) {
                return false;
            }
        }

        if(portion!=other.portion){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 *idIngredient+ (nameIngredient == null ? 0 : nameIngredient.hashCode()
               +portion));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "nameIngredient='" + nameIngredient  +
                " idIngredient=" + idIngredient+
                ", portion=" +portion;
    }
}
