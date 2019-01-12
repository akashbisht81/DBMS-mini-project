import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class testing {
    public static void main(String args[]){

        String Firstname = "popioioiyiiuo98";
        String Lastname = "lastnjhjjamdde";
        Double Mobilenumber = 11188719.8845;
        String Email = "eiliddd@g.com";
        String Pass = "dddddrd";
        String Interest = "inddterest";
        ResultSet rs = null;




        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DBUtil.getConnection(DBType.MYSQLDB);
            //      con = DriverManager.getConnection(dburl, username, password);
            //String query = "INSERT INTO reviewer(rvr_first_name,rvr_last_name,rvr_phone,rvr_email,rvr_pwd,rvr_interest) values(?,?,?,?,?,?)";

            CallableStatement cstmt = con.prepareCall("{call register_reviewer(?,?,?,?,?,?)}");

            //PreparedStatement stmt = con.prepareStatement(query);

            //cstmt.set

            //stmt.setInt(1, 2);

            cstmt.setString(1,Firstname);

            cstmt.setString(2,Lastname);

            cstmt.setDouble(3, Double.parseDouble(String.valueOf(Mobilenumber)));

            cstmt.setString(4,Email);

            cstmt.setString(5,Pass);

            cstmt.setString(6,Interest);
            cstmt.executeQuery();
            //int res= cstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    }

