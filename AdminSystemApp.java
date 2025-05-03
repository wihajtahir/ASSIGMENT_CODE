

public class AdminSystemApp {
    public static void main(String[] args) {
        // Initialize services
        AuditLogger logger = new AuditLogger();
        NotificationService notificationService = new NotificationService();
        AdminService adminService = new AdminService(logger, notificationService);

        // Create sample admin and roles
        Admin admin = new Admin("sysadmin", "secure123", true);
        Role adminRole = new Role("ADMIN");
        adminRole.addPermission("MANAGE_ROLES");
        adminRole.addPermission("RESET_ACCOUNT");
        adminRole.addPermission("VIEW_LOGS");
        admin.getRoles().add(adminRole);

        // Create sample user
        UserAccount user = new UserAccount("johndoe", "john@example.com");

        try {
            // Simulate admin actions
            adminService.resetAccount(admin, user);
            
            List<Role> newRoles = List.of(adminRole);
            adminService.manageRoles(admin, user, newRoles);
            
        } catch (SecurityException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
