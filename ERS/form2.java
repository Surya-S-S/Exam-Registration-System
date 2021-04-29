import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class form2 extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            session.setAttribute("clg",request.getParameter("clg"));
            session.setAttribute("course",request.getParameter("course"));
            session.setAttribute("date",request.getParameter("date"));
            session.setAttribute("sess",request.getParameter("sess"));        
            session.setAttribute("pref1",request.getParameter("pref1"));
            session.setAttribute("pref2",request.getParameter("pref2"));
            session.setAttribute("pref3",request.getParameter("pref3"));
            out.println("<script>");
            out.println("location.href='http://localhost:8080/ERS/form3.html'");
            out.println("</script>");
        }
        catch(Exception e) {
            System.out.println(e);
        }        
    }
}