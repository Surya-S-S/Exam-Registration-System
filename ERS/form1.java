import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class form1 extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            session.setAttribute("sname",request.getParameter("sname"));
            session.setAttribute("dob",request.getParameter("dob"));
            session.setAttribute("email",request.getParameter("email"));
            session.setAttribute("gender",request.getParameter("gender"));        
            session.setAttribute("phno",request.getParameter("phno"));
            session.setAttribute("addr",request.getParameter("addr"));
            out.println("<script>");
            out.println("location.href='http://localhost:8080/ERS/form2.html'");
            out.println("</script>");
        }
        catch(Exception e) {
            System.out.println(e);
        }        
    }
}