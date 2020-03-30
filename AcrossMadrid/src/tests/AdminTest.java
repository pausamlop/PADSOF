package tests;

import across.application.*;
import across.admin.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdminTest {

	@Test
	public final void testLogin() {
		Admin admin = Application.getApplication().getAdmin();
		assertTrue(admin.login(admin.getPassword()));
		assertFalse(admin.login("12345"));
	}

}
