package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
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
import io.cucumber.java.Before;

public class AddStockTest extends Mockito{
	@BeforeClass
	public static void setup() {
		DropUserTable du = new DropUserTable();
		DropStockTable ds = new DropStockTable();
		CreateUserTable cu = new CreateUserTable();
		CreateStockTable cs = new CreateStockTable();
	}
	
	@Test
	public void testDoGet() throws IOException {
		SQL.register("hyunjae","Hj1234");
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request.getParameter("enddate_graph")).thenReturn("10/10/2020");
        
        when(request.getParameter("ticker")).thenReturn("AAPL");
        when(request.getParameter("quantity")).thenReturn("1");
        when(request.getParameter("startdate")).thenReturn("09/11/2020");
        when(request.getParameter("enddate")).thenReturn("09/21/2020");
        when(request.getParameter("username")).thenReturn("hyunjae");
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        new AddStock().doGet(request, response);
        
        writer.flush();
		assertTrue(stringWriter.toString().contains("AAPL"));
		
		//add different stock
		HttpServletRequest request5 = mock(HttpServletRequest.class);       
        HttpServletResponse response5 = mock(HttpServletResponse.class);
        
        when(request5.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request5.getParameter("enddate_graph")).thenReturn("10/10/2020");
        
        when(request5.getParameter("ticker")).thenReturn("IBM");
        when(request5.getParameter("quantity")).thenReturn("1");
        when(request5.getParameter("startdate")).thenReturn("09/11/2020");
        when(request5.getParameter("enddate")).thenReturn("09/21/2020");
        when(request5.getParameter("username")).thenReturn("hyunjae");
        
        StringWriter stringWriter5 = new StringWriter();
        PrintWriter writer5 = new PrintWriter(stringWriter5);
        when(response5.getWriter()).thenReturn(writer5);
        
        new AddStock().doGet(request5, response5);
        
        writer5.flush();
		assertTrue(stringWriter5.toString().contains("IBM"));
		
		
		//cover invalid ticker
		HttpServletRequest request1 = mock(HttpServletRequest.class);       
        HttpServletResponse response1 = mock(HttpServletResponse.class);
        
        when(request1.getParameter("ticker")).thenReturn("randomfaketicker");
        when(request1.getParameter("quantity")).thenReturn("1");
        when(request1.getParameter("startdate")).thenReturn("09/11/2020");
        when(request1.getParameter("enddate")).thenReturn("09/21/2020");
        when(request1.getParameter("username")).thenReturn("hyunjae");
        when(request1.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request1.getParameter("enddate_graph")).thenReturn("10/10/2020");
        StringWriter stringWriter1 = new StringWriter();
        PrintWriter writer1 = new PrintWriter(stringWriter1);
        when(response1.getWriter()).thenReturn(writer1);
        
        new AddStock().doGet(request1, response1);
        
        writer1.flush();
		assertTrue(stringWriter1.toString().contains("Invalid ticker!"));
		
		//trigger database exception
		HttpServletRequest request2 = mock(HttpServletRequest.class);       
        HttpServletResponse response2 = mock(HttpServletResponse.class);
        when(request2.getParameter("startdate_graph")).thenReturn("09/11/2020");
        when(request2.getParameter("enddate_graph")).thenReturn("10/10/2020");
        when(request2.getParameter("ticker")).thenReturn("AAPL");
        when(request2.getParameter("quantity")).thenReturn("1");
        when(request2.getParameter("startdate")).thenReturn("09/11/2020");
        when(request2.getParameter("enddate")).thenReturn("09/21/2020");
        when(request2.getParameter("username")).thenReturn("fakeusername");
        StringWriter stringWriter2 = new StringWriter();
        PrintWriter writer2 = new PrintWriter(stringWriter2);
        when(response2.getWriter()).thenReturn(writer2);
        
        new AddStock().doGet(request2, response2);
        
        writer2.flush();
		assertTrue(stringWriter2.toString().contains("Exception thrown"));
        
	}
}
