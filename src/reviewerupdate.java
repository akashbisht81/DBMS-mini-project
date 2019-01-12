import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "reviewerupdate")
public class reviewerupdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int set = 0;
        PrintWriter out = response.getWriter();
        String r_f_n = request.getParameter("firstname");
        String r_l_n = request.getParameter("lastname");
        String r_p_n = request.getParameter("telephone");
        String r_e_id = request.getParameter("email");
        String r_pwd = request.getParameter("password");
        String r_intrst = request.getParameter("interest");

        HttpSession session = request.getSession();
        //String s = (String)session.getAttribute("e");
        String email= (String)session.getAttribute("email");
        String pass= (String)session.getAttribute("pass");


        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);


            // constructs SQL statement
            String sql1 = "UPDATE reviewer SET rvr_first_name = ?, rvr_last_name = ?,rvr_phone= ?,rvr_email= ?,rvr_pwd= ?,rvr_interest= ? where rvr_email= ? and rvr_pwd = ? ";
            PreparedStatement statement1 = conn.prepareStatement(sql1, ResultSet.CONCUR_READ_ONLY, ResultSet.CONCUR_UPDATABLE);
            statement1.setString(1, r_f_n);
            statement1.setString(2, r_l_n);
            statement1.setString(3, r_p_n);
            statement1.setString(4, r_e_id);
            statement1.setString(5, r_pwd);
            statement1.setString(6, r_intrst);
            statement1.setString(7,email);
            statement1.setString(8,pass);
            statement1.executeUpdate();


            set = 1;
            if(set == 1){
                out.println("<html>");
                out.println("<title>");
                out.println("</title>");
                out.println("<body>");
                out.println("<h1><center> Successfully Updated !!! </center><br><br>");
                out.println("<h1><center><a href=\"http://localhost:63342/mini_project/loginindex1.html\">Go to Login Page Again</a></center>");
                //+abc+""+s+""+s1);
                out.println("</body>");
                out.println("</html>");

            }

        } catch (SQLException e){
            System.out.println(""+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
