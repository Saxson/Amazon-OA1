import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * Created by whr on 10/31/15.
 */
public class FCFS {
       public static void getInformation(Event[] events, PriorityQueue<Event> q) {
           Queue<Event> ready_q = new LinkedList<Event>();
           for(int i = 0;i < 10; i++) {
               ready_q.offer(events[i]);
           }
           Queue<Event> IO_q = new LinkedList<Event>();
           q.offer(events[0]);
           int curTime = 0;
           while(!q.isEmpty()) {
               Event cur = q.poll();
               curTime = cur.arriveTime;
               //IO_event
               if(cur.T == EventType.IO_event) {
                   if(cur.TT == TimeType.START) {
                       cur.TT = TimeType.END;
                       cur.arriveTime = curTime + 60;
                       q.offer(cur);

                   } else {
                       IO_q.poll();
                       if(!IO_q.isEmpty()) {
                           Event temp = IO_q.peek();
                           temp.TT = TimeType.START;
                           q.offer(temp);
                       }
                       cur.T = EventType.CPU_event;
                       cur.TT = TimeType.START;
                       if(ready_q.isEmpty()) {
                           q.offer(cur);
                       }
                       ready_q.offer(cur);
                   }
               }
               //CPU_event
               if(cur.T == EventType.CPU_event){
                   if(cur.TT == TimeType.START) {
                       cur.TT = TimeType.END;
                       double seed = 1. / cur.IO_interval;
                       int interval = (int)(cur.IO_interval * RandomGenerator.expRandom(seed));
                       if(cur.burstTime > interval) {
                           cur.arriveTime = curTime + interval;
                           cur.burstTime -= interval;
                       } else {
                           cur.arriveTime = curTime + cur.burstTime ;
                           cur.burstTime = 0;
                       }
                       q.offer(cur);

                   } else {
                       ready_q.poll();
                       if(!ready_q.isEmpty()){
                           Event temp = ready_q.peek();
                           temp.TT = TimeType.START;
                           q.offer(temp);
                       }
                       if(cur.burstTime > 0) {
                           cur.T = EventType.IO_event;
                           cur.TT = TimeType.START;
                           if (IO_q.isEmpty()) {
                               q.offer(cur);
                           }
                           IO_q.offer(cur);
                       }
                   }

               }
           }
           System.out.println(curTime);
       }
}
