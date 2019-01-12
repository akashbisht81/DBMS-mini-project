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

@WebServlet(name = "Loginservlet1")
public class Loginservlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Connection conn= null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conferencesystem", "root", "root");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);
            PreparedStatement pst = conn.prepareStatement("Select rvr_email,rvr_pwd from reviewer where rvr_email=? and rvr_pwd= ? ");
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                RequestDispatcher rd2=request.getRequestDispatcher("/a_reviewerlogin");
               //response.sendRedirect("\a_reviewerlogin.java");
                //response.sendRedirect("reviewer.jsp");
                rd2.include(request,response);
                /*
                out.println("<html>");
                out.println("<head></head>");
                out.println("<body>");
                out.println("<h1>Correct login credentials</h1>");
                out.println("</body>");
                out.println("</html>");
                */
            }
            else {
                out.println("Incorrect login credentials");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
