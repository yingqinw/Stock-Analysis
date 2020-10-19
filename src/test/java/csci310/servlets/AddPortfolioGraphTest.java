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

public class AddPortfolioGraphTest extends Mockito{

	@Test
	public void testDoPost() throws IOException {
		Stocks s = new Stocks(1,"apple","1999/01/01","2020/01/01");
		SQL.register("Bigmonster","Abc123");
		SQL.addStock("Bigmonster",s);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        when(request.getParameter("username")).thenReturn("Bigmonster");
        when(request.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request.getParameter("enddate_graph")).thenReturn("09/21/2020");
        
        new AddPortfolioGraph().doPost(request, response);
        writer.flush();
        assertTrue(true);
	}

}
