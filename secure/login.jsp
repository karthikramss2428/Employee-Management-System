<html>
<head>
<title>Login Page</title>
</head>

<body>
    <form method="POST" action='<%= response.encodeURL("j_security_check") %>'>
        <table cellspacing="5">
            <tr>
                <th align="right">Username:</th>
                <td align="left"><input type="text" name="j_username"></td>
            </tr>

            <tr>
                <th align="right">Password:</th>
                <td align="left"><input type="password" name="j_password"></td>
            </tr>

            <tr>
                <td align="right"><input type="submit"><a href="index.jsp"></a></td>
                <td align="left"><input type="reset"></td>
            </tr>
        </table>
    </form>
</body>
</html>