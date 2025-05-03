public class NotificationService {
    public void notifyUser(UserAccount user, String message) {
        // In real implementation, would send email or other notification
        System.out.printf("Notification sent to %s (%s): %s%n", 
            user.getUsername(), user.getEmail(), message);
    }

    public void notifySecurity(String alert) {
        System.err.println("SECURITY ALERT: " + alert);
    }
}
