/**
 * Created by whr on 10/7/15.
 */
import java.lang.Runnable;
import java.lang.InterruptedException;
public class RunnerableThread implements Runnable {
    public int count = 0;

    public void run() {
        System.out.println("RunnableThread starting");
        try {
            while (count < 5) {
                Thread.sleep(500);
                count++;
            }

        } catch (InterruptedException exc) {
            System.out.println("RunnableTread interruted.");
        }
        System.out.println("RunnableThread terminating");
    }
}

