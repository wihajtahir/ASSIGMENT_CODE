public class Role {
    private String name;
    private Set<String> permissions;

    public Role(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
    }

    public void addPermission(String permission) {
        permissions.add(permission);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    // Getters
    public String getName() { return name; }
    public Set<String> getPermissions() { return permissions; }
}
