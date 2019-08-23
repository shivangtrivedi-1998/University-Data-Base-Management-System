import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class IssuedBooksFaculty extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
    
        pw.println("<h1>Books Issued By Faculty</h1><br><br>");
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
            rs = st.executeQuery(" select book_id, book_name ,author_name, first_name, last_name from faculty natural join faculty_issued_books natural join books order by book_id");
            String s1="", s2="", s3="",s4="",s5="";
            pw.println("<table border='1' width='500'>");
        pw.println("<tr style='font-weight: bold; color: blue; font-size: 20px;'><td>book_id</td><td>book_name</td><td>author_name</td><td>Issuere Name</td></tr>"); 
            pw.println("<br>");
            while(rs.next())
            {
                s1 = rs.getString(1);
                s2 = rs.getString(2);
                s3 = rs.getString(3);
                s4 = rs.getString(4);
                s5 = rs.getString(5);
                pw.println("<tr><td>"+s1+"</td><td>"+s2+"</td><td>"+s3+"</td><td>"+s4+" "+s5+"</td></tr>");
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