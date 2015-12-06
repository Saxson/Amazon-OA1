/**
 * Created by whr on 10/19/15.
 * Process query 1
 */
import java.sql.*;
import java.util.Hashtable;
import java.util.Set;
public class Query_1 {
    static public void Execute_Query_1(){
        //use a hashtable to record the result
        Hashtable<String, Data_Structure_1> table = new Hashtable<String, Data_Structure_1>();

        //the database server information
        String usr = "postgres";
        String pwd = "ad3245219";
        String url = "jdbc:postgresql://localhost:5433/sales";
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Success loading Driver!");
        } catch (Exception e) {
            System.out.println("Fail loading Driver!");
            e.printStackTrace();
        }
        //link to database server and process the query 1
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");
            while (rs.next()) {
                //the key is the entry of each record
                String key = rs.getString(1) + "_" + rs.getString(2);
                //if we find the entry we update the value in corresponding data structure
                if(table.containsKey(key)) {
                    Data_Structure_1 item = table.get(key);
                    item.sum += rs.getInt(7);//update the sum
                    item.n++;//update the total number
                    //update the max information
                    if(rs.getInt(7) > item.max_Q){
                        item.max_Q = rs.getInt(7);
                        item.max_state = rs.getString(6);
                        item.max_day = rs.getInt(3);
                        item.max_month = rs.getInt(4);
                        item.max_year = rs.getInt(5);
                    }
                    //update the min information
                    if(rs.getInt(7) < item.min_Q){
                        item.min_Q = rs.getInt(7);
                        item.min_state = rs.getString(6);
                        item.min_day = rs.getInt(3);
                        item.min_month = rs.getInt(4);
                        item.min_year = rs.getInt(5);
                    }
                } else {// if we can't find the entry, we will create a new entry and initialize the values
                    Data_Structure_1 item = new Data_Structure_1();
                    item.cust = rs.getString(1);
                    item.prod = rs.getString(2);
                    item.max_state = rs.getString(6);
                    item.min_state = rs.getString(6);
                    item.max_Q = rs.getInt(7);
                    item.min_Q = rs.getInt(7);
                    item.sum = rs.getInt(7);
                    item.n = 1;
                    item.max_day = rs.getInt(3);
                    item.max_month = rs.getInt(4);
                    item.max_year = rs.getInt(5);
                    item.min_day = rs.getInt(3);
                    item.min_month = rs.getInt(4);
                    item.min_year = rs.getInt(5);
                    table.put(key,item);
                }
            }
            //close the link to the database server
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {// catch the link exception
            System.out.println("Connection URL or username or password errors!");
            e.printStackTrace();
        }
        //output the result in the required form
        System.out.println("CUSTOMER  PRODUCT  MAX_Q  DATE        ST  MIN_Q  DATE        ST  AVG_Q");
        System.out.println("========  =======  =====  ==========  ==  =====  ==========  ==  =====");
        Set<String> keys = table.keySet();
        for(String key : keys) {
            Data_Structure_1 item = table.get(key);
            System.out.println(String.format( "%-8s  %-7s  %5d  %02d/%02d/%d  %s  %5d  %02d/%02d/%d  %s  %5d",item.cust,item.prod,item.max_Q,item.max_month,item.max_day,item.max_year,item.max_state,item.min_Q,
                    item.min_month,item.min_day,item.min_year,item.min_state,item.sum/item.n));
        }
    }
}
