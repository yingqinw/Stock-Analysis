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

public class UpdatePortfolioTest extends Mockito{

	 @BeforeClass
	 public static void setup() {
		  DropUserTable du = new DropUserTable();
		  DropStockTable ds = new DropStockTable();
		  CreateUserTable cu = new CreateUserTable();
		  CreateStockTable cs = new CreateStockTable();
	 }
	 
	 @Test
	 public void testDoPost() throws IOException {
		  HttpServletRequest request = mock(HttpServletRequest.class);       
	        HttpServletResponse response = mock(HttpServletResponse.class);
	        
	        StringWriter stringWriter = new StringWriter();
	        PrintWriter writer = new PrintWriter(stringWriter);
	        when(response.getWriter()).thenReturn(writer);
	        
	        Stocks s = new Stocks(1,"IBM","10/06/2020","10/09/2020");
	        Stocks s1 = new Stocks(1,"AAPL","10/06/2020","10/09/2020");
	        Stocks s2 = new Stocks(1,"AMZN","10/06/2020","10/09/2020");
	        Stocks s3 = new Stocks(1,"QQQ","10/06/2020","10/09/2020");
		  SQL.register("Bigmonster","Abc123");
		  SQL.addStock("Bigmonster",s);
		  SQL.addStock("Bigmonster",s1);
		  SQL.addStock("Bigmonster",s2);
		  SQL.addStock("Bigmonster",s3);
		  when(request.getParameter("username")).thenReturn("Bigmonster");
	        when(request.getParameter("tickers_graph")).thenReturn("[ \"AAPL\", \"AMZN\", \"QQQ\" ]");
	        when(request.getParameter("startdate_graph")).thenReturn("10/05/2020");
	        when(request.getParameter("enddate_graph")).thenReturn("10/13/2020");
	        
	        new UpdatePortfolio().doPost(request, response);
	        
	        writer.flush();
	        assertTrue(stringWriter.toString().contains("3488"));
	        
	        
	        //trigger exceptions
	        HttpServletRequest request1 = mock(HttpServletRequest.class);       
	        HttpServletResponse response1 = mock(HttpServletResponse.class);
	        
	        StringWriter stringWriter1 = new StringWriter();
	        PrintWriter writer1 = new PrintWriter(stringWriter1);
	        when(response1.getWriter()).thenReturn(writer1);
	        
	        
		  when(request1.getParameter("username")).thenReturn("anusernamethatisobviouslyfake");
	        when(request1.getParameter("tickers_graph")).thenReturn("[ \"AAPL\", \"AMZN\", \"QQQ\" ]");
	        when(request1.getParameter("startdate_graph")).thenReturn("10/05/2020");
	        when(request1.getParameter("enddate_graph")).thenReturn("10/13/2020");
	        
	        new UpdatePortfolio().doPost(request1, response1);
	        
	        writer1.flush();
	        assertTrue(true);
		}
}