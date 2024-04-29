package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	Connection con;
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
	//---------------------------------------------------------------------------------------------------------------------
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String query1="select * from user where username = ? and password = ?";
        
        try 
        {
        	pStmt= con.prepareStatement(query1); 
        	pStmt.setString(1, username);
        	pStmt.setString(2, password);
            
            rs =  pStmt.executeQuery();
            if (rs.next()) 
            {
            	HttpSession session = request.getSession();
            	session.setAttribute("username", username);
                response.sendRedirect("dashboard.jsp");
            } 
            else 
            {
                response.sendRedirect("login.jsp?error=invalid"); 
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            
            try { if (rs != null) rs.close(); } 
            catch (SQLException e) { e.printStackTrace(); }
            try { if (pStmt != null) pStmt.close(); }
            catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } 
            catch (SQLException e) { e.printStackTrace(); }
        }// Invalid Login Credentials
    }   
}
