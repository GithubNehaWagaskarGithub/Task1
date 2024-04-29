<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h2>Registration</h2>
    <% if (request.getParameter("error") != null && request.getParameter("error").equals("exists")) { %>
        <p style="color: red;">UserName already exists!</p>
    <% } else if (request.getParameter("error") != null && request.getParameter("error").equals("fail")) { %>
        <p style="color: red;">Registration failed! Please try again.</p>
    <% } %>
    <form action="register" method="post">
        UserName: <input type="text" name="username" required="required"><br>
        Password: <input type="password" name="password" required="required" pattern="^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"><br>
        <input type="submit" value="Register">
    </form>
    <p>Already have an account? <a href="login.jsp">Login here</a></p>
</body>
</html>