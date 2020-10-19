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
		Stocks s = new Stocks(1,"IBM","10/06/2020","10/09/2020");
		Stocks s1 = new Stocks(2,"AAPL","10/03/2020","10/07/2020");
		SQL.register("Bigmonster","Abc123");
		SQL.addStock("Bigmonster",s);
		SQL.addStock("Bigmonster",s1);
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        when(request.getParameter("username")).thenReturn("Bigmonster");
        when(request.getParameter("startdate_graph")).thenReturn("10/02/2020");
        when(request.getParameter("enddate_graph")).thenReturn("10/10/2020");
        
        new AddPortfolioGraph().doPost(request, response);
        writer.flush();
        assertTrue(true);
	}

}
