import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class hallTicket extends HttpServlet{
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
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel='stylesheet' href='ticket.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='ticket'>");
            out.println("<div id='header'><h1>Hall Ticket</h1></div>");
            out.println("<table>");            
            while(rs.next()) {
                out.println("<tr><th>Registration ID</th><td>"+rs.getString(1)+"</td></tr>");
                out.println("<tr><th>Student Name</th><td>"+rs.getString(3)+"</td></tr>");
                out.println("<tr><th>Course</th><td>"+rs.getString(10)+"</td></tr>");
                out.println("<tr><th>Exam date</th><td>"+rs.getString(11)+"</td></tr>");
                out.println("<tr><th>Session</th><td>"+rs.getString(12)+"</td></tr>");               
            }
            out.println("</table>");
            out.println("<div id='photopane'><center><img id='photo' src='getPhoto?rid="+rid+"'></center></div>");
            out.println("</div><br>");
            out.println("<button class='button' type='button' onclick='window.print()'>Print</button>");
            out.println("</body>");
            out.println("</html>");             
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