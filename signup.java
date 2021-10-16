import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class signup extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
            try
            {
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
               Statement stmt=con.createStatement(); 
               String db="insert into user(username,roles) values('"+username+"','"+role+"')"; 
               int rs=stmt.executeUpdate(db);
               String db1="insert into autentication(username,password) values('"+username+"','"+password+"')";
               int rs1=stmt.executeUpdate(db1);
               response.sendRedirect("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/intern/index.jsp");
            }
            catch(Exception e){ System.out.println(e);}
            
    }
}