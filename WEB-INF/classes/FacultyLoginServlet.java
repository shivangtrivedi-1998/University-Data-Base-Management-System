import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FacultyLoginServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String s1=req.getParameter("facultyid");
        String s2=req.getParameter("pass");
         Connection con = null;
        Statement st = null;
        ResultSet rs = null;
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
            rs = st.executeQuery("select * from faculty where faculty_id = '"+s1+"' and password ='"+s2+"'");
            
                if(rs.next())
                {
                   res.sendRedirect("FacultyWelcomeServlet?k="+s1); // username is passed through s1 to WelcomeServlet by url rewriting method
                } 
                  /* pw.println("<form method='GET' action='WelcomeServlet'>");
                   pw.println("<input type='hidden' name='username' value='"+s1+"'>");
                   pw.println("Enter Any Number : <input type='text' name='ean' value=''>");
                   pw.println("<input type='submit' name='submit' value='submit'>");
                   pw.println("</form>");  
                    second method is hidden filed
                   
                   Cookie ob = new Cookie(s1,s2);
                   ob.setMaxAge(365*24*60*60);
                   res.addCookie(ob);
                   res.sendRedirect("WelcomeServlet"); */
                else
                {
                    pw.println("inalid details");
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
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        doGet(req,res);
      }
}