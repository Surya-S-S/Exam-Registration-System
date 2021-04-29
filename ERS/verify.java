import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class verify extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
        Long rid = Long.parseLong(request.getParameter("rid"));
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String update = "UPDATE student_db SET verified=? WHERE rid="+rid;
            PreparedStatement pre = conn.prepareStatement(update);
            pre.setInt(1,1);
            int res = pre.executeUpdate();
			pre.close();
            out.println("<tr><th>Verified</th></tr>");
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