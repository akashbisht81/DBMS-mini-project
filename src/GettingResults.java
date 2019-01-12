import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GettingResults {
    public static void main(String args[]) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DBUtil.getConnection(DBType.MYSQLDB);
            String sql = "Select * from author where a_email= ? and a_pwd=? ";
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, "aa7@g.com");
            pstmt.setInt(2, 1234);
            rs = pstmt.executeQuery();
           // int format = "%-20s%-10d\n";
            while (rs.next()) {
                System.out.println(rs.getInt("a_id"));
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
    }
}