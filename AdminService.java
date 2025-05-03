public class AdminService {
    private final AuditLogger logger;
    private final NotificationService notificationService;

    public AdminService(AuditLogger logger, NotificationService notificationService) {
        this.logger = logger;
        this.notificationService = notificationService;
    }

    public void manageRoles(Admin admin, UserAccount user, List<Role> newRoles) {
        if (!admin.hasPermission("MANAGE_ROLES")) {
            logger.logViolation(admin.getUsername(), "Attempted to manage roles without permission");
            throw new SecurityException("Unauthorized action");
        }

        // Implementation to update user roles
        logger.logAction(admin.getUsername(), "Updated roles for user: " + user.getUsername());
        notificationService.notifyUser(user, "Your account roles have been updated");
    }

    public void resetAccount(Admin admin, UserAccount user) {
        if (!admin.hasPermission("RESET_ACCOUNT")) {
            logger.logViolation(admin.getUsername(), "Attempted to reset account without permission");
            throw new SecurityException("Unauthorized action");
        }

        String tempPassword = generateTempPassword();
        user.setTemporaryPassword(tempPassword);
        user.setLocked(false);
        
        logger.logAction(admin.getUsername(), "Reset account for user: " + user.getUsername());
        notificationService.notifyUser(user, "Your account has been reset. Temporary password: " + tempPassword);
    }

    private String generateTempPassword() {
        // Simple implementation - in production use secure random generator
        return "TEMP-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
