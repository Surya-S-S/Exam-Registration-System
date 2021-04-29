import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class admin extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
        String usrname = request.getParameter("usrname2");
        String passwd = request.getParameter("passwd2");
		try{			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String search = "SELECT * FROM admin WHERE usrname='"+usrname+"'";
            ResultSet rs = stmt.executeQuery(search);
			if(!rs.next()) {
				out.println("<script>");			    
                out.println("alert('Login failed\\nInvalid Username\\nTry again');");
				out.println("location.href='http://localhost:8080/ERS/index.html'");
				out.println("</script>");
			}
			else {
                if(rs.getString(1).equals(usrname) && rs.getString(2).equals(passwd))
                {
					HttpSession session = request.getSession(true);
					session.setAttribute("user",usrname);
					out.println("<script>");		    
                    out.println("alert('Logged In\\nWelcome "+usrname+"');");
                    out.println("location.href='http://localhost:8080/ERS/smenu.html'");
                    out.println("</script>");
                }
                else {
                    out.println("<script>");		    
                    out.println("alert('Login failed\\nInvalid Password');");
                    out.println("location.href='http://localhost:8080/ERS/index.html'");
                    out.println("</script>");
                }
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