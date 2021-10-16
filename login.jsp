<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="google-signin-client_id" content="846793744986-4qhnconnn7eif0adsgo029ot0qhc7gga.apps.googleusercontent.com">
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <style>
       button
      {
        font-size: 16px;
        background-color:#fec107;
        border: none;
        color: white;
        padding: 5px 8px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        }
      a{
        color: aliceblue;
        text-decoration: none;
      }
  </style>
  <link rel="stylesheet" href="style.css">
</head>
<title>Login Page</title>
<body bgcolor="white">
<!--%
cookieString = cookieString.substring(0,cookieString.length()-1) + "}";
response.setHeader("Set-Cookie", "Cookies="+cookieString+"; SameSite=none; Secure;");
%-->
<div class="wrapper">
    <div class="title">
      Login Form
    </div>
    <form method="POST" action='<%= response.encodeURL("j_security_check") %>'>
    <div class="form">
        <div class="inputfield">
            <label>Username:</label>
            <input type="text" class="input" name="j_username">
        </div>  
          <div class="inputfield">
            <label>Password:</label>
            <input type="password" class="input" name="j_password">
        </div> 
        <div class="inputfield">
          <input type="submit" value="Log In" class="btn">
        </div>
        <center><button type="button" value="signup"><a href="signup.html">Signup</a></button></center>
      </div>
    <center><button type="button" onclick="myFunction()">Logout</button></center>
    </form>
</div>
</body>
</html>