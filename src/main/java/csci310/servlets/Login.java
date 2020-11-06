package csci310.servlets;

import csci310.SQL;
import csci310.PasswordHash;
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
	public class LoginSuccess{
		private String successMsg = "";
		public LoginSuccess(String successMsg) {
			this.successMsg = successMsg;
		}
	}
	
	private int count = 0;
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " hello there.");
		
		
		
		boolean user = SQL.login(username, password);
		System.out.println(user);
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		
		if(user) {
//			HttpSession session = request.getSession(false);
//			session.setAttribute("username", username);
			LoginSuccess success = new LoginSuccess("Welcome, " + username);
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(success));
	        out.flush();   
	        count = 0;
		}
		
		else {
			count++;
			//System.out.println("Login attempt: "+ count);
			if(count == 3) {
				LoginError le = new LoginError("Incorrect username or password entered 3 times, user locked.");
				PrintWriter out = response.getWriter();
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        out.print(this.gson.toJson(le));
		        out.flush(); 
		        count = 0;
		        return;
			}
			LoginError le = new LoginError("Incorrect username or password. Please try again! :)");
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(le));
	        out.flush();   
		}

	}

}
