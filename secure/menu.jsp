]\
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <title>Home Page</title>
  </head>
  <style>
  	<% if(!request.isUserInRole("admin")){ %> 
  		.gest_proveedores a:link{ color: red;}
  		.gest_proveedores a:visited{ color: red; }
  		.gest_compras a:link{ color: red;}
  		.gest_compras a:visited{ color: red; }
  	<% } %>
	  a{
		color: aliceblue;
		text-decoration: none;
	}
	button
	{
		font-size: 16px;
		background-color:#A52A2A; /* Green */
		border: none;
		color: white;
		padding: 5px 8px;
		text-align: center;
		text-decoration: none;
		display: inline-block;
		font-size: 16px;
    }
    #username
    {
      position: absolute;
      left:1100px;
    }
  </style>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>
<button id="username"><i class="fa fa-home"></i> <%= util.HTMLFilter.filter(request.getUserPrincipal().getName()) %></button>
<%

String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3307/";
String dbName = "employee";
String userId = "root";
String password = "Minesister11@";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Employee Management Table</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1" id="myTable">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td style="color: white;"><b>Empid</b></td>
<td style="color: white;"><b>Name</b></td>
<td style="color: white;"><b>Mobile</b></td>
<td style="color: white;"><b>Email</b></td>
<td style="color: white;"><b>dob</b></td>
<td style="color: white;"><b>Gender</b></td>
<td style="color: white;"><b>EDIT</b></td>
<td style="color: white;"><b>DELETE</b></td>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM employeedb";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<form>
<tr bgcolor="#DEB887">
<td><%=resultSet.getInt("empid") %></td>
<td><%=resultSet.getString("empname") %></td>
<td><%=resultSet.getString("mobile") %></td>
<td><%=resultSet.getString("email") %></td>
<td><%=resultSet.getString("dob") %></td>
<td><%=resultSet.getString("gender") %></td>
<td><button class="return" style="background-color: #A52A2A;"><a href=update.html>Update</a></button></td>
<td><button class="return" style="background-color: #A52A2A;"><a href=deletion.html>Delete</a></button></td>
</tr>
</form>


<% 
}


} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
<br>
<center><button type="button"><a href="index.html">Insert a New Employee</a></button>
<button type="button"><a href="sendmail.html">Export as CSV</a></button>
<button type="button"><a href="upload.html">Import CSV</button>
<button><a href="${pageContext.request.contextPath}/logout.jsp">Logout</a> </button></center>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script  src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js">
</script>
<script src="fancytable.js">
</script>
<script>
    $(document).ready(function(){
        $("#myTable").fancyTable({
        /* Column number for initial sorting*/
        /* Setting pagination or enabling */
        pagination: true,
        /* Rows per page kept for display */
        perPage:10,
        globalSearch:true
        });
        $("#myTable").on('click','.return',function(){
            var currentRow=$(this).closest("tr"); 
            var col1=currentRow.find("td:eq(0)").text();
            var col2=currentRow.find("td:eq(1)").text(); 
            var col3=currentRow.find("td:eq(2)").text(); 
            var col4=currentRow.find("td:eq(3)").text(); 
            var col5=currentRow.find("td:eq(4)").text(); 
            var col6=currentRow.find("td:eq(5)").text(); 
            localStorage.setItem("col1",col1);
            localStorage.setItem("col2",col2);
            localStorage.setItem("col3",col3);
            localStorage.setItem("col4",col4);
            localStorage.setItem("col5",col5);
            localStorage.setItem("col6",col6);
        });
});
function myFunction() {
      gapi.auth2.getAuthInstance().disconnect();
      location.reload();
   }
</script>
</body>
</html>
