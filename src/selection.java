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

@WebServlet(name = "selection")
public class selection extends HttpServlet {


String accept = null;
String reject = null;
Connection conn= null;
int paper= 0;
ResultSet rs = null;
int num=1;
int num1= 0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    PrintWriter out = response.getWriter();
                    accept= request.getParameter("accept");
                    reject= request.getParameter("reject");
                   paper= Integer.parseInt(request.getParameter("paper"));


        String sql = "INSERT INTO selection(s_p_id, acceptance) values (?,?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            PreparedStatement statement1 = conn.prepareStatement(sql);
           statement1.setInt(1, paper);
           if(accept!=null){
               statement1.setInt(2, num);
           }
           else
               statement1.setInt(2, num1);


            //rs = statement1.executeQuery();
            int row = statement1.executeUpdate();
            //int row1 = statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
