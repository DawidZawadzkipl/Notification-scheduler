import java.util.concurrent.PriorityBlockingQueue;
/*servers thread handling notification queue and sending notifications at the right time */
public class NotificationDispatcher implements Runnable{
    private final PriorityBlockingQueue<Notification> queue;

    public NotificationDispatcher(PriorityBlockingQueue<Notification> queue){
        this.queue = queue;
    }

    @Override
    public void run(){
        while(true){
            try{
                Notification notif = queue.peek();
                if(notif != null && System.currentTimeMillis() >= notif.time){
                    queue.poll();
                    notif.writer.println(notif.message);
                    notif.writer.flush();
                }
                else{
                    Thread.sleep(500);
                }
            }
            catch (Exception e){
                System.err.println("Queue error: " + e.getMessage());
            }

        }
    }
}