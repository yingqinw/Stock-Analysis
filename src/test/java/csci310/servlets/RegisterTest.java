package csci310.servlets;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import csci310.SQL;

public class RegisterTest extends Mockito {

	@Test
	public void testDoPost() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("hyunjae");
        when(request.getParameter("password")).thenReturn("Hj1234");
        when(request.getParameter("confirmPassword")).thenReturn("Hj123");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Passwords did not match."));
        
        
	}
	
	@Test
	public void testDoPost2() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("Hj1234");
        when(request.getParameter("confirmPassword")).thenReturn("Hj1234");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
               
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("use mass blankspace."));
	}
	
	@Test
	public void testDoPost3() throws Exception {
		
	SQL.register("hyunjae","Hj1234");
		
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("hyunjae");
        when(request.getParameter("password")).thenReturn("Hj1234");
        when(request.getParameter("confirmPassword")).thenReturn("Hj1234");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("User already exists."));
	}
	
	@Test
	public void testDoPost4() throws Exception {
	
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("dummies");
        when(request.getParameter("password")).thenReturn("Dm333");
        when(request.getParameter("confirmPassword")).thenReturn("Dm333");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new Register().doPost(request, response);
        
        writer.flush();
        
        assertTrue(stringWriter.toString().contains("Welcome, " + "dummies"));
	}
	

}
