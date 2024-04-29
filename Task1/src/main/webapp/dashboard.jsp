<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>

	<h2>Dashboard</h2>
	
   	<h2>Hello <%=session.getAttribute("username")%> </h2>
    
<form action="logouts" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html> 
