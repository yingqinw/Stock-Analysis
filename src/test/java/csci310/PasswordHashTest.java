package csci310;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

public class PasswordHashTest extends Mockito{

	@Test
	public void testGetHash() {
		String pass = "Password123";
		String hashed = PasswordHash.getHash(pass,"SHA-512");
		assertTrue(hashed.equals("804f50ddbaab7f28c933a95c162d019acbf96afde56dba10e4c7dfcfe453dec4bacf5e78b1ddbdc1695a793bcb5d7d409425db4cc3370e71c4965e4ef992e8c4"));
		
		
		String hashed2 = PasswordHash.getHash(pass, "random-instance");
	}

}
