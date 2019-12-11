package by.trjava.kaloshych.entity;

public enum UserStatus {

    ADMIN(1),
    CUSTOMER(2),
    GUEST(3);

    private static final long serialVersionUID = 1L;
    private int idUserStatus;

    UserStatus(int idUserStatus) {
        this.idUserStatus = idUserStatus;
    }

    public int getIdUserStatus() {
        return idUserStatus;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@ idUserStatus=" + idUserStatus;
    }
}