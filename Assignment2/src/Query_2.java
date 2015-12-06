/**
 * Created by whr on 10/19/15.
 * Process query 2
 */
import java.sql.*;
import java.util.Hashtable;
import java.util.Set;
public class Query_2 {
    static public void Execute_Query_2() {
        //use a hashtable to record the result
        Hashtable<String, Data_Structure_2> table = new Hashtable<String, Data_Structure_2>();
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
                    Data_Structure_2 item = table.get(key);
                    if(rs.getString(6).equals("NY")) {//update the max information in NY between 2000 and 2005
                        if(rs.getInt(7) > item.max_NY && rs.getInt(5) >= 2000 && rs.getInt(5) <= 2005) {
                            item.max_NY = rs.getInt(7);
                            item.NY_day = rs.getInt(3);
                            item.NY_month = rs.getInt(4);
                            item.NY_year = rs.getInt(5);
                        }
                    }
                    if(rs.getString(6).equals("NJ")) {//update the max information in NJ between 2000 and 2005
                        if(rs.getInt(7) > item.max_NJ && rs.getInt(5) >= 2000 && rs.getInt(5) <= 2005) {
                            item.max_NJ = rs.getInt(7);
                            item.NJ_day = rs.getInt(3);
                            item.NJ_month = rs.getInt(4);
                            item.NJ_year = rs.getInt(5);
                        }
                    }
                    if(rs.getString(6).equals("CT")) {//update the min information in CT
                        if(rs.getInt(7) < item.min_CT) {
                            item.min_CT = rs.getInt(7);
                            item.CT_day = rs.getInt(3);
                            item.CT_month = rs.getInt(4);
                            item.CT_year = rs.getInt(5);
                        }
                    }
                } else {// if we can't find the entry, we will create a new entry and initialize the values
                    Data_Structure_2 item = new Data_Structure_2();
                    item.cust = rs.getString(1);
                    item.prod = rs.getString(2);
                    //initialize the max information in NY between 2000 and 2005
                    if(rs.getString(6).equals("NY") && rs.getInt(5) >= 2000 && rs.getInt(5) <= 2005) {
                        item.max_NY = rs.getInt(7);
                        item.NY_day = rs.getInt(3);
                        item.NY_month = rs.getInt(4);
                        item.NY_year = rs.getInt(5);
                    }
                    //initialize the max information in NJ between 2000 and 2005
                    if(rs.getString(6).equals("NJ") && rs.getInt(5) >= 2000 && rs.getInt(5) <= 2005) {
                        item.max_NJ = rs.getInt(7);
                        item.NJ_day = rs.getInt(3);
                        item.NJ_month = rs.getInt(4);
                        item.NJ_year = rs.getInt(5);
                    }
                    //initialize the min information in CT
                    if(rs.getString(6).equals("CT")) {
                        item.min_CT = rs.getInt(7);
                        item.CT_day = rs.getInt(3);
                        item.CT_month = rs.getInt(4);
                        item.CT_year = rs.getInt(5);
                    }
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
        System.out.println("CUSTOMER  PRODUCT  NY_MAX  DATE        NJ_MAX  DATE        CT_MIN  DATE      ");
        System.out.println("========  =======  ======  ==========  ======  ==========  ======  ==========");
        Set<String> keys = table.keySet();
        for(String key : keys) {
            Data_Structure_2 item = table.get(key);
            System.out.print(String.format("%-8s  %-7s  ", item.cust, item.prod));
            if (item.max_NY == Integer.MIN_VALUE) System.out.print(String.format("%6s  %10s  ", "NULL", "NULL"));
            else
                System.out.print(String.format("%6d  %02d/%02d/%d  ", item.max_NY, item.NY_month, item.NY_day, item.NY_year));
            if (item.max_NJ == Integer.MIN_VALUE) System.out.print(String.format("%6s  %10s  ", "NULL", "NULL"));
            else
                System.out.print(String.format("%6d  %02d/%02d/%d  ", item.max_NJ, item.NJ_month, item.NJ_day, item.NJ_year));
            if (item.min_CT == Integer.MAX_VALUE) System.out.print(String.format("%6s  %10s", "NULL", "NULL"));
            else
                System.out.print(String.format("%6d  %02d/%02d/%d", item.min_CT, item.CT_month, item.CT_day, item.CT_year));
            System.out.println();
        }
    }
}
