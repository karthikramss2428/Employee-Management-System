import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class check extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String gender=request.getParameter("gender");
        try
       {
           /*Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
           Statement stmt=con.createStatement(); */
          // String db="insert into sample(dob,gender) values('"+date+"','"+gender+"')"; 
           //int rs=stmt.executeUpdate(db);
           String docType1 =
          "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
           out.println("<html>\n" +
          "<body bgcolor = \"#f0f0f0\">\n" +
          "<h2>"+"Hello" +gender+ "!"+"</h2>\n"+
          "</body>" +
          "</html>"
         );
        }
        catch(Exception e){ System.out.println(e);}
    }
}