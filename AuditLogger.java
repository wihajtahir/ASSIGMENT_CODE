public class AuditLogger {
    public void logAction(String adminUsername, String action) {
        String logEntry = String.format("[%s] %s: %s", new Date(), adminUsername, action);
        System.out.println(logEntry);
        // In real implementation, write to database or file
    }

    public void logViolation(String username, String violation) {
        String logEntry = String.format("[%s] SECURITY VIOLATION by %s: %s", new Date(), username, violation);
        System.err.println(logEntry);
        // In real implementation, would also trigger alerts
    }
}
