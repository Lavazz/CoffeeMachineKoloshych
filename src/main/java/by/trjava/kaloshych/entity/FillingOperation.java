package by.trjava.kaloshych.entity;

import java.util.Objects;

public class FillingOperation {
    private static final long serialVersionUID = 1L;

    private int idFillingOperation;
    private Component component;
    private int maxPortion;

    public FillingOperation() {
    }

    public FillingOperation(int idFillingOperation, Component component, int maxPortion) {
        this.idFillingOperation = idFillingOperation;
        this.component = component;
        this.maxPortion = maxPortion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdFillingOperation() {
        return idFillingOperation;
    }

    public void setIdFillingOperation(int idFillingOperation) {
        this.idFillingOperation = idFillingOperation;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getMaxPortion() {
        return maxPortion;
    }

    public void setMaxPortion(int maxPortion) {
        this.maxPortion = maxPortion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FillingOperation that = (FillingOperation) o;
        return idFillingOperation == that.idFillingOperation &&
                maxPortion == that.maxPortion &&
                component.equals(that.component);
    }

    @Override
    public int hashCode() {
        return (int) (31 *idFillingOperation+(component==null?0:component.hashCode())+maxPortion);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idFillingOperation=" + idFillingOperation +
                ", component=" + component +
                ", maxPortion=" + maxPortion;
    }
}
