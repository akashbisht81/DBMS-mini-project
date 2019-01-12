
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class aaaa {
    public static void main(String args[]) {
        ResultSet rs = null;
        ResultSet rs1 = null;
        String abc = null;
        String abc1 = null;
        String xyz = null;
        String xyz1 = null;
        Connection conn = null;

        try {
            // connects to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);


            // constructs SQL statement
            String sql1 = "Select * from author";

            PreparedStatement statement1 = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statement1.setString(1, "ab@g.com");
            //statement1.setString(2, "1234");


            rs = statement1.executeQuery();

            String sql2 = "select * from paper p, author a where p.author_id= ? and p.author_id=a.a_id";

            PreparedStatement statement2 = conn.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            while (rs.next()) {
                abc = rs.getString("a_first_name");
                abc1 = rs.getString("a_last_name");
                int abc2 = Integer.parseInt(rs.getString("a_id"));
                System.out.println(""+abc+" "+abc1+" "+abc2);

                statement2.setInt(1,abc2);
                rs1 = statement2.executeQuery();
                while(rs1.next()) {
                    int b = rs1.getInt("author_id");
                    int a = rs1.getInt("paper_id");

                    System.out.println("Author id " + b + " has submitted paper as paper id " + a);


                }
                //System.out.println("Author id    Paper_id");
                //System.out.println(""+b+" "+a);

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
