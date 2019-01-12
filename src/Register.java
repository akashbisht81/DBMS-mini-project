import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;


@WebServlet(name = "Register")
public class Register extends HttpServlet {
    //static String username="root";
    //static String password="root";
    //static String dburl= "jdbc:mysql://localhost:3306/conferencesystem";
    //static String mydriver = "com.mysql.cj.jdbc.Driver";
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String Firstname = request.getParameter("firstname");
        String Lastname = request.getParameter("lastname");
        String Mobilenumber = request.getParameter("mobilenumber");
        String Email = request.getParameter("emailid");
        String Pass = request.getParameter("password");





        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DBUtil.getConnection(DBType.MYSQLDB);
      //      con = DriverManager.getConnection(dburl, username, password);
            String query = "INSERT INTO author(a_first_name,a_last_name,a_phone_no,a_email,a_pwd) values(?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(query);

            //stmt.setInt(1, 2);

            stmt.setString(1,Firstname);

            stmt.setString(2,Lastname);

            stmt.setDouble(3, Double.parseDouble(Mobilenumber));

            stmt.setString(4,Email);

            stmt.setString(5,Pass);

            out.println("<html>");
            out.println("<body>");
            out.println("<h1><b><u>Confirmation Details</u></b></h1>");
            //out.println("" + Firstname);
            //out.println("" + Lastname);
            //out.println("" + Email);
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
            out.println("<h2>Your Record has been successfully inserted<h2>");
            int res= stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}