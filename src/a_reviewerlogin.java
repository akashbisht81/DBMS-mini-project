import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "a_reviewerlogin")
public class a_reviewerlogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paperid=0;
        int tpaperid = 0;
        boolean st = false;
                    PrintWriter out = response.getWriter();
                    String email = request.getParameter("email");
                    String pass = request.getParameter("pass");
                    Connection conn=null;
                    Connection conn1= null;

                    request.getSession(true).setAttribute("email",email);
                    request.getSession(true).setAttribute("pass",pass);



                    ResultSet rs= null;
                    ResultSet rs1 = null;
                    String abc = null;
                    String abc1= null;
                    String xyz = null;
                    String xyz1=null;
                    String email1 = null;
                    String phone = null;
                    String interest = null;

        Blob image = null;

                    try {
                        // connects to the database
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DBUtil.getConnection(DBType.MYSQLDB);


                        // constructs SQL statement
                        String sql1 = "Select * from reviewer where rvr_email= ? and rvr_pwd=? ";

                        PreparedStatement statement1 = conn.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        statement1.setString(1, email);
                        statement1.setString(2, pass);

                        rs = statement1.executeQuery();

                        while (rs.next()) {
                            abc = rs.getString("rvr_first_name");
                            abc1 = rs.getString("rvr_last_name");
                            email1 = rs.getString("rvr_email");
                            phone= rs.getString("rvr_phone");
                            interest = rs.getString("rvr_interest");


                        }

                        request.getSession(true).setAttribute("abc",abc);


                        String sql2 = "Select * from author";
                        PreparedStatement statement2 = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        ResultSet resultSet = statement2.executeQuery();

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
                                "<h2>Welcome "+abc+" "+abc1+
                                "<br>Your E-mail : - "+email1+
                                "<br>Your Phone :- "+phone+
                                "<br>Your Interest :- "+interest+"</h2>"+
                                "<h3> This is the total list of ALL available Papers Uploaded Uptil Now </h3>"+
                                "<h4> Feel free to review any paper !!!</h4><br><br>"+
                                "\n" +
                                "<table>\n" +
                                "  <tr>\n" +
                                "    <th>Author's List</th>\n" +
                                "    <th>Papers</th>\n" +
                                "  </tr>\n");

                        String sql3 = "select * from paper p, author a where p.author_id= ? and p.author_id=a.a_id";
                        PreparedStatement statement3 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

                        out.println("<form action=\"http://localhost:8080/reviewerupdate0\" method=\"post\">");
                        out.println("<input type=\"submit\" value=\"Update Your information here\" />\n" +
                                "");
                        out.println("<br><br></form");

                            while (resultSet.next()) {
                            xyz = resultSet.getString("a_first_name");
                            xyz1 = resultSet.getString("a_last_name");
                            int b = resultSet.getInt("a_id");
                            int abc2 = Integer.parseInt(resultSet.getString("a_id"));

                            statement3.setInt(1,abc2);
                            rs1 = statement3.executeQuery();
                            while(rs1.next()) {
                                int zz = rs1.getInt("author_id");
                                String a = rs1.getString("title");
                                paperid = rs1.getInt("paper_id");
                               // image= rs1.getBlob("photo");
                                //System.out.println("Author id " + b + " has submitted paper as paper id " + a);

                                response.setContentType("image/gif");
                               /// InputStream in = image.getBinaryStream();
                                //int length = (int)image.length();
                               // int buffersize = 1924;
                               // byte[] buffer = new byte[buffersize];
                                //while((length = in.read(buffer)) !=-1) {
                                //  out.write(buffer, 0, length);
                                //}

                                out.println("<form action=\"http://localhost:8080/reviewsystem\" method=\"post\">");
                                out.println("  <tr>\n" +
                                        "    <td>" + xyz + " " + b + " " + "</td>\n" +
                                        "    <td>"+"Paper id: " + paperid +" || Title: "+ a + " || </td>\n" +
                                        "  </tr>\n");



                            }




                    }


                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>");
                        out.println("</title>");
                        out.println("</head>");
                        out.println("<body>");
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
                                "<h3><center>SUBMIT THE REVIEW On a Scale of 1 to 5 </center></h3>" +
                                "\n");

                        out.println("<center>");

                        out.println("\n" +

                                "<b>Enter the paper id for which you want to submit the review:</b> <input type=\"number\" name=\"paperid\" value=\"\"><br>\n" +
                                "\n" +
                                "\n");
                        out.println( "<form>\n" +
                                "<ul class=\"form-style-1\">\n" +
                                "  \n" +
                                "  <center>\n" +
                                "    \n" +
                                "        <label>Clarity ::::::::::::</label>\n" +
                                "        <select name=\"clarity\" class=\"field-select\">\n" +
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
                                "        <select name=\"relevance\" class=\"field-select\">\n" +
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
                                "        <select name=\"merit\" class=\"field-select\">\n" +
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
                        out.println("</form>");

                    }catch (SQLException e){

            System.out.println(""+e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // System.out.println("" +xyz+ "" + xyz1 + " " + abc2);
    ///list
    //out.println("<html>");
    //out.println("<title>");
    //out.println("</title>");
    //out.println("<head>");
    //out.println("</head>");
    //out.println("<body>");




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
