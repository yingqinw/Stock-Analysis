package csci310.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csci310.Hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Hello h = new Hello();
//		response.setStatus(HttpServletResponse.SC_OK);
//		response.setContentType("text/plain");
//		response.getWriter().println(h.greet("dfg"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		for(int i = 0; i<1000;i++) System.out.println(username + " hello there.");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " hello there.");
		for(int i = 0; i<1000;i++) System.out.println(username + " hello there.");
		
//		boolean user = SQL.login(username, password);
//		System.out.println(user);
//		
//		if(user) {
//			HttpSession session = request.getSession(false);
//			session.setAttribute("username", username);
//			
//			String hello = (String)session.getAttribute("username");
//			System.out.println(hello);
//			
//			
//			
//			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(nextpage);
//			dispatch.forward(request, response);
//		}
//		
//		else {
//			nextpage = "/login.jsp";
//			request.setAttribute("loginError", "Incorrect username or password. Please try again! :)");
//			RequestDispatcher dispatch = getServletContext().getRequestDispatcher(nextpage);
//			dispatch.forward(request, response);
//		}
//		
		
	}

}
