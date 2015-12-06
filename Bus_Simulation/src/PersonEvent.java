/**
 * Created by whr on 10/2/15.
 */
//class PersonEvent is subclass of Event which record the information for a person action
public class PersonEvent extends Event {
    private int Bus_Stop_Num;
    public PersonEvent(double tt,EventType t,int bsn){
        super(tt,t);
        Bus_Stop_Num = bsn;

    }
    public int getBus_Stop_Num(){
        return Bus_Stop_Num;
    }
}
