package by.trjava.kaloshych.entity;

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
        FillingOperation other = (FillingOperation) obj;
        if (idFillingOperation != other.idFillingOperation) {
            return false;
        }
        if (component == null) {
            if (other.component != null) {
                return false;
            }
        } else if (!component.equals(other.component)) {
            return false;
        }
        return maxPortion == other.maxPortion;
    }


    @Override
    public int hashCode() {
        return 31 * idFillingOperation + (component == null ? 0 : component.hashCode()) + maxPortion;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "idFillingOperation=" + idFillingOperation +
                ", component=" + component +
                ", maxPortion=" + maxPortion;
    }
}
