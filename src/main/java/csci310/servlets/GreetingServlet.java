package csci310.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csci310.Hello;

public class GreetingServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Hello h = new Hello();
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");
		response.getWriter().println(h.greet("servlet"));
	}
}
