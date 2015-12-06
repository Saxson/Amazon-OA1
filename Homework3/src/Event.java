/**
 * Created by whr on 10/31/15.
 */
public class Event {
    public int pid;
    public int arriveTime;
    public int burstTime;
    public double IO_interval;
    public EventType T;
    public TimeType TT;
    public Event(int n, int at, int bt, EventType t, double i, TimeType tt){
        pid = n;
        arriveTime = at;
        burstTime = bt;
        T = t;
        IO_interval = i;
        TT = tt;
    }
}
