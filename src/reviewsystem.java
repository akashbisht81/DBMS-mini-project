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

@WebServlet(name = "reviewsystem")
public class reviewsystem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        boolean st = false;
        PrintWriter out = response.getWriter();
        int paperid = Integer.parseInt(request.getParameter("paperid"));
        int clarity = Integer.parseInt(request.getParameter("clarity"));
        int relevance = Integer.parseInt(request.getParameter("relevance"));
        int merit = Integer.parseInt(request.getParameter("merit"));
       // String pass = request.getParameter("pass");
        Connection conn = null;
        Connection conn1 = null;
        int rvrid = 0;
        //request.getSession(true).setAttribute("email", email);
        //request.getSession(true).setAttribute("pass", pass);

        HttpSession session = request.getSession();
        //String s = (String)session.getAttribute("e");
        String pass=(String)session.getAttribute("pass");
        String abc= (String) session.getAttribute("abc");
        String email =(String) session.getAttribute("email");




        ResultSet rs = null;
        ResultSet rs1 = null;
        //String abc = null;
        String abc1 = null;
        String xyz = null;
        String xyz1 = null;

        try {
            // connects to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);




            // constructs SQL statement
            String sql1 = "Select * from reviewer where rvr_email= ? and rvr_pwd=? ";

            PreparedStatement statement1 = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement1.setString(1, email);
            statement1.setString(2, pass);


            rs = statement1.executeQuery();

            while (rs.next()) {
                abc = rs.getString("rvr_first_name");
                abc1 = rs.getString("rvr_last_name");
                rvrid= rs.getInt("rvr_id");
            }

            String sql = "INSERT INTO review (clarity,relevance,technical_rate,Rrvr_id,p_id) values (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);


            statement.setInt(1, clarity);
            statement.setInt(2, relevance);
            statement.setInt(3,merit);
            statement.setInt(4,rvrid);
            statement.setInt(5,paperid);

            int row = statement.executeUpdate();


            
            //String sql2 = "Select * from author";
            //PreparedStatement statement2 = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statement2.setString(1, email);
            //statement2.setString(2, pass);
            //ResultSet resultSet = statement2.executeQuery();

            //String sql2 = "Select * from author";
            //PreparedStatement statement2 = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //statement2.setString(1, email);
            //statement2.setString(2, pass);
            //ResultSet resultSet = statement2.executeQuery();



            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><center>Conference Review System</h1></center><br><br>");
            out.println("<h2><u><center>REVIEW SYSTEM<center></u></h2><br><br>");
            out.println("<table style=\"width:100%\">");
            out.println("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table {\n" +
                    "    font-family: arial, sans-serif;\n" +
                    "    border-collapse: collapse;\n" +
                    "    width: 100%;\n" +
                    "}\n" +
                    "\n" +
                    "td, th {\n" +
                    "    border: 1px solid #dddddd;\n" +
                    "    text-align: left;\n" +
                    "    padding: 8px;\n" +
                    "}\n" +
                    "\n" +
                    "tr:nth-child(even) {\n" +
                    "    background-color: #dddddd;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h1>REVIEW SUBMITTED ! " +"</h1>" +
                    //"<h3><center>SUBMIT THE REVIEW On a Scale of 1 to 5 </center></h3>" +
                    "\n");
/*
            out.println("<center>");
            out.println( "<form>\n" +
                    "<ul class=\"form-style-1\">\n" +
                    "  \n" +
                    "  <center>\n" +
                    "    \n" +
                    "        <label>Clarity ::::::::::::</label>\n" +
                    "        <select name=\"field4\" class=\"field-select\">\n" +
                    "        <option value=\"1\">1</option>\n" +
                    "        <option value=\"2\">2</option>\n" +
                    "        <option value=\"3\">3</option>\n" +
                    "        <option value=\"4\">4</option>\n" +
                    "        <option value=\"5\">5</option>\n" +
                    "        </select>\n" +
                    " \n" +
                    "  \n" +
                    "    \n" +
                    "        <label>Relevance :::::::</label>\n" +
                    "        <select name=\"field4\" class=\"field-select\">\n" +
                    "        <option value=\"1\">1</option>\n" +
                    "        <option value=\"2\">2</option>\n" +
                    "        <option value=\"3\">3</option>\n" +
                    "        <option value=\"4\">4</option>\n" +
                    "        <option value=\"5\">5</option>\n" +
                    "\n" +
                    "        </select>\n" +
                    "    \n" +
                    "  \n" +
                    "    \n" +
                    "        <label>Technical merit </label>\n" +
                    "        <select name=\"field4\" class=\"field-select\">\n" +
                    "        <option value=\"1\">1</option>\n" +
                    "        <option value=\"2\">2</option>\n" +
                    "        <option value=\"3\">3</option>\n" +
                    "        <option value=\"4\">4</option>\n" +
                    "        <option value=\"5\">5</option>\n" +
                    "\n" +
                    "        </select>\n" +
                    "    </li>\n" +
                    "   \n" +
                    "    <br>\n" +
                    "  <br>\n" +
                    "  \n" +
                    "        <input type=\"submit\" value=\"Submit\" />\n" +
                    "  </center>\n" +
                    "</ul>\n" +
                    "</form>");
            out.println("</center>");
                    /*
                    +
                    "  <tr>\n" +
                    "    <td>Centro comercial Moctezuma</td>\n" +
                    "    <td>Francisco Chang</td>\n" +
                    "    <td>Mexico</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Ernst Handel</td>\n" +
                    "    <td>Roland Mendel</td>\n" +
                    "    <td>Austria</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Island Trading</td>\n" +
                    "    <td>Helen Bennett</td>\n" +
                    "    <td>UK</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Laughing Bacchus Winecellars</td>\n" +
                    "    <td>Yoshi Tannamuri</td>\n" +
                    "    <td>Canada</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Magazzini Alimentari Riuniti</td>\n" +
                    "    <td>Giovanni Rovelli</td>\n" +
                    "    <td>Italy</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n");
                    */
            // out.println("LIST of AUTHORS<br><br>");
            // out.println("</body>");
            //out.println("<html>");


            //String sql3 = "select * from paper p, author a where p.author_id= ? and p.author_id=a.a_id";
            //  PreparedStatement statement3 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }catch (SQLException e){
            System.out.println(""+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
