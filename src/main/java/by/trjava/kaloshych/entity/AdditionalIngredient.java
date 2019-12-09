package by.trjava.kaloshych.entity;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalIngredient extends Component implements Serializable {
    private static final long serialVersionUID = 1L;

    private int calories;

    public AdditionalIngredient() {
    }

    public AdditionalIngredient(int idComponent, String nameComponent, int portion, String picturePath, int calories) {
        super(idComponent, nameComponent, portion, picturePath);
        this.calories = calories;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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
        AdditionalIngredient other = (AdditionalIngredient) obj;
        return calories == other.calories;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + calories;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "AdditionalIngredient{" +
                "calories=" + calories +
                '}';
    }
}