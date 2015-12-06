import java.sql.*;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by whr on 11/12/15.
 */
public class Query_5 {
    static public void Execute_Query_5() {
        //use a hashtable to record the result
        Hashtable<String, Data_Structure_5> table = new Hashtable<String, Data_Structure_5>();
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
        //link to database server and process the query 5 in first scan
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");
            while (rs.next()) {
                //the key is the entry of each record
                int month = rs.getInt(4);
                int q = 0;
                if(month >= 1 && month <= 3) {
                    q = 1;
                }
                if(month >= 4 && month <= 6) {
                    q = 2;
                }
                if(month >= 7 && month <= 9) {
                    q = 3;
                }
                if(month >= 10 && month <= 12) {
                    q = 4;
                }
                String key = rs.getString(1) + "_" + rs.getString(2) + "_" + q;
                //if we find the entry we update the value in corresponding data structure
                if(table.containsKey(key)) {
                    Data_Structure_5 item = table.get(key);
                    item.cur_sum += rs.getInt(7);
                    item.cur_n++;
                    item.cur_min = Math.min(item.cur_min,rs.getInt(7));
                } else {// if we can't find the entry, we will create a new entry and initialize the values
                    Data_Structure_5 item = new Data_Structure_5();
                    item.cust = rs.getString(1);
                    item.prod = rs.getString(2);
                    item.quarter = q;
                    item.cur_sum += rs.getInt(7);
                    item.cur_n++;
                    item.cur_min = Math.min(item.cur_min,rs.getInt(7));
                    table.put(key,item);
                }
            }
            rs.close();
            //for the second scan;
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM sales");
            while (rs2.next()) {
                //the key is the entry of each record
                int month = rs2.getInt(4);
                if(month >= 1 && month <= 3) {
                    String key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 2;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_prev++;
                        }
                    }
                }
                if(month >= 4 && month <= 6) {
                    String key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 1;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_next++;
                        }
                    }
                    key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 3;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_prev++;
                        }
                    }
                }
                if(month >= 7 && month <= 9) {
                    String key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 2;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_next++;
                        }
                    }
                    key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 4;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_prev++;
                        }
                    }
                }
                if(month >= 10 && month <= 12) {
                    String key = rs2.getString(1) + "_" + rs2.getString(2) + "_" + 3;
                    if(table.containsKey(key)) {
                        Data_Structure_5 item = table.get(key);
                        if(rs2.getInt(7) >= item.cur_min && rs2.getInt(7) <= (item.cur_sum/item.cur_n)) {
                            item.count_next++;
                        }
                    }
                }
            }
            //close the link to the database server
            rs2.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {// catch the link exception
            System.out.println("Connection URL or username or password errors!");
            e.printStackTrace();
        }
        //output the result in the required form
        System.out.println("CUSTOMER  PRODUCT  QUARTER  BEFORE_TOT  AFTER_TOT");
        System.out.println("========  =======  =======  ==========  =========");
        Set<String> keys = table.keySet();
        for(String key : keys) {
            Data_Structure_5 item = table.get(key);
            System.out.print(String.format("%-8s  %-7s  ", item.cust, item.prod));
            System.out.print("Q" + item.quarter + "       ");
            if(item.quarter == 1) {
                System.out.print(String.format("%10d  ", 0));
                System.out.print(String.format("%9d", item.count_next));
            }
            if(item.quarter == 2) {
                System.out.print(String.format("%10d  ", item.count_prev));
                System.out.print(String.format("%9d", item.count_next));
            }
            if(item.quarter == 3) {
                System.out.print(String.format("%10d  ", item.count_prev));
                System.out.print(String.format("%9d", item.count_next));
            }
            if(item.quarter == 4) {
                System.out.print(String.format("%10d  ", item.count_prev));
                System.out.print(String.format("%9d", 0));
            }
            System.out.println();
        }
    }
}
