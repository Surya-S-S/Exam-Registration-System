import java.util.*;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
@MultipartConfig(maxFileSize = 16177215)

public class form3 extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL="jdbc:mysql://localhost/ers";
		String USER = "root";
		String PASS = "Hackme19*";
		HttpSession session = request.getSession(true);
		Long rid = 0L;
		Long uid = Long.parseLong((String)session.getAttribute("uid"));
		String course = (String)session.getAttribute("course");
		try{			
            InputStream photo = null;
			InputStream sign = null;
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();            
            Part filePart1 = request.getPart("photo");
			Part filePart2 = request.getPart("sign");
            if (filePart1 != null) {photo = filePart1.getInputStream();}
			if (filePart2 != null) {sign = filePart2.getInputStream();}
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String view = "SELECT sname FROM student_db WHERE uid="+uid+" AND course='"+course+"' AND verified!=-1";
            ResultSet rs = stmt.executeQuery(view);
			if(rs.next())
			{
				out.println("<script>");
				out.println("alert('Already Registered');");
				out.println("location.href='cmenu.html';");
				out.println("</script>");
				return;
			}
			stmt.close();
			String insert = "INSERT INTO student_db(uid,sname,dob,email,gender,phno,addr,clg,course,date,sess,city1,city2,city3,photo,sign) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(insert);
			pre.setLong(1,Long.parseLong((String)session.getAttribute("uid")));
            pre.setString(2,(String)session.getAttribute("sname"));
			pre.setDate(3,java.sql.Date.valueOf((String)session.getAttribute("dob")));
			pre.setString(4,(String)session.getAttribute("email"));
			pre.setString(5,(String)session.getAttribute("gender"));
			pre.setLong(6,Long.parseLong((String)session.getAttribute("phno")));
			pre.setString(7,(String)session.getAttribute("addr"));
			pre.setString(8,(String)session.getAttribute("clg"));
			pre.setString(9,(String)session.getAttribute("course"));
			pre.setDate(10,java.sql.Date.valueOf((String)session.getAttribute("date")));
			pre.setString(11,(String)session.getAttribute("sess"));
			pre.setString(12,(String)session.getAttribute("pref1"));
			pre.setString(13,(String)session.getAttribute("pref2"));
			pre.setString(14,(String)session.getAttribute("pref3"));
			pre.setBlob(15,photo);
			pre.setBlob(16,sign);
            pre.execute();
			pre.close();
			stmt = conn.createStatement();
            String search = "SELECT rid FROM student_db ORDER BY rid DESC LIMIT 1";
            rs = stmt.executeQuery(search);
            while(rs.next()) {
                rid = rs.getLong(1);
            }
			out.println("<script>");
			out.println("window.localStorage.setItem('rid',"+rid+");");
			out.println("location.href='check.html';");
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