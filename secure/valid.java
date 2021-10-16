import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Valid extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String strchoice=request.getParameter("choice");
        int choice=Integer.parseInt(strchoice);
        switch(choice)
        {
            case 1:String empname=request.getParameter("empname");
            String mobile=request.getParameter("mobile");
            String email=request.getParameter("email");
            String gender=request.getParameter("gender");
            String dob=request.getParameter("dob");
            try
            {
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
               Statement stmt=con.createStatement(); 
               String db="insert into employeedb(empname,mobile,email,gender,dob) values('"+empname+"','"+mobile+"','"+email+"','"+gender+"','"+dob+"')"; 
               int rs=stmt.executeUpdate(db);
               String docType1 =
               "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
                out.println("<html>\n" +
               "<body bgcolor = \"#f0f0f0\">\n" +
               "<h2>"+"Hello" +empname+ "!"+"</h2>\n"+
               "</body>" +
               "</html>"
              );
            }
            catch(Exception e){ System.out.println(e);}
            break;
            case 2:String strid=request.getParameter("id");
            int id=Integer.parseInt(strid);
            String name1=request.getParameter("empname");
            String mobile1=request.getParameter("mobile");
            String email1=request.getParameter("email");
            String dob1=request.getParameter("dob");
            String gender1=request.getParameter("gender");
            try
            {
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
              Statement stmt=con.createStatement();
              String query="update employeedb SET empname='"+name1+"',mobile='"+mobile1+"',email='"+email1+"',dob='"+dob1+"',gender='"+gender1+"' WHERE empid=" +id;
              int rs2=stmt.executeUpdate(query); 
               String docType1 =
              "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
               out.println("<html>\n" +
              "<body bgcolor = \"#f0f0f0\">\n" +
              "<h2>"+"Details updated " +name1+ "!"+"</h2>\n"+
              "</body>" +
              "</html>"
             );
            }        
            catch(Exception e){ System.out.println(e);}
            break;
            default:System.exit(0);
            case 3:String strid1=request.getParameter("id");
            int id1=Integer.parseInt(strid1);
            String name2=request.getParameter("empname");
            String mobile2=request.getParameter("mobile");
            String email2=request.getParameter("email");
            String dob2=request.getParameter("dob");
            String gender2=request.getParameter("gender");
            try
            {
                 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
                 Statement stmt=con.createStatement();
                 String query="delete from employeedb where empid="+id1;
                 int rs=stmt.executeUpdate(query); 
                 String docType1 =
               "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
                out.println("<html>\n" +
               "<body bgcolor = \"#f0f0f0\">\n" +
               "<h2>Employee Detail Deleted</h2>\n"+
               "</body>" +
               "</html>"
              ); 
             }
             catch(Exception e){ System.out.println(e);}
             break;
    }
    }
}