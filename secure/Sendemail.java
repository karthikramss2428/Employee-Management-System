import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sendemail extends HttpServlet{
 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException{
  response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  String jdbcURL = "jdbc:mysql://localhost:3307/employee";
  String username = "root";
  String password1 = "Minesister11@";
   
  String csvFilePath = "Employee.csv";
   
  try (Connection connection = DriverManager.getConnection(jdbcURL, username, password1)) {
      String sql = "SELECT * FROM employeedb";
       
      Statement statement = connection.createStatement();
       
      ResultSet result = statement.executeQuery(sql);
       
      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
       
      // write header line containing column names       
      fileWriter.write("empid,empname,mobile,email,dateofbirth,gender");
       
      while (result.next()) {
          int empid = result.getInt("empid");
          String empName = result.getString("empname");
          String mobile= result.getString("mobile");
          String email= result.getString("email");
          String gender = result.getString("gender");
          String dob= result.getString("dob");
           
          String line = String.format("\"%d\",%s,%s,%s,%s,%s",
                  empid, empName, mobile, email, gender, dob);
           
          fileWriter.newLine();
          fileWriter.write(line);            
      }
       
      statement.close();
      fileWriter.close();
       
  } catch (SQLException e) {
      e.printStackTrace();
  } catch (IOException e) {

      e.printStackTrace();
  }

  String to=request.getParameter("email");//change accordingly
  final String user="karthikramss2824@gmail.com";//change accordingly
  final String password="Google1235@";//change accordingly
  String host="localhost";
 
  //1) get the session object   
  Properties properties = System.getProperties();
  properties.setProperty("mail.smtp.host",host);//change accordingly
  properties.put("mail.smtp.auth", "true");
  properties.put("mail.smtp.starttls.enable", "true");
  properties.put("mail.smtp.host", "smtp.gmail.com");
  properties.put("mail.smtp.port", "587");

  Session session = Session.getDefaultInstance(properties,
   new javax.mail.Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
   return new PasswordAuthentication(user,password);
   }
  });
   
  //2) compose message   
  try{
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(user));
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
    message.setSubject("Employee details");
    
    //3) create MimeBodyPart object and set your message content    
    BodyPart messageBodyPart1 = new MimeBodyPart();
    messageBodyPart1.setText("Here we had sent the employee details as csv format");
    
    //4) create new MimeBodyPart object and set DataHandler object to this object    
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();

    String filename = "Employee.csv";//change accordingly
    DataSource source = new FileDataSource(filename);
    messageBodyPart2.setDataHandler(new DataHandler(source));
    messageBodyPart2.setFileName(filename);
   
   
    //5) create Multipart object and add MimeBodyPart objects to this object    
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart1);
    multipart.addBodyPart(messageBodyPart2);

    //6) set the multiplart object to the message object
    message.setContent(multipart );
   
    //7) send message
    Transport.send(message);
 
   }catch (MessagingException ex) {ex.printStackTrace();}
   String docType =
   "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
    out.println(docType +
   "<html>\n" +
      "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">Message Sent</h1>\n" +
      "</body>" +
   "</html>"
);
 }
}