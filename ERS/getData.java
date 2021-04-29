import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class getData extends HttpServlet{
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
            Statement stmt = conn.createStatement();
            String search = "SELECT * FROM student_db WHERE rid="+rid;
            ResultSet rs = stmt.executeQuery(search);
            while(rs.next()) {
                out.println("<tr><th>Student Name</th><td>"+rs.getString(3)+"</td></tr>");
                out.println("<tr><th>Date of Birth</th><td>"+rs.getString(4)+"</td></tr>");
                out.println("<tr><th>Gender</th><td>"+rs.getString(6)+"</td></tr>");
                out.println("<tr><th>E-mail</th><td>"+rs.getString(5)+"</td></tr>");                
                out.println("<tr><th>Mobile no.</th><td>"+rs.getString(7)+"</td></tr>");
                out.println("<tr><th>City</th><td>"+rs.getString(8)+"</td></tr>");
                out.println("<tr><th>College</th><td>"+rs.getString(9)+"</td></tr>");
                out.println("<tr><th>Course</th><td>"+rs.getString(10)+"</td></tr>");
                out.println("<tr><th>Fees Paid</th><td>"+rs.getString(18)+"</td></tr>");
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