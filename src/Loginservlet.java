


import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;


@WebServlet(name = "Loginservlet")

public class Loginservlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
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
            PreparedStatement pst = conn.prepareStatement("Select a_email,a_pwd from author where a_email=? and a_pwd= ? ");
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            RequestDispatcher rd2=request.getRequestDispatcher("/authorlogin");
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
}





/*

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Loginservlet extends HttpServlet
{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public void init(ServletConfig config)throws ServletException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/conferencesystem","root","root");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        String username=request.getParameter("Username");
        String password=request.getParameter("password");
        pw.println("<html><body>");
        try
        {
            ps=con.prepareStatement("select * from loginvalidation where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
            if(rs.next())
            {
                pw.println("<h3>welcome " +" " + username +"</h3>");
                RequestDispatcher rd1=request.getRequestDispatcher("./home.html");
                rd1.include(request,response);
                //or
                //response.sendRedirect("./home.html");
                pw.println("<form method=\"post\" action=\"Login.html\">");
                pw.println("<input type=\"submit\" name=\"logout\" " + "value=\"Logout\">");
                pw.println("</form>");

            }
            else
            {
                pw.println("<center><h3>invalid username/password Enter Correct username/password</h3></center>");
                RequestDispatcher rd2=request.getRequestDispatcher("./Login.html");
                rd2.include(request,response);
                //or
                //response.sendRedirect("./Login.html");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

*/












