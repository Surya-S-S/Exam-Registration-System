import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class retrieve extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
        String cid = request.getParameter("cid");
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String view = "SELECT * FROM exam_db WHERE cid='"+cid+"'";
            ResultSet rs = stmt.executeQuery(view);
			if(!rs.next()) {
				out.println("<script>");			    
                out.println("alert('Course not found');");
				out.println("location.href='http://localhost:8080/ERS/details.html'");
				out.println("</script>");
			}
			else {								
				out.println("<html><head>");
				out.println("<style>h2{margin-top:150px;}");
				out.println("form{width:400px;}");
				out.println(".button{width:28%;}</style>");
				out.println("<link rel='stylesheet' type='text/css' href='style.css'>");			
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>Exam Registration System</h2>");
				out.println("<h3>Update Course</h3>");
				out.println("<form method='post' action='http://localhost:8080/ERS/update'>");
				out.println("<label for='cid'>Course Code</label>");
				out.println("<input class='field' pattern='[A-Z0-9]{5,10}' type='text' id='cid' name='cid' value="+rs.getString(1)+" required>");
				out.println("<br><br><label for='ldor'>Registration Due</label>");
				out.println("<input class='field' type='date' id='ldor' name='ldor' value="+rs.getString(3)+" required>");
				out.println("<br><br><label for='sdate'>Exam Start date</label>");
				out.println("<input class='field' type='date' id='sdate' name='sdate' value="+rs.getString(4)+" required>");
				out.println("<br><br><label for='edate'>Exam End date</label>");
				out.println("<input class='field' type='date' id='edate' name='edate' value="+rs.getString(5)+" required>");
				out.println("<br><br><center><input class='button' type='submit' value='Update Course'></center>");
				out.println("</form>");
				out.println("<script>");
				out.println("</script>");
				out.println("</body></html>");
				out.close();
				stmt.close();
				conn.close();
			}                       
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}