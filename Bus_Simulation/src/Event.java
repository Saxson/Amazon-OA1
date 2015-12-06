/**
 * Created by whr on 10/2/15.
 */
//The father class for arrival,board,person event
public class Event {
    protected double timeStamp;
    protected EventType T;
    public Event(double tt,EventType t){
        timeStamp = tt;
        T = t;
    }
    public double getTimeStamp(){
        return timeStamp;
    }
    public EventType getEventType(){
        return T;
    }
}
