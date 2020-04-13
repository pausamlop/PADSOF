package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import java.time.*;

import across.model.application.*;
import across.model.project.*;
import across.model.user.User;

import org.junit.Test;


public class ApplicationTest2 {
	private Application app;
	private Project p1;
	private Project p2;
	private User user1;
	
	@Before
	public void setUp() {
		app = Application.getApplication();
		
		user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p1.validate();
        
        LocalDate lastVote = LocalDate.now();
        lastVote = lastVote.minusDays(31);
        p1.setLastVote(lastVote);
        
	}
	
	@Test
	public final void testCheckExpired() {
		
		app.checkExpired();
		
		assertTrue(app.getRejectedProjects().contains(p1));
	}

}
