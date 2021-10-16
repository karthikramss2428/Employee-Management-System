import java.io.*;  
import java.util.Scanner;  
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Readempfile extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        try
        {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String path="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/internship/";
            String filename=request.getParameter("filename");
            String file=path+filename;
            String row="";
            File editfile=new File(file);
            BufferedReader csvReader = new BufferedReader(new FileReader(editfile));
            while ((row = csvReader.readLine()) != null){
                String[] rows=row.split(",");
                String name=rows[0];
                String mobile=rows[1];
                String email=rows[2];
                String dob=rows[3];
                String gender=rows[4];
                try
                {
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
                    Statement stmt=con.createStatement(); 
                    String db="insert into employeedb(empname,mobile,email,gender,dob) values('"+name+"','"+mobile+"','"+email+"','"+gender+"','"+dob+"')"; 
                    int rs=stmt.executeUpdate(db);
                }catch (SQLException e) {
                    e.printStackTrace();
                } 
            }
            String docType1 =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
             out.println("<html>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
            "<h2>"+"Employee Details Inserted!"+"</h2>\n"+
            "</body>" +
            "</html>"
           );
            csvReader.close();
        }
        catch (IOException e)   
        {  
            e.printStackTrace();    //prints exception if any  
        }  
    }  
}  