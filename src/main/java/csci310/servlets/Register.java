package csci310.servlets;

import csci310.SQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Register")
public class Register extends HttpServlet {
	
	public class RegisterError{
		private String registererr = "";
		public RegisterError(String errmesg) {
			registererr = errmesg;
		}
	}
	
	private Gson gson = new Gson();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("confirmPassword");
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		if(!password.equals(password2)) {
			
			RegisterError re = new RegisterError("Passwords did not match.");
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(re));
	        out.flush();   
		}
		else if(password.trim().equals("") || password2.trim().equals("") || username.trim().equals("")) {
			RegisterError re = new RegisterError("Please don't use mass blankspace.");
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(re));
	        out.flush();
		}
		else if(SQL.userExist(username)) {
			RegisterError re = new RegisterError("User already exists.");
			PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(this.gson.toJson(re));
	        out.flush();
		}
		else {
			SQL.register(username, password);
			HttpSession session = request.getSession(false);
			session.setAttribute("username", username);
			
			
		}
		
	}

}
