import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class courseFiller extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String search = "SELECT cname FROM exam_db WHERE ldor>=CURDATE()";
            ResultSet rs = stmt.executeQuery(search);
            while(rs.next()) {
                out.println("<option value='"+rs.getString(1)+"'>"+rs.getString(1)+"</option>");
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