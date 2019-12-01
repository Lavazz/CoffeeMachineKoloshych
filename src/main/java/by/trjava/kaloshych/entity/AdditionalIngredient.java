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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdditionalIngredient that = (AdditionalIngredient) o;
        return calories == that.calories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), calories);
    }

    @Override
    public String toString() {
        return "AdditionalIngredient{" +
                "calories=" + calories +
                '}';
    }
}