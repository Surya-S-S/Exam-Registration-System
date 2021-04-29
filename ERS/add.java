import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class add extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String insert = "INSERT INTO exam_db(cid,cname,ldor,sdate,edate) VALUES(?,?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(insert);
			pre.setString(1,request.getParameter("cid"));
			pre.setString(2,request.getParameter("cname"));
			pre.setDate(3,java.sql.Date.valueOf(request.getParameter("ldor")));
			pre.setDate(4,java.sql.Date.valueOf(request.getParameter("sdate")));
			pre.setDate(5,java.sql.Date.valueOf(request.getParameter("edate")));
			pre.execute();
			out.println("<script>");
			out.println("location.href='http://localhost:8080/ERS/details.html'");
			out.println("</script>");
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