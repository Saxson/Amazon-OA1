/**
 * Created by whr on 10/15/15.
 * Data_Structure_1 is designed to store information for query 1
 */
public class Data_Structure_1 {
    public String cust;
    public String prod;
    public String max_state;//record the state with max quantity
    public String min_state;//record the state with min quantity
    public int max_Q = Integer.MIN_VALUE;//record the max quantity
    public int min_Q = Integer.MAX_VALUE;//record the min quantity
    public int sum;//record the total quantity, will be used to calculate the average
    public int n;//record the total num
    public int max_day;//record the day with max quantity
    public int max_month;//record the month with max quantity
    public int max_year;//record the year with max quantity
    public int min_day;//record the day with min quantity
    public int min_month;//record the month with min quantity
    public int min_year;//record the year with min quantity
}

