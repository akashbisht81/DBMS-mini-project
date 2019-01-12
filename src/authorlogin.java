
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "authorlogin")
public class authorlogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        boolean st = false;
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Connection conn=null;

         request.getSession(true).setAttribute("email",email);
         request.getSession(true).setAttribute("pass",pass);


    ResultSet rs = null;
    ResultSet rs1 = null;
    String abc = null;
    String abc1= null;
    String aid=null;
    String paperid = null;

        try {
            // connects to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);


            // constructs SQL statement
            String sql1 = "Select * from author where a_email= ? and a_pwd=? ";
            PreparedStatement statement1 = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement1.setString(1, email);
            statement1.setString(2, pass);



            rs = statement1.executeQuery();

            while (rs.next()) {
                abc = rs.getString("a_first_name");
                abc1 = rs.getString("a_last_name");
                aid= rs.getString("a_id");
            }
        }catch (SQLException e){
            System.out.println(""+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        //String e=(String)session.getAttribute("email"+email);
        //session.setAttribute("e",e);
        //String p=(String)session.getAttribute("pass"+pass);
        //session.setAttribute("p",p);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Greetings</title>");
            out.println("</head>");
            out.println("<body>");

        out.println("<h1><u><center>Conference Review System</h1></center></u><br><br>");

    out.print("<center>");
                out.print(" <form method=\"post\" action=\"FileUploadDBServlet\" enctype=\"multipart/form-data\">"+
                       "<h1><p><center>Welcome "+abc+" "+abc1+"</p><center><h1>"+
                        "<table border=\"0\">"+
                        "<tr>"+
                        "<td>Enter Your Paper Title: </td>"+
                        "<td><input type=\"text\" name=\"title\" size=\"50\"/></td>"+
                        " </tr>"+


                                "<tr>"+
                                "<td>Enter your Abstract: </td>"+
                                "<td><input type=\"text\" name=\"abstract\" size=\"50\"/></td>"+
                                "</tr>"+



                                "<tr>"+
                                "<td>Give Your File Name: </td>"+
                                "<td><input type=\"text\" name=\"filename\" size=\"50\"/></td>"+
                                " </tr>"+
                        "<tr>"+
                        "<td>File (only in JPEG format): </td>"+
                        "<td><input type=\"file\" name=\"photo\" size=\"50\"/></td>"+
                                " </tr>"+





                        "<tr>"+
                                "<td colspan=\"2\">"+
                                "<input type=\"submit\" value=\"Save\">"+

                        "</td>"+
                                " </tr>"+
                                "</table>"+
                        "</form>"+
                                "</center>"
                        );






    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
