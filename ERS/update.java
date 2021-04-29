import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class update extends HttpServlet{
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
            String update = "UPDATE exam_db SET ldor=?,sdate=?,edate=? WHERE cid='"+cid+"'";
            PreparedStatement pre = conn.prepareStatement(update);
            pre.setDate(1,java.sql.Date.valueOf(request.getParameter("ldor")));
			pre.setDate(2,java.sql.Date.valueOf(request.getParameter("sdate")));
			pre.setDate(3,java.sql.Date.valueOf(request.getParameter("edate")));
            int res = pre.executeUpdate();
			out.println("<script>");
			out.println("location.href='http://localhost:8080/ERS/details.html'");
			out.println("</script>");
			out.close();            
			pre.close();
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