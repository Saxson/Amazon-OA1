/**
 * Created by whr on 10/2/15.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
public class main {
    public static void main(String[] args){
        //stops record the state of each bus stop
        BusStop[] stops = new BusStop[Initialization.bus_stop_number];
        for(int i = 0; i < Initialization.bus_stop_number;i++){
            stops[i] = new BusStop();
        }
        //buses record the state of each bus
        Bus[] buses = new Bus[Initialization.bus_number];
        for(int i = 0;i < Initialization.bus_number;i++){
            buses[i] = new Bus();
            buses[i].Bus_Stop_Num = i*3;
        }
        //Use priority queue as structure to dispatch events
        Comparator<Event> comparator = new myComparator();
        PriorityQueue<Event> q = new PriorityQueue<Event>(10,comparator);
        //initialize the priority q
        for(int i = 0;i < Initialization.bus_number;i++){
            ArrivalEvent temp = new ArrivalEvent(0,EventType.ARRIVAL,i*3,i);
            q.offer(temp);
        }
        for(int i = 0;i < Initialization.bus_stop_number;i++){
            PersonEvent temp = new PersonEvent(0,EventType.PERSON,i);
            q.offer(temp);
        }
        //record current timestamp;
        double cur_time;
        for(int i = 0;i < Initialization.total_time;i++) {
            do {
                Event temp = q.poll();
                cur_time = temp.getTimeStamp();
                //process arrival event
                if (temp.getEventType() == EventType.ARRIVAL) {
                    int cur_stop = ((ArrivalEvent) temp).getBus_Stop_Num();
                    int cur_bus = ((ArrivalEvent) temp).getBus_Num();
                    buses[cur_bus].Bus_Stop_Num = cur_stop;
                    stops[cur_stop].max = Math.max(stops[cur_stop].max, stops[cur_stop].q.size());
                    stops[cur_stop].min = Math.min(stops[cur_stop].min, stops[cur_stop].q.size());
                    stops[cur_stop].n++;
                    stops[cur_stop].sum += stops[cur_stop].q.size();
                    if (stops[cur_stop].q.isEmpty()) {
                        ArrivalEvent next_arrival = new ArrivalEvent(cur_time + Initialization.drive_interval, EventType.ARRIVAL, (cur_stop + 1) % Initialization.bus_stop_number, cur_bus);
                        q.offer(next_arrival);
                    } else {
                        BoardEvent board = new BoardEvent(cur_time, EventType.BOARD, cur_stop, cur_bus);
                        q.offer(board);
                    }
                }
                //process board event
                else if (temp.getEventType() == EventType.BOARD) {
                    int cur_stop = ((BoardEvent) temp).getBus_Stop_Num();
                    int cur_bus = ((BoardEvent) temp).getBus_Num();
                    stops[cur_stop].q.poll();
                    if (stops[cur_stop].q.isEmpty()) {
                        ArrivalEvent next_arrival = new ArrivalEvent(cur_time + Initialization.drive_interval + Initialization.board_time, EventType.ARRIVAL, (cur_stop + 1) % Initialization.bus_stop_number, cur_bus);
                        q.offer(next_arrival);
                    } else {
                        BoardEvent board = new BoardEvent(cur_time + Initialization.board_time, EventType.BOARD, cur_stop, cur_bus);
                        q.offer(board);
                    }
                }
                //process person event
                else {
                    int cur_stop = ((PersonEvent) temp).getBus_Stop_Num();
                    stops[cur_stop].q.offer(1);
                    double seed = 1. / Initialization.mean_inter_arrival;
                    double interval = Initialization.mean_inter_arrival * RandomGenerator.expRandom(seed);
                    PersonEvent next = new PersonEvent(cur_time + interval, EventType.PERSON, cur_stop);
                    q.offer(next);
                }

            } while (cur_time <= 3600*(i+1));
            System.out.println("After " + (i+1) + "hour(s):");
            System.out.println();
            //show the status of each bus
            for (int j = 0; j < Initialization.bus_number; j++)
                System.out.println("Bus " + j + " is at " + buses[j].Bus_Stop_Num );
            //calculate average number of waiting people
            System.out.println();
            for (int j = 0; j < Initialization.bus_stop_number; j++)
                stops[j].average = stops[j].sum/stops[j].n;
            //show the status of each bus stop
            for (int j = 0; j < Initialization.bus_stop_number; j++)
                System.out.println("Bus stop " + j + " MAX " + stops[j].max + " MIN " + stops[j].min + " AVG " + stops[j].average);
            //reset bus stop
            for (int j = 0; j < Initialization.bus_stop_number; j++) {
                stops[j].min = stops[j].q.size();
                stops[j].max = stops[j].q.size();
                stops[j].average = 0;
                stops[j].n = 0;
                stops[j].sum = 0;
            }
            System.out.println("----------------------------------");
            System.out.println();
        }

    }
}
