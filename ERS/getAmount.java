import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class getAmount extends HttpServlet{
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
            Statement stmt = conn.createStatement();
            String search = "SELECT COUNT(*) AS total FROM student_db WHERE uid="+uid+" AND paid='no' AND verified!=-1";
            ResultSet rs = stmt.executeQuery(search);
            while(rs.next()) {
                out.println(rs.getInt("total"));
            }
			out.close();            
			stmt.close();
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