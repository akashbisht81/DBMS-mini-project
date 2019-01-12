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

@WebServlet(name = "reviewerupdate0")
public class reviewerupdate0 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int set = 0;
        PrintWriter out = response.getWriter();
        String email= null;
        String pass = null;


        HttpSession session = request.getSession();
         email= (String)session.getAttribute("email");
         pass= (String)session.getAttribute("pass");

        request.getSession(true).setAttribute("email",email);
        request.getSession(true).setAttribute("pass",pass);

        //request.getSession(true).getAttribute("email",email);
        //request.getSession(true).getAttribute("pass",pass);



        out.println("<html>");
        out.println("<head>");
        out.println("<title>");
        out.println("</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("HELLO"+email);
        out.println("<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "    <form id=\"contact\" action=\"http://localhost:8080/reviewerupdate\" method=\"post\">\n" +
                "\n" +
                "        <h3>Reviewer Details Update </h3>\n" +
                "        <h4>Enter the information to update your details</h4>\n" +
                "        <fieldset>\n" +
                "            <input placeholder=\"First name\" type=\"text\" name=\"firstname\" tabindex=\"1\" required autofocus>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "            <input placeholder=\"Last name\" type=\"text\" name=\"lastname\"tabindex=\"1\" required autofocus>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "            <input placeholder=\"Your Phone Number\" type=\"tel\" name=\"telephone\"tabindex=\"3\" required>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "            <input placeholder=\"Your Email Address\" type=\"email\" name=\"email\" tabindex=\"2\" required>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "            <input placeholder=\"password\" type=\"password\" name=\"password\" tabindex=\"1\" required autofocus>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "\n" +
                "        </fieldset>\n" +
                "\n" +
                "        <fieldset>\n" +
                "            <textarea placeholder=\"review interest\" name=\"interest\" tabindex=\"5\" required></textarea>\n" +
                "        </fieldset>\n" +
                "        <fieldset>\n" +
                "            <button name=\"submit\" type=\"submit\" id=\"contact-submit\" data-submit=\"...Sending\">Submit</button>\n" +
                "        </fieldset>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>");

        out.println("</body");
        out.println("</html>");


    }






    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
