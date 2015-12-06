/**
 * Created by whr on 11/1/15.
 */
import java.util.Comparator;
public class myComparator implements Comparator<Event>{
    @Override
    public int compare(Event x, Event y)
    {
        if(x.arriveTime < y.arriveTime)
            return -1;
        else
            return 1;
    }
}
