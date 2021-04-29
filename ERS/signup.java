import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class signup extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
		String usrname = request.getParameter("usrname");
        String passwd = request.getParameter("passwd");
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String insert = "INSERT INTO credentials(usrname,passwd) VALUES(?,?)";
			PreparedStatement pre = conn.prepareStatement(insert);
			pre.setString(1,usrname);
			pre.setString(2,passwd);
			pre.execute();
			out.println("<script>");
			out.println("location.href='http://localhost:8080/ERS/index.html'");
			out.println("</script>");
			out.close();
			conn.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}