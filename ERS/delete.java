import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class delete extends HttpServlet{
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
            String delete = "DELETE FROM exam_db WHERE cid='"+cid+"'";
            int res = stmt.executeUpdate(delete);
			out.println("<script>");
            if(res==0)
                out.println("alert('Invalid Record');");
			out.println("location.href='http://localhost:8080/ERS/details.html'");
			out.println("</script>");
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