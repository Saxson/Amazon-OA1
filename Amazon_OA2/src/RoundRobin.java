/**
 * Created by whr on 12/4/15.
 */
import java.util.Queue;
import java.util.LinkedList;
public class RoundRobin {
    static class Process {
        public int arriveTime;
        public int burstTime;
        public Process(int at, int bt) {
            arriveTime = at;
            burstTime = bt;
        }
    }
    public static double getAvgWaitTime(int[] arrivalTimes, int[] executeTimes, int q) {
        //edge case 看情况定
        int finished = 0, totalWaitTime = 0, curTime = 0, nextProcess = 0, n = arrivalTimes.length;
        Queue<Process> readyQueue = new LinkedList<Process>();
        while(finished < n) {
            if(readyQueue.isEmpty() && nextProcess < n) {
                readyQueue.offer(new Process(arrivalTimes[nextProcess],executeTimes[nextProcess]));
                curTime = arrivalTimes[nextProcess++];
            }
            Process running = readyQueue.poll();
            totalWaitTime += curTime - running.arriveTime;
            int runTime = Math.min(running.burstTime, q);
            curTime += runTime;
            while(nextProcess < n && arrivalTimes[nextProcess] <= curTime) {
                readyQueue.offer(new Process(arrivalTimes[nextProcess],executeTimes[nextProcess]));
                nextProcess++;
            }
            running.burstTime -= runTime;
            if(running.burstTime == 0) {
                finished++;
            } else {
                running.arriveTime = curTime;
                readyQueue.offer(running);
            }
        }
        return (double)totalWaitTime / n;
    }

}
