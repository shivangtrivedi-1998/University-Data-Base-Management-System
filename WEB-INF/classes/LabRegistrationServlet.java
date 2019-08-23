import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LabRegistrationServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String s1 = req.getParameter("rollno");
        String s2 = req.getParameter("firstname");
        String s3 = req.getParameter("lastname");
        String s4 = req.getParameter("email");
        String s5 = req.getParameter("phone");
        String s6 = req.getParameter("password");
        String s7 = req.getParameter("work");
        String s8 = req.getParameter("deptid");
        String s9 = req.getParameter("hostelno");
        
        Connection con = null;
        Statement st1 = null;
        Statement st2 = null;
        try{
           Class.forName("com.mysql.jdbc.Driver");
           con =  DriverManager.getConnection("jdbc:mysql://localhost:3307/ums","root","76625362");
        }    
        catch(ClassNotFoundException e1)
        {
            pw.println(e1);
        }
        catch(SQLException e2)
        {
            pw.println(e2);
        }
        try{
            st1 = con.createStatement();
           
            st1.executeUpdate("insert into student values('"+s1+"','"+s2+"','"+s3+"','"+s7+"','"+s5+"','"+s4+"','"+s8+"','"+s9+"','"+s6+"')");
             pw.println("<h1>registration successful</h1>");
             con.close();
        }
        catch(SQLException e1)
        {
            pw.println(e1);
        }
        catch(Exception e2)
        {
            pw.println(e2);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doGet(req , res);
    }
}   