import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FileUploadDBServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {

    // database connection settings
    //private String dbURL = "jdbc:mysql://localhost:3306/conferencesystem";
    //private String dbUser = "root";
    //private String dbPass = "root";
    ResultSet rs = null;
   int abc;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        String authorTitle = request.getParameter("title");
        String authorAbstract = request.getParameter("abstract");
        String fileName= request.getParameter("filename");
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
        PrintWriter out=  response.getWriter();
        HttpSession session = request.getSession();
        //String s = (String)session.getAttribute("e");
        String s1=(String)session.getAttribute("pass");
        String s= (String) session.getAttribute("email");
        try {
            // connects to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBUtil.getConnection(DBType.MYSQLDB);


            // constructs SQL statement
            String sql1 = "Select * from author where a_email= ? and a_pwd=? ";
            PreparedStatement statement1 = conn.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement1.setString(1, s);
            statement1.setString(2, s1);
            rs = statement1.executeQuery();
            while (rs.next()) {
                abc= rs.getInt("a_id");
            }
            String sql = "INSERT INTO paper (title, abstract,file_name,photo,author_id) values (?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);


            statement.setString(1, authorTitle);
            statement.setString(2, authorAbstract);
            statement.setString(3,fileName);
            //RequestDispatcher rd2=request.getRequestDispatcher("/Message");
            //http://localhost:8080/Message
            //rd2.include(request,response);
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(4, inputStream);
                out.println("<html>");
                out.println("<title>");
                out.println("</title>");
                out.println("<body>");
                out.println("<h1> Successfully inserted !!!");
                //+abc+""+s+""+s1);
                out.println("</body>");
                out.println("</html>");

            }

           statement.setInt(5,abc);


            // sends the statement to the database server
            int row = statement.executeUpdate();
            int row1 = statement1.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
            if(row1 > 1){
                String message1 = "SUCCESSFULLY UPCODED !";
            }
        } catch (ClassNotFoundException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message", message);

            // forwards to the message page

            //getServletContext().getRequestDispatcher("///////Meessage").forward(request, response);
        }
    }
}
