import java.sql.*;
import java.util.HashSet;

/**
 * Created by whr on 4/21/15.
 */
public class QueryProcess {
    String usr ="whr";
    String pwd ="";
    String url ="jdbc:postgresql://localhost:5432/whr";
    MF_Structure[] mf = new MF_Structure[500];
    public static void main(String[] args){
        QueryProcess dbmsass1 = new QueryProcess();
        dbmsass1.connect();
        dbmsass1.retreive();
    }

    //Function to connect to the database

    void connect(){
        try {
            Class.forName("org.postgresql.Driver");     //Loads the required driver
            System.out.println("Success loading Driver!");
        } catch(Exception exception) {
            System.out.println("Fail loading Driver!");
            exception.printStackTrace();
        }
    }

    //Function to retreive from the database and process on the resultset received

    void retreive(){

        try {
            Connection con = DriverManager.getConnection(url, usr, pwd);    //connect to the database using the password and username
            System.out.println("Success connecting server!");
            ResultSet rs;          			 //resultset object gets the set of values retreived from the database
            boolean more;
            int i = 0;
            HashSet mp = new HashSet();
            Statement st = con.createStatement();   //statement created to execute the query
            String ret = "select * from sales";
            rs = st.executeQuery(ret);              //executing the query
            more=rs.next();                         //checking if more rows available
            System.out.printf("%-8s","Customer  ");             //left aligned
            System.out.printf("%-11s","sum_1_quant  ");              //left aligned
            System.out.printf("%-11s","sum_2_quant  ");          //left aligned
            System.out.printf("%-11s%n", "sum_3_quant  ");                //left aligned
            System.out.println("========  ===========  ===========  =========== ");

            // build entries
            while(more) {
                if(!mp.contains(rs.getString(1))){
                    mf[i] = new MF_Structure();
                    mf[i++].cust = rs.getString(1);
                    mp.add(rs.getString(1));
                }
                more=rs.next();
            }
            //round 1 count the sum and avg of NY
            rs = st.executeQuery(ret);              //executing the query
            more=rs.next();
            while(more) {
                for(int j = 0;j < i;j++){
                    if(mf[j].cust.equals(rs.getString(1)) && rs.getString(6).equals("NY")){
                        mf[j].count1++;
                        mf[j].sum_1_quant+= rs.getInt(7);
                    }
                }
                more=rs.next();
            }
            for(int m = 0;m < i;m++)
                mf[m].avg_1_quant = mf[m].count1 == 0? 0:mf[m].sum_1_quant/mf[m].count1;
            // round 2 count the sum of NJ
            rs = st.executeQuery(ret);              //executing the query
            more=rs.next();
            while(more) {
                for(int j = 0;j < i;j++){
                    if(mf[j].cust.equals(rs.getString(1)) && rs.getString(6).equals("NJ"))
                        mf[j].sum_2_quant+= rs.getInt(7);
                }
                more=rs.next();
            }
            //round 3 count the sum  and avg of CT
            rs = st.executeQuery(ret);              //executing the query
            more=rs.next();
            while(more) {
                for(int j = 0;j < i;j++){
                    if(mf[j].cust.equals(rs.getString(1)) && rs.getString(6).equals("CT")) {
                        mf[j].count2++;
                        mf[j].sum_3_quant += rs.getInt(7);
                    }
                }
                more=rs.next();
            }
            for(int m = 0;m < i;m++)
                mf[m].avg_3_quant = mf[m].count2 == 0? 0:mf[m].sum_3_quant/mf[m].count2;
            //output the result
           for(int j = 0;j < i;j++){
                if(mf[j].sum_1_quant > 2 * mf[j].sum_2_quant || mf[j].avg_1_quant > mf[j].avg_3_quant){
                    System.out.printf("%-8s  ",mf[j].cust);
                    System.out.printf("%-11s  ",mf[j].sum_1_quant);
                    System.out.printf("%-11s  ",mf[j].sum_2_quant);
                    System.out.printf("%-11s%n",mf[j].sum_3_quant);
               }
            }
        } catch(SQLException e) {
            System.out.println("Connection URL or username or password errors!");
            e.printStackTrace();
        }
    }
}
