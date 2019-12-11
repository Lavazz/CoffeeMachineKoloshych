package by.trjava.kaloshych.entity;

import java.io.Serializable;

public abstract class Component implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idComponent;
    private String nameComponent;
    private int portion;

    private String picturePath;

    public Component() {
    }

    public Component(int idComponent, String nameComponent, int portion, String picturePath) {
        this.idComponent = idComponent;
        this.nameComponent = nameComponent;
        this.portion = portion;
        this.picturePath = picturePath;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public String getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
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

        Component other = (Component) obj;
        if (idComponent != other.idComponent) {
            return false;
        }
        if (nameComponent == null) {
            if (other.nameComponent != null) {
                return false;
            }
        } else if (!nameComponent.equals(other.nameComponent)) {
            return false;
        }
        if (portion != other.portion) {
            return false;
        }
        if (picturePath == null) {
            return other.picturePath == null;
        } else return picturePath.equals(other.picturePath);
    }

    @Override
    public int hashCode() {
        return 31 * idComponent + (nameComponent == null ? 0 : nameComponent.hashCode())
                + portion + (picturePath == null ? 0 : picturePath.hashCode());

    }

    @Override
    public String toString() {
        return getClass().getName() + "@" +
                "nameComponent='" + nameComponent +
                " idComponent=" + idComponent +
                ", portion=" + portion +
                ", picturePath" + picturePath;
    }
}
