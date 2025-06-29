import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server{
    private static final int PORT = 44444;
    public static final PriorityBlockingQueue<Notification> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args){
        ExecutorService executor = Executors.newCachedThreadPool();
        new Thread(new NotificationDispatcher(queue)).start();

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("|Server| Listening on port " + PORT);
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }
        }
        catch(IOException e){
            System.err.println("|Server| Server error: " + e.getMessage());
        }

    }
}