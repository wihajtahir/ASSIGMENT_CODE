public class Admin {
    private String username;
    private String password;
    private boolean isSuperAdmin;
    private List<Role> roles;

    public Admin(String username, String password, boolean isSuperAdmin) {
        this.username = username;
        this.password = password;
        this.isSuperAdmin = isSuperAdmin;
        this.roles = new ArrayList<>();
    }

    public boolean verifyCredentials(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public boolean hasPermission(String permission) {
        return roles.stream().anyMatch(role -> role.hasPermission(permission));
    }

    // Getters and setters
    public String getUsername() { return username; }
    public boolean isSuperAdmin() { return isSuperAdmin; }
    public List<Role> getRoles() { return roles; }
}
