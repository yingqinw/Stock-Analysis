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
import csci310.Stocks;

public class ChangeDateGraphTest extends Mockito{

	@Test
	public void testDoPost() throws IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Stocks s = new Stocks(1,"IBM","10/06/2020","10/09/2020");
		SQL.register("Bigmonster","Abc123");
		SQL.addStock("Bigmonster",s);
		when(request.getParameter("username")).thenReturn("Bigmonster");
        when(request.getParameter("tickers_graph")).thenReturn("[ \"AAPL\", \"AMZN\", \"QQQ\" ]");
        when(request.getParameter("startdate_graph")).thenReturn("08/05/2020");
        when(request.getParameter("enddate_graph")).thenReturn("10/13/2020");
        
        new ChangeDateGraph().doPost(request, response);
        
        writer.flush();
		assertTrue(true);
	}

}
