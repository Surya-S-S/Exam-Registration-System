import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class getStatus extends HttpServlet{
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
            String search = "SELECT rid,sname,course,paid,verified FROM student_db WHERE uid="+uid;
            ResultSet rs = stmt.executeQuery(search);
            out.println("<tr><th>Reg ID</th><th>Name</th><th>Course</th><th>Payment</th><th>Status</th><th>Hall ticket</th></tr>");
            while(rs.next()) {
                out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td>");
                if(rs.getInt(5)==1)
                    out.println("<td>Approved</td><td><center><button class='button' type='button' onclick='ticketDisp("+rs.getLong(1)+")'>View</button></center></td></tr>");
                else if(rs.getInt(5)==0)
                    out.println("<td>Waiting List</td><td>Unavailable</td></tr>");
                else
                    out.println("<td>Rejected</td><td>Unavailable</td></tr>");
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