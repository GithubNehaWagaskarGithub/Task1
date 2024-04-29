<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <% if (request.getParameter("error") != null && request.getParameter("error").equals("invalid")) { %>
        <p style="color: red;">Invalid UserName or password!</p>
    <% } %>
    <form action="login" method="post">
        UserName: <input type="text" name="username" required="required"><br>
        Password: <input type="password" name="password" required="required"><br>
        <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="register.jsp">Register here</a></p>
</body>
</html>