import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by whr on 11/12/15.
 */
public class Query_3 {
    public static void Execute_Query_3() {
        //use a hashtable to record the result
        Hashtable<String, Data_Structure_3> table = new Hashtable<String, Data_Structure_3>();
        ArrayList<Data_Structure_3> record = new ArrayList<Data_Structure_3>();
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
        //link to database server and process the query 3
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");
            while (rs.next()) {
                //the key is the entry of each record
                String key = rs.getString(1) + "_" + rs.getString(2);
                //if we find the entry we update the value in corresponding data structure
                if(table.containsKey(key)) {
                    Data_Structure_3 item = table.get(key);
                    item.cust_n++;
                    item.cust_sum += rs.getInt(7);
                } else {// if we can't find the entry, we will create a new entry and initialize the values
                    Data_Structure_3 item = new Data_Structure_3();
                    item.cust = rs.getString(1);
                    item.prod = rs.getString(2);
                    table.put(key,item);
                    record.add(item);
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

        for(int i = 0; i < record.size(); i++) {
            for(int j = 0; j < record.size(); j++) {
                if(i != j) {
                    if(record.get(j).cust.equals(record.get(i).cust)) {
                        record.get(i).other_prod_sum += record.get(j).cust_sum;
                        record.get(i).other_prod_n += record.get(j).cust_n;
                    }
                    if(record.get(j).prod.equals(record.get(i).prod)) {
                        record.get(i).other_cust_sum += record.get(j).cust_sum;
                        record.get(i).other_cust_n += record.get(j).cust_n;
                    }
                }
            }
        }
        //output the result in the required form
        System.out.println("CUSTOMER  PRODUCT  CUST_AVG  OTHER_CUST_AVG  OTHER_PROD_AVG");
        System.out.println("========  =======  ========  ==============  ==============");
        for(int i = 0; i < record.size(); i++) {
            Data_Structure_3 item = record.get(i);
            System.out.print(String.format("%-8s  %-7s  ", item.cust, item.prod));
            if(item.cust_n == 0) {
                System.out.print(String.format("%-8s  ", "NULL"));
            } else {
                System.out.print(String.format("%8d  ", item.cust_sum / item.cust_n));
            }
            if(item.other_cust_n == 0) {
                System.out.print(String.format( "%-14s  ","NULL"));
            } else {
                System.out.print(String.format("%14d  ", item.other_cust_sum / item.other_cust_n));
            }
            if(item.other_prod_n == 0) {
                System.out.print(String.format("%-14s", "NULL"));
            } else {
                System.out.print(String.format("%14d", item.other_prod_sum / item.other_prod_n));
            }
            System.out.println();
        }
    }
}
