import java.io.BufferedReader;
import java.io.IOException;

public class NotificationReceiver implements Runnable {
    private final BufferedReader in;

    public NotificationReceiver(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("[NOTIFICATION RECEIVED] " + line);
            }
        } catch (IOException e) {
            System.err.println("[Notification receiving error] " + e.getMessage());
        }
    }
}