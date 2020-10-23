package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import csci310.CreateStockTable;
import csci310.CreateUserTable;
import csci310.DropStockTable;
import csci310.DropUserTable;
import csci310.SQL;
import csci310.Stocks;

public class AddPortfolioGraphTest extends Mockito{
	@BeforeClass
	public static void setup() {
		DropUserTable du = new DropUserTable();
		DropStockTable ds = new DropStockTable();
		CreateUserTable cu = new CreateUserTable();
		CreateStockTable cs = new CreateStockTable();
	}
	
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
        assertTrue(stringWriter.toString().contains("10/02/2020"));
        
        //trigger branch
		HttpServletRequest request1 = mock(HttpServletRequest.class);       
        HttpServletResponse response1 = mock(HttpServletResponse.class);
        
        StringWriter stringWriter1 = new StringWriter();
        PrintWriter writer1 = new PrintWriter(stringWriter1);
        when(response1.getWriter()).thenReturn(writer1);
        
        when(request1.getParameter("username")).thenReturn("Bigmonste");
        when(request1.getParameter("startdate_graph")).thenReturn("10/02/2020");
        when(request1.getParameter("enddate_graph")).thenReturn("10/10/2020");
        
        new AddPortfolioGraph().doPost(request1, response1);
        writer1.flush();
        assertTrue(true);
        
        //trigger exception
		HttpServletRequest request2 = mock(HttpServletRequest.class);       
        HttpServletResponse response2 = mock(HttpServletResponse.class);
        
        StringWriter stringWriter2 = new StringWriter();
        PrintWriter writer2 = new PrintWriter(stringWriter2);
        when(response2.getWriter()).thenReturn(writer2);
        
        when(request2.getParameter("username")).thenReturn("anusernamethatisobviouslyfake");
        when(request2.getParameter("startdate_graph")).thenReturn("10/02/2020");
        when(request2.getParameter("enddate_graph")).thenReturn("10/10/2020");
        
        new AddPortfolioGraph().doPost(request2, response2);
        writer2.flush();
        assertTrue(true);
	}

}
