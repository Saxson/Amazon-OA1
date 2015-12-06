/**
 * Created by whr on 10/2/15.
 */
//class record the status of each bus stop
import java.util.Queue;
import java.util.LinkedList;
public class BusStop {
    public int max;
    public int min;
    public int sum;
    public int n;
    public int average;
    public Queue<Integer> q = new LinkedList<Integer>();
    public BusStop(){
        min = 0;
        max = 0;
        sum = 0;
        n = 0;
        average = 0;
        q= new LinkedList<Integer>();
    }
}