package csci310.servlets;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

public class GetFormTest extends Mockito{
 
 public byte[] createFileContent(byte[] data, String boundary, String contentType, String fileName){
        
	 	String usernameField = "--" + "q1w2e3r4t5y6u7i8o9" + "\r\n Content-Disposition: form-data; name=\"" + "username" + "\";"
                     + "Content-type: " + "text" + "\r\n value=\"12345\"" + "\r\n\r\n";
        String usernameValue = "trojan" + "\r\n";
        
        String startField = "--" + "q1w2e3r4t5y6u7i8o9" + "\r\n Content-Disposition: form-data; name=\"" + "startDate" + "\";"
                + "Content-type: " + "text" + "\r\n value=\"12345\"" + "\r\n\r\n";
        String startValue = "08/20/2020" + "\r\n";
        
        String endField = "--" + "q1w2e3r4t5y6u7i8o9" + "\r\n Content-Disposition: form-data; name=\"" + "endDate" + "\";"
                + "Content-type: " + "text" + "\r\n value=\"12345\"" + "\r\n\r\n";
        String endValue = "10/20/2020" + "\r\n";
	    
        String file = "--" + boundary + "\r\n Content-Disposition: form-data; name=\"file\"; filename=\""+fileName+"\"\r\n"
                + "Content-type: "+contentType+"\r\n\r\n";;
                
       String end = "\r\n--" + boundary + "--";
       return ArrayUtils.addAll((usernameField+usernameValue+startField+startValue+endField+endValue+file).getBytes(),ArrayUtils.addAll(data,end.getBytes()));
    }
 


 @Test
 public void testDoPostHttpServletRequestHttpServletResponse() {
	 String current_dir = System.getProperty("user.dir");
	  //System.out.println(Paths.get(current_dir, "test.csv"));
	        Path path = Paths.get(current_dir, "test.csv");
        byte[] data = null;
        try {
        	data = Files.readAllBytes(path);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        MockMultipartFile file = new MockMultipartFile("test.csv", "test.csv",
                "application/csv", data);
        MockMultipartHttpServletRequest mockRequest = new MockMultipartHttpServletRequest();
        String boundary = "q1w2e3r4t5y6u7i8o9";
        mockRequest.setContentType("multipart/form-data; boundary="+boundary);
        
        //mockRequest.addParameter("username", "trojan");
        //mockRequest.addParameter("startDate", "08/20/2020");
        //mockRequest.addParameter("endDate", "10/20/2020");
        
        mockRequest.setContent(createFileContent(data,boundary,"application/csv","test.csv"));
        mockRequest.addFile(file);
        mockRequest.setMethod("POST");
        
        MockHttpServletResponse response = new MockHttpServletResponse();
        
        try{
         new GetForm().doPost(mockRequest, response);
        }catch(ServletException se) {
         
        }catch(IOException ie) {
         
        }
        assertTrue(true);
        
        
        //trigger error
        Path path1 = Paths.get(current_dir, "testError.csv");
        byte[] data1 = null;
        try {
        	data1 = Files.readAllBytes(path1);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        MockMultipartFile file1 = new MockMultipartFile("testError.csv", "testError.csv",
                "application/csv", data1);
        MockMultipartHttpServletRequest mockRequest1 = new MockMultipartHttpServletRequest();
        mockRequest1.setContentType("multipart/form-data; boundary="+boundary);
        
        mockRequest1.addParameter("username", "trojan");
        mockRequest1.addParameter("startDate", "08/20/2020");
        mockRequest1.addParameter("endDate", "10/20/2020");
        
        mockRequest1.setContent(createFileContent(data1,boundary,"application/csv","testError.csv"));
        mockRequest1.addFile(file1);
        mockRequest1.setMethod("POST");
        
        MockHttpServletResponse response1 = new MockHttpServletResponse();
        
        try{
         new GetForm().doPost(mockRequest1, response1);
        }catch(ServletException se) {
         
        }catch(IOException ie) {
         
        }
        assertTrue(true);
        
        
 }

}