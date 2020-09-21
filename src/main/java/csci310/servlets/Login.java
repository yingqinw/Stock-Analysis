package csci310.servlets;

import csci310.SQL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	public class LoginError{
		private String loginerr = "";
		public LoginError(String errmesg) {
			loginerr = errmesg;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " hello there.");
		
		
		boolean user = SQL.login(username, password);
		System.out.println(user);
		
		if(user) {
			HttpSession session = request.getSession(false);
			session.setAttribute("username", username);
			
			String hello = (String)session.getAttribute("username");
			
		}
		
		else {
			LoginError le = new LoginError("Incorrect username or password. Please try again! :)");
			response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(le));
	        out.flush();   
		}
		
		
	}

}
