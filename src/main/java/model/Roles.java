package model;

public enum Roles {
    ADMIN(1), USER(2);
    private final int value;

    private Roles(int value) {
        this.value = value;
    }

    public int getRole() {
        return value;
    }
}
