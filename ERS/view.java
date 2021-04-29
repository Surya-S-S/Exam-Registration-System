import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class view extends HttpServlet{
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
            String view = "SELECT * FROM exam_db";
            ResultSet rs = stmt.executeQuery(view);
			out.println("<html><head>");
			out.println("<style>");
			out.println("h2{margin-top:75px;}");
			out.println("table{width:50%;}");
			out.println("</style>");
			out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
			out.println("</head><body>");
			out.println("<h2>Exam Registration System</h2>");
    		out.println("<h3>Course Database</h3><br><br>");
            out.println("<center><table>");
            out.println("<tr><th>Course Code</th><th>Course Name</th><th>Registration Due</th><th>Exam Start Date</th><th>Exam End Date</th></tr>");
            while(rs.next()) {
                out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
            }
            out.println("</table></center>");
			out.println("<script>");
			out.println("</script>");
			out.println("</body></html>");
			out.close();
			rs.close();
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