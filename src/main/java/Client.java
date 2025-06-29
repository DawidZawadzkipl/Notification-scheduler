import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 44444;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in)
        ) {
            //starting notification receiving in separe thread
            NotificationReceiver receiver = new NotificationReceiver(in);
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();

            boolean running = true;
            while (running) {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Add notification");
                System.out.println("2. Exit");
                System.out.print("Choose option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        NotificationSender sender = new NotificationSender(out, scanner);
                        sender.sendNotifications();
                        break;
                    case "2":
                        out.writeObject("programexit");
                        out.flush();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
            receiverThread.join();
            System.out.println("Client closed.");

        } catch (Exception e) {
            System.err.println("[Client error] " + e.getMessage());
        }
    }
}
