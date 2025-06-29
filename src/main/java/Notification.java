import java.io.PrintWriter;
/*comparable for priority queue */
public class Notification implements Comparable<Notification>{
    String message;
    long time;
    PrintWriter writer;

    public Notification(String message, long time, PrintWriter writer){
        this.message = message;
        this.time = time;
        this.writer = writer;
    }

    @Override
    public int compareTo(Notification o){
        return Long.compare(this.time, o.time);
    }
}