import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class payment extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
        HttpSession session = request.getSession(true);
        Long uid = Long.parseLong((String)session.getAttribute("uid"));
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String update = "UPDATE student_db SET paid=? WHERE uid="+uid+" AND paid='no'";
            PreparedStatement pre = conn.prepareStatement(update);
            pre.setString(1,"yes");
            int res = pre.executeUpdate();
			pre.close();
            out.println(res);
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