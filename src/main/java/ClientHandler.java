import java.io.*;
import java.net.Socket;
/*Class for handling a single client(reading data and adding it to the queue) */
public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                try {
                    Object msgObj = in.readObject();
                    if (msgObj instanceof String && ((String) msgObj).equalsIgnoreCase("exit")) {
                        continue;
                    }
                    if (msgObj instanceof String && ((String) msgObj).equalsIgnoreCase("programexit")) {
                        break;
                    }

                    String message = (String) msgObj;
                    long timestamp = (Long) in.readObject();

                    Notification notif = new Notification(message, timestamp, out);
                    Server.queue.add(notif);

                    System.out.println("[Server] Notification added: " + message);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[Client error] " + e.getMessage());
        }
    }
}