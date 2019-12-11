package by.trjava.kaloshych.entity;

import java.io.Serializable;

public class Drink extends Component implements Serializable {
    private static final long serialVersionUID = 1L;

    private double price;
    private String description;

    public Drink() {
    }

    public Drink(int idComponent, String nameComponent, int portion, String picturePath, String description, double price) {
        super(idComponent, nameComponent, portion, picturePath);
        this.price = price;
        this.description = description;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Drink other = (Drink) obj;
        if (Double.compare(price, other.price) != 0) {
            return false;
        }
        if (description == null) {
            return other.description == null;
        } else return description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return (int) (31 * super.hashCode() + (description == null ? 0 : description.hashCode()) + price);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                ", description" + description +
                ", price=" + price;
    }
}
