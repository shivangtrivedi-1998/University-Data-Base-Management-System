import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ManageDepartment extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
    
        pw.println("<h1>Total Departments</h1><br><br>");
        Connection con = null;
        Statement st = null;
        ResultSet rs=null;
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
             st = con.createStatement();
            rs = st.executeQuery("select * from department");
            String s1="", s2="", s3="",s4="",s5="",s6="",s7="",s8="",s9="";
            pw.println("<table border='1' width='500'>");
        pw.println("<tr style='font-weight: bold; color: blue; font-size: 20px;'><td>department_id</td><td>department_name</td></tr>"); 
            pw.println("<br>");
            while(rs.next())
            {
                s1 = rs.getString(1);
                s2 = rs.getString(2);
               
                pw.println("<tr><td>"+s1+"</td><td>"+s2+"</td></tr>");
                pw.println("<br>");
            }
            pw.println("</table>");
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
}