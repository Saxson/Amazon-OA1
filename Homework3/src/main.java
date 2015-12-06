/**
 * Created by whr on 10/31/15.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
public class main {
    public static void main(String[] args) {
        Random rand = new Random();
        Event[] events = new Event[10];
        for(int i = 0; i < 10; i++) {
            int temp= (rand.nextInt(3)+2)*1000*60;
            events[i] = new Event(i, 0, temp ,EventType.CPU_event, 30+i*5, TimeType.START);
        }
        Comparator<Event> comparator = new myComparator();
        PriorityQueue<Event> q = new PriorityQueue<Event>(10,comparator);
        FCFS.getInformation(events,q);
    }
}
