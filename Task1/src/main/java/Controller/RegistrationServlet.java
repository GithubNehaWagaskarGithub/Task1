package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	
	Connection con=null;
	PreparedStatement pStmt;
	ResultSet rs;
	@Override
	public void init() throws ServletException 
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/operation1?useSSL=true&requireSSL=true","root","Neha@123");
			} catch (SQLException e) {
				
			}
		} catch (ClassNotFoundException e) {
			
		}
	}
	//---------------------------------------------------------------------------------------------------------------------------
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String checkSql = "select * from user where username=?";
        try 
        {
        	pStmt = con.prepareStatement(checkSql);
        	pStmt.setString(1, username);
        	rs = pStmt.executeQuery();

        	if (rs.next()) 
        	{	
        		// User already exists
        		response.sendRedirect("register.jsp?error=exists");
        	} 
        	else {
        		// Register the user
        		String insertSql = "insert into  user (username, password) values (?, ?)";
        		pStmt = con.prepareStatement(insertSql);
        		pStmt.setString(1, username);
        		pStmt.setString(2, password);
        		int count = pStmt.executeUpdate();

        		if (count > 0) {
        			// Registration successful
        			HttpSession session = request.getSession();
        			session.setAttribute("username", username);
        			response.sendRedirect("dashboard.jsp");
        		} else {
        			// Registration failed
        			response.sendRedirect("register.jsp?error=fail");
        		}
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } 
        finally {
        	// Close connections
        	try { if (pStmt != null) pStmt.close(); }
        	catch (SQLException e) { e.printStackTrace(); }
        	try { if (pStmt != null) con.close(); } 
        	catch (SQLException e) { e.printStackTrace(); }
        }
    }//already Register
}
