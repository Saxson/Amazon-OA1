/**
 * Created by whr on 10/2/15.
 */
//class BoardEvent is subclass of Event which record the information for a board action
public class BoardEvent extends Event {
    private int Bus_Stop_Num;
    private int Bus_Num;
    public BoardEvent(double tt,EventType t,int bsn,int bn){
        super(tt,t);
        Bus_Stop_Num = bsn;
        Bus_Num = bn;
    }
    public int getBus_Stop_Num(){
        return Bus_Stop_Num;
    }
    public int getBus_Num(){
        return Bus_Num;
    }
}
