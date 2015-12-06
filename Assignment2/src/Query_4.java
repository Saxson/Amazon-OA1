import java.sql.*;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by whr on 11/12/15.
 */
public class Query_4 {
    static public void Execute_Query_4() {
        //use a hashtable to record the result
        Hashtable<String, Data_Structure_4> table = new Hashtable<String, Data_Structure_4>();
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
        //link to database server and process the query 4
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales");
            while (rs.next()) {
                //the key is the entry of each record
                String key = rs.getString(1) + "_" + rs.getString(2);
                //if we find the entry we update the value in corresponding data structure
                if(table.containsKey(key)) {
                    Data_Structure_4 item = table.get(key);
                    if(rs.getInt(4) >= 1 && rs.getInt(4) <= 3) {
                        item.Q1_sum += rs.getInt(7);
                        item.Q1_n++;
                    }
                    if(rs.getInt(4) >= 4 && rs.getInt(4) <= 6) {
                        item.Q2_sum += rs.getInt(7);
                        item.Q2_n++;
                    }
                    if(rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {
                        item.Q3_sum += rs.getInt(7);
                        item.Q3_n++;
                    }
                    if(rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {
                        item.Q4_sum += rs.getInt(7);
                        item.Q4_n++;
                    }

                } else {// if we can't find the entry, we will create a new entry and initialize the values
                    Data_Structure_4 item = new Data_Structure_4();
                    item.cust = rs.getString(1);
                    item.prod = rs.getString(2);
                    if(rs.getInt(4) >= 1 && rs.getInt(4) <= 3) {
                        item.Q1_sum += rs.getInt(7);
                        item.Q1_n++;
                    }
                    if(rs.getInt(4) >= 4 && rs.getInt(4) <= 6) {
                        item.Q2_sum += rs.getInt(7);
                        item.Q2_n++;
                    }
                    if(rs.getInt(4) >= 7 && rs.getInt(4) <= 9) {
                        item.Q3_sum += rs.getInt(7);
                        item.Q3_n++;
                    }
                    if(rs.getInt(4) >= 10 && rs.getInt(4) <= 12) {
                        item.Q4_sum += rs.getInt(7);
                        item.Q4_n++;
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
        System.out.println("CUSTOMER  PRODUCT  QUARTER  BEFORE_AVG  AFTER_AVG");
        System.out.println("========  =======  =======  ==========  =========");
        Set<String> keys = table.keySet();
        for(String key : keys) {
            Data_Structure_4 item = table.get(key);
            for(int i = 1; i <= 4; i++) {
                System.out.print(String.format("%-8s  %-7s  ", item.cust, item.prod));
                System.out.print("Q" + i + "       ");
                if(i == 1) {
                    System.out.print(String.format("%10s  ", "NULL"));
                    if(item.Q2_n != 0) {
                        System.out.print(String.format("%9d", item.Q2_sum / item.Q2_n));
                    } else {
                        System.out.print(String.format("%9s", "NULL"));
                    }
                }
                if(i == 2) {
                    if(item.Q1_n != 0) {
                        System.out.print(String.format("%10d  ", item.Q1_sum/item.Q1_n));
                    } else {
                        System.out.print(String.format("%10s  ", "NULL"));
                    }
                    if(item.Q3_n != 0) {
                        System.out.print(String.format("%9d", item.Q3_sum / item.Q3_n));
                    } else {
                        System.out.print(String.format("%9s", "NULL"));
                    }
                }
                if(i == 3) {
                    if(item.Q2_n != 0) {
                        System.out.print(String.format("%10d  ", item.Q2_sum / item.Q2_n));
                    } else {
                        System.out.print(String.format("%10s  ", "NULL"));
                    }
                    if(item.Q4_n != 0) {
                        System.out.print(String.format("%9d", item.Q4_sum / item.Q4_n));
                    } else {
                        System.out.print(String.format("%9s", "NULL"));
                    }
                }
                if(i == 4) {
                    if(item.Q3_n != 0) {
                        System.out.print(String.format("%10d  ", item.Q3_sum / item.Q3_n));
                    } else {
                        System.out.print(String.format("%10s  ", "NULL"));
                    }
                    System.out.print(String.format("%9s", "NULL"));
                }
                System.out.println();
            }
        }

    }
}
