/**
 * Created by whr on 10/2/15.
 */
//used to define the priority_queue
import java.util.Comparator;
public class myComparator implements Comparator<Event>{
    @Override
    public int compare(Event x, Event y)
    {
        if(x.timeStamp < y.timeStamp)
            return -1;
        else
            return 1;
    }
}
