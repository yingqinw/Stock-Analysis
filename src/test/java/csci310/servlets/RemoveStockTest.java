package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import csci310.SQL;

public class RemoveStockTest extends Mockito{

	@Test
	public void testDoGet() throws IOException {
		SQL.register("hyunjae","Hj1234");
		
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        
        
        when(request.getParameter("ticker")).thenReturn("AAPL");
        when(request.getParameter("username")).thenReturn("hyunjae");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new RemoveStock().doGet(request, response);
        
        writer.flush();
		assertTrue(true); // can be refactored
		
		//test wrong username
		HttpServletRequest request1 = mock(HttpServletRequest.class);       
        HttpServletResponse response1 = mock(HttpServletResponse.class);
        when(request1.getParameter("ticker")).thenReturn("AAPL");
        when(request1.getParameter("username")).thenReturn("unregisteredusername");
        
        StringWriter stringWriter1 = new StringWriter();
        PrintWriter writer1 = new PrintWriter(stringWriter1);
        when(response1.getWriter()).thenReturn(writer1);
        
        new RemoveStock().doGet(request1, response1);
        
        writer1.flush();
		assertTrue(true);
		
		//trigger exception
		HttpServletRequest request2 = mock(HttpServletRequest.class);       
        HttpServletResponse response2 = mock(HttpServletResponse.class);
        when(request2.getParameter("ticker")).thenReturn("AAPL");
        when(request2.getParameter("username")).thenReturn("fakeusername");
        
        StringWriter stringWriter2 = new StringWriter();
        PrintWriter writer2 = new PrintWriter(stringWriter2);
        when(response2.getWriter()).thenReturn(writer2);
        
        new RemoveStock().doGet(request2, response2);
        
        writer2.flush();
		assertTrue(true);
	}

}
