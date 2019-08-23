import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ResultServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String s = req.getParameter("k");
       
        Connection con = null;
        Statement st = null, st1 =null;
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
             st1 = con.createStatement();
            rs = st.executeQuery("select course_id, obtain_grades from grades natural join student where rollno ='"+s+"'");
            String s1="", s2="",s3="";
            pw.println("<table border='1' width='500'>");
        pw.println("<tr style='font-weight: bold; color: blue; font-size: 20px;'><td>Course_id</td><td>Obtain Grades</td></tr>"); 
            pw.println("<br>");
            while(rs.next())
            {
                s1 = rs.getString(1);
                s2 = rs.getString(2);
                pw.println("<tr><td>"+s1+"</td><td>"+s2+"</td></tr>");
                pw.println("<br>");
            }
            rs = st1.executeQuery(" select sum(obtain_grades) from grades natural join student where rollno = '"+s+"' group by first_name;"); 
            pw.println("<tr><td style='font-weight: bold; color: blue; font-size: 20px;'>Total Grades</td>");
            while(rs.next())
            {
                s3 = rs.getString(1);
                 pw.println("<td style='font-weight: bold;'>"+s3+"</td>");
            }
            pw.println("</tr>");
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