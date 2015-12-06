/**
 * Created by whr on 10/7/15.
 */
import java.lang.InterruptedException;
import java.lang.Thread;
public class main {
    public static void main(String[] args){
        RunnerableThread instance = new RunnerableThread();
        Thread thread = new Thread(instance);
        thread.start();
        while(instance.count != 5) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
    }
}
