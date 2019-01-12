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

@WebServlet(name = "onepcchair")
public class onepcchair extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paperid = 0;
        int tpaperid = 0;
        boolean st = false;

        Connection conn = null;


        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        ResultSet rs3 = null;
        ResultSet rs4 = null;
        String abc = null;
        String abc1 = null;
        String xyz = null;
        String xyz1 = null;


        String an = null;
        int pd = 0;
        int pd2=0;
        String pd1 = null;
        String rr = null;
        String name = null;
        int ad = 0;
        int cy = 0;
        int re = 0;
        int tr = 0;

        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Connection conn1 = null;

        request.getSession(true).setAttribute("email", email);
        request.getSession(true).setAttribute("pass", pass);


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
            }

            request.getSession(true).setAttribute("abc", abc);


            out.println("<html>");
            out.println("<head>");
            out.println("<title>");
            out.println("</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><center>Conference Review System</h1></center><br><br>");
           // out.println("<h2><u><center>FETCHED DATA THROUGH STORED PROCEDURE USING CALLABLE STATEMENT.<br> DETAILS OF THE REVIEWS.<center></u></h2><br><br>");
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
                    "<h2>Welcome ONE PC CHAIR " + "</h2>" +
                    "<h3> This is the total list of ALL available REVIEWS Uptil Now From the Database</h3>" +
                    "<h4> Feel free to accept any paper !!!</h4><br><br>" +
                    "\n" +
                    "<table>\n" +
                    "  <tr>\n" +
                    "    <th>Author Name</th>\n" +
                    "    <th>Paper_name</th>\n" +
                    "    <th>Reviewer</th>\n" +
                    "    <th>Clarity</th>\n" +
                    "    <th>Relevance</th>\n" +
                    "    <th>Technical Rate </th>\n" +
                    // "    <th>Review</th>\n" +
                    "  </tr>\n");


            String sql2 = "\n" +
                    "select a.a_first_name,p.title,p.paper_id, rr.rvr_first_name,r.rv_id,r.clarity,r.relevance,r.technical_rate \n" +
                    "from review r \n" +
                    "join reviewer rr on\n" +
                    "  rr.rvr_id = r.Rrvr_id\n" +
                    "  join  paper p on\n" +
                    "  r.p_id = p.paper_id \n" +
                    " join author a on\n" +
                    " p.author_id = a.a_id;";
            PreparedStatement statement2 = conn.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs1 = statement2.executeQuery();
/*
            String sql3 = "select * from review r where rv_id= ? ";
            //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
            PreparedStatement statement3 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql4 = "select * from reviewer rrr where rrr.rvr_id= ? ";
            //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
            PreparedStatement statement4 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql5 = "select * from paper p, author a where paper_id= ? and p.author_id=a.a_id ";
            //"and p.paper_id=r.p_id and rr.rvr_id=r.Rrvr_id";
            PreparedStatement statement5 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
*/
            rs1 = statement2.executeQuery();
            while (rs1.next()) {
                name = rs1.getString("a.a_first_name");
                pd1 = rs1.getString("p.title");
                pd2 = rs1.getInt("p.paper_id");
                rr = rs1.getString("rr.rvr_first_name");
                // pd = rs1.getInt("r.rv_id");
                cy = rs1.getInt("r.clarity");
                re = rs1.getInt("r.relevance");
                tr = rs1.getInt("r.technical_rate");


                //out.println("<form action=\"http://localhost:8080/selection\" method=\"post\">");
                out.println("  <tr>\n" +
                        "    <td>" + ""+ name + "" + " " + "</td>\n" +
                        "    <td>" + "" + pd1 + "(paper no :" +pd2+")"+" </td>\n" +
                        "    <td>" + "" + rr + "" + "  </td>\n" +
                        "    <td>" + "" + cy + "" + " </td>\n" +
                        "    <td>" + "" + re + "" + "  </td>\n" +
                        "    <td>" + "" + tr + "" + " </td>\n" +

                        //out.println("<a href=\"https://www.w3schools.com\"> </a>");
                        //Paper no: "+paperid+"
                        //"    <td><input type=\"submit\" name=\"element\" value=\"Review this paper\"\"</td>\n" +
                        "  </tr>\n");


            }
            out.println("<center><b>ENTER THE PAPER NO. TO ACCEPT OR REJECT </b><form action=\"http://localhost:8080/selection\" method=\"post\">\n" +
                    "<input type=\"number\" name=\"paper\" value\"\" >\n" +
                    "<input type=\"Submit\" name=\"accept\" value=\"Accept\" style=\"background-color:yellowgreen;color:white;padding:5px;font-size:18px;border:none;padding:8px;\">\n" +
                   "<input type=\"Submit\" name=\"reject\" value=\"Reject\" style=\"background-color:red;color:white;padding:5px;font-size:18px;border:none;padding:8px;\">\n" +
                    "</form></center>");
        } catch (SQLException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

                /*
                System.out.println("pd");
                statement3.setInt(Integer.parseInt("1"),pd);

                rs2 = statement3.executeQuery();

                while (rs2.next()) {


                    //an=rs2.getString("a_first_name");

                    pd1 = rs2.getInt("p_id");
                    rr = rs2.getInt("Rrvr_id");

                    cy = rs2.getString("clarity");
                    re = rs2.getString("relevance");
                    tr = rs2.getString("technical_rate");


                    //System.out.println("<form action=\"http://localhost:8080/reviewsystem\" method=\"post\">");


                    statement4.setInt(Integer.parseInt("1"),rr);

                    rs3 = statement4.executeQuery();

                    while (rs3.next()) {

                        name= rs3.getString(2);

                    }

                    statement5.setInt(Integer.parseInt("1"),pd1);
                    rs4=statement5.executeQuery();
                    while (rs4.next()){

                        ad = rs4.getInt(1);

                    }

                }

                out.println("<form action=\"http://localhost:8080/reviewsystem\" method=\"post\">");
                out.println("  <tr>\n" +
                        "    <td>" + ""+ name + "" + " " + "</td>\n" +
                        "    <td>" + "" + pd1 + "" + " </td>\n" +
                        "    <td>" + "" + rr + "" + "  </td>\n" +
                        "    <td>" + "" + cy + "" + " </td>\n" +
                        "    <td>" + "" + re + "" + "  </td>\n" +
                        "    <td>" + "" + tr + "" + " </td>\n" +
                        //out.println("<a href=\"https://www.w3schools.com\"> </a>");
                        //Paper no: "+paperid+"
                        //"    <td><input type=\"submit\" name=\"element\" value=\"Review this paper\"\"</td>\n" +
                        "  </tr>\n");

            }
            //----------------------------------------------------------------------------------------------

            //          String sql3 = "select * from paper p, author a where p.author_id= ? and p.author_id=a.a_id";
//            PreparedStatement statement3 = conn.prepareStatement(sql3, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


            /*

            while (resultSet.next()) {
                xyz = resultSet.getString("a_first_name");
                xyz1 = resultSet.getString("a_last_name");
                int b = resultSet.getInt("a_id");
                int abc2 = Integer.parseInt(resultSet.getString("a_id"));
                // System.out.println("" +xyz+ "" + xyz1 + " " + abc2);
                ///list
                //out.println("<html>");
                //out.println("<title>");
                //out.println("</title>");
                //out.println("<head>");
                //out.println("</head>");
                //out.println("<body>");
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
                            //out.println("<a href=\"https://www.w3schools.com\"> </a>");
                            //Paper no: "+paperid+"
                            //"    <td><input type=\"submit\" name=\"element\" value=\"Review this paper\"\"</td>\n" +
                            "  </tr>\n");



                }




            }

            //request.getSession(true).setAttribute("tpaperid",paperid);



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
                    //"<input type=\"submit\" value=\"Submit\">\n" +
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



         catch(ClassNotFoundException e)

        {
            e.printStackTrace();
        }

    }
*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
