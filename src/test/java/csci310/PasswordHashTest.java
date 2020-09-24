package csci310;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(MessageDigest.class)

public class PasswordHashTest extends Mockito{

	@Test
	public void testGetHash() throws NoSuchAlgorithmException {
		PasswordHash ph = new PasswordHash();
		String pass = "Password123";
		String hashed = PasswordHash.getHash(pass,"SHA-512");
		assertTrue(hashed.equals("804f50ddbaab7f28c933a95c162d019acbf96afde56dba10e4c7dfcfe453dec4bacf5e78b1ddbdc1695a793bcb5d7d409425db4cc3370e71c4965e4ef992e8c4"));
		
		//PowerMockito.mockStatic(MessageDigest.class);
        //Mockito.when(MessageDigest.getInstance("SHA-512")).thenThrow(new NoSuchAlgorithmException());
		//String hashed2 = PasswordHash.getHash(pass, "SHA-512");
	}

}
