/**
 * Created by whr on 12/4/15.
 */
import java.util.PriorityQueue;
import java.util.Comparator;
public class SJF {
    static class Process {
        public int arriveTime;
        public int burstTime;
        public Process(int at, int bt) {
            arriveTime = at;
            burstTime = bt;
        }
    }
    public static double getAvgWaitTime(int[] arrivalTimes, int[] executeTimes) {
        //edge case看具体情况写
        int finished = 0, totalWaitTime = 0, curTime = 0, nextProcess = 0, n = arrivalTimes.length;
        PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                if(o1.burstTime == o2.burstTime) {
                    return Integer.compare(o1.arriveTime, o2.arriveTime);
                } else {
                    return Integer.compare(o1.burstTime, o2.burstTime);
                }
            }
        });
        while(finished < n) {
            if(readyQueue.isEmpty() && nextProcess < n) {
                readyQueue.offer(new Process(arrivalTimes[nextProcess], executeTimes[nextProcess]));
                curTime = arrivalTimes[nextProcess++];
            }
            Process running = readyQueue.poll();
            totalWaitTime += curTime - running.arriveTime;
            curTime += running.burstTime;
            finished++;
            while(nextProcess < n && arrivalTimes[nextProcess] <= curTime) {
                readyQueue.offer(new Process(arrivalTimes[nextProcess],executeTimes[nextProcess]));
                nextProcess++;
            }
        }
        return (double)totalWaitTime / n;
    }
//    public static void main(String[] args) {
//        int[] arrivalTimes = {0,1,3,9};
//        int[] executeTimes = {2,1,7,5};
//
//        System.out.println(getAvgWaitTime(arrivalTimes,executeTimes));
//    }
}
