import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;


@WebServlet(name = "Register1")
public class Register1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String Firstname = request.getParameter("firstname");
        String Lastname = request.getParameter("lastname");
        Double Mobilenumber = Double.valueOf(request.getParameter("mobilenumber"));
        String Email = request.getParameter("emailid");
        String Pass = request.getParameter("password");
        String Interest = request.getParameter("interest");
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



            out.println("<html>");
            out.println("<body>");
            out.println("<h1><b><u>Confirmation Details</u></b></h1>");
            //out.println("" + Firstname);
            //out.println("" + Lastname);
            //out.println("" + Email);
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");
            out.println("<h2>Your have Successfully register as a REVIEWER<h2>");
           // int res= cstmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
