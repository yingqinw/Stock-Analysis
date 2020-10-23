package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class AddStockGraphTest extends Mockito {

	@Test
	public void testDoPost() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        when(request.getParameter("ticker_graph")).thenReturn("AAPL");
        when(request.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request.getParameter("enddate_graph")).thenReturn("09/21/2020");
        
        new AddStockGraph().doPost(request, response);
        
        writer.flush();
        assertTrue(stringWriter.toString().contains("09/14/2020"));
        
        
        //trigger addstockerr
        HttpServletRequest request1 = mock(HttpServletRequest.class);       
        HttpServletResponse response1 = mock(HttpServletResponse.class);
        
        StringWriter stringWriter1 = new StringWriter();
        PrintWriter writer1 = new PrintWriter(stringWriter1);
        when(response1.getWriter()).thenReturn(writer1);
        
        when(request1.getParameter("ticker_graph")).thenReturn("WRONGSTOCK");
        when(request1.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request1.getParameter("enddate_graph")).thenReturn("09/21/2020");
        
        new AddStockGraph().doPost(request1, response1);
        
        writer1.flush();
        assertTrue(stringWriter1.toString().contains("Invalid"));
	}

}
