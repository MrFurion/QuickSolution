package by.trubetski.quick.solution.util.enums;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_COURIER("ROLE_COURIER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
