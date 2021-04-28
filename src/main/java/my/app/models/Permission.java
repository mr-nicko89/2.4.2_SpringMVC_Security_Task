package my.app.models;

public enum Permission {
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
