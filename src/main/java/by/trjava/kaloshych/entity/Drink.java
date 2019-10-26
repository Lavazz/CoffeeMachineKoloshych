package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class Drink implements Serializable {

    private int idDrink;
    private String drinkName;
    private double price;

    public Drink() {
    }

    public Drink(int idDrink, String drinkName, double price) {
        this.idDrink = idDrink;
        this.drinkName = drinkName;
        this.price = price;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Drink other = (Drink) obj;
        if (idDrink != other.idDrink) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        if (drinkName == null) {
            if (other.drinkName != null) {
            } else if (!drinkName.equals(other.drinkName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * idDrink + (drinkName == null ? 0 : drinkName.hashCode()) + price);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idDrink=" + idDrink +
                ", drinkName='" + drinkName + '\'' +
                ", price=" + price;
    }
}