<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
  <head>
      <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <!--meta name="google-signin-client_id" content="846793744986-4qhnconnn7eif0adsgo029ot0qhc7gga.apps.googleusercontent.com">
      <script src="https://apis.google.com/js/platform.js" async defer></script-->
      <style>
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
      a{
        color: aliceblue;
        text-decoration: none;
      }
      </style>
      	<link rel="stylesheet" href="style.css">
      <title>Home Page</title>
      <script src="https://apis.google.com/js/platform.js" async defer></script>
      <meta name="google-signin-client_id" content="846793744986-4qhnconnn7eif0adsgo029ot0qhc7gga.apps.googleusercontent.com">
  </head>
<body>
  <div class="wrapper">
    <div class="title">
      Home Page
    </div>
    <div class="form">
      <center><button type="button"><a href="secure/menu.jsp">Redirect to Login Page</a></p></button></center>
      <!--center><div class="g-signin2" data-onsuccess="onSignIn"></div>
        <button type="button" onclick="myfunction()">Logout</button></center-->
    </div>
  </div>
  <!--script>
    function onSignIn(googleUser){
      document.cookie = 'cookie2=value2; SameSite=None; Secure';
      console.log(JSON.stringify(googleUser));
      window.location="secure/menu.jsp"
    }
  function myFunction() {
      gapi.auth2.getAuthInstance().disconnect();
      location.reload();
   }
  </script-->
</body>
</html>
