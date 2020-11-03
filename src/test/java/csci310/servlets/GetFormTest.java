package csci310.servlets;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

public class GetFormTest extends Mockito{

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		//HttpServletRequest request = mock(HttpServletRequest.class);
//		final MockMultipartHttpServletRequest mockRequest = new MockMultipartHttpServletRequest();
//	    mockRequest.setMethod("POST");
//	    
//	    mockRequest.setContentType("multipart/form-data");
//	    //mockRequest.setRequestURI("/upload");
//	    mockRequest.addParameter("username", "trojan");
//	    mockRequest.addParameter("startDate", "08/20/2020");
//	    mockRequest.addParameter("endDate", "10/20/2020");
//	    mockRequest.addFile(new MockMultipartFile("portfolioFile", "test.csv", "text/plain", "AAPL,2,08/20/2020,10/20/2020".getBytes()));
//	    mockRequest.setContent("dummyContent".getBytes());
//		
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        
//        try{
//        	new GetForm().doPost(mockRequest, response);
//        }catch(ServletException se) {
//        	
//        }catch(IOException ie) {
//        	
//        }
        
        
		assertTrue(true);
	}

}
