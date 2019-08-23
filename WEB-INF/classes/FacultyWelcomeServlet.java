import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FacultyWelcomeServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String s = req.getParameter("k");
    
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
            rs = st.executeQuery("select * from faculty where faculty_id = '"+s+"'");
            String s1="", s2="", s3="",s4="",s5="",s6="",s7="",s8="";
            while(rs.next())
            {
                s1 = rs.getString(1);
                s2 = rs.getString(2);
                s3 = rs.getString(3);
                s4 = rs.getString(4);
                s5 = rs.getString(5);
                s6 = rs.getString(6);
                s7 = rs.getString(7);
                s8 = rs.getString(8);
                 pw.println("<body style='background: lightblue;'>");
                pw.println("<h1>WELCOME, "+s2+"</h1>");
                pw.println("<div style='margin: 10px 0px 0px 20px;'>");
                pw.println("<span style='font-weight: bold; color: blue;'>Faculty ID. : </span>"+s1);
                pw.println("<br><span style='font-weight: bold; color: blue;'>First_Name : </span>"+s2);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Last_Name : </span>"+s3);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Designation : </span>"+s4);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Email : </span>"+s5);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Department ID : </span>"+s6);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Course ID : </span>"+s7);
                pw.println("<br><span style='font-weight: bold; color: blue;'>Password : </span>"+s8);
                pw.println("</div>");
                 pw.println("<a href='FacultyLibraryServlet?k="+s+"' style='font-weight: bold; font-size: 20px; margin-left: 30px;'>See Library Details</a>");
                 pw.println("</body>");
            }
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