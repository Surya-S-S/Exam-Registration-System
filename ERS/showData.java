import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class showData extends HttpServlet{
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
                out.println("<tr><th>Registration ID</th><td id='rid'>"+rs.getString(1)+"</td><th>College</th><td>"+rs.getString(9)+"</td></tr>");
                out.println("<tr><th>Student Name</th><td>"+rs.getString(3)+"</td><th>Course</th><td>"+rs.getString(10)+"</td><tr>");
                out.println("<tr><th>Date of Birth</th><td>"+rs.getString(4)+"</td><th>Exam date</th><td>"+rs.getString(11)+"</td></tr>");
                out.println("<tr><th>Gender</th><td>"+rs.getString(6)+"</td><th>Exam session</th><td>"+rs.getString(12)+"</td></tr>");
                out.println("<tr><th>E-mail</th><td>"+rs.getString(5)+"</td><th>Preference 1</th><td>"+rs.getString(13)+"</td></tr>");
                out.println("<tr><th>Mobile no.</th><td>"+rs.getString(7)+"</td><th>Preference 2</th><td>"+rs.getString(14)+"</td></tr>");
                out.println("<tr><th>City</th><td>"+rs.getString(8)+"</td><th>Preference 3</th><td>"+rs.getString(15)+"</td></tr>");
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