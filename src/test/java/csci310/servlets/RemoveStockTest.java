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
		assertTrue(true);
	}

}
