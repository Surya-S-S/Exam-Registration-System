import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class logout extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{			
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            session.invalidate();
            out.println("<script>");
            out.println("location.href='http://localhost:8080/ERS/index.html'");
			out.println("</script>");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
