import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class NotificationSender {
    private final ObjectOutputStream out;
    private final Scanner scanner;

    public NotificationSender(ObjectOutputStream out, Scanner scanner) {
        this.out = out;
        this.scanner = scanner;
    }

    public void sendNotifications() throws Exception {
        while (true) {
            System.out.print("Enter notification text (or 'exit'): ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                out.writeObject("exit");
                break;
            }

            System.out.print("Enter the notification send time (format: yyyy-MM-dd HH:mm:ss): ");
            String timeStr = scanner.nextLine();

            try {
                long timestamp = validateInput(message, timeStr);
                out.writeObject(message);
                out.writeObject(timestamp);
                out.flush();
                System.out.println("Notification saved.");
            } catch (InvalidNotificationException e) {
                System.err.println("[Validation error] " + e.getMessage());
            }
        }
    }

    private long validateInput(String message, String timeStr) throws InvalidNotificationException {
        if (message.isEmpty()) throw new InvalidNotificationException("Notification cannot be empty");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(timeStr);
            long timestamp = date.getTime();

            if (timestamp < System.currentTimeMillis()) {
                throw new InvalidNotificationException("Time cannot be in the past");
            }

            return timestamp;
        } catch (ParseException e) {
            throw new InvalidNotificationException("Incorrect date format");
        }
    }
}
