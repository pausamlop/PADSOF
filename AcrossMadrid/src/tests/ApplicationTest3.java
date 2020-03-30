package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import across.application.*;
import across.enumerations.typeSocial;
import across.project.*;
import across.user.User;

import org.junit.Test;


public class ApplicationTest3 {
	private Application app;
	private Project p1;
	private Project p3;
	private User user1;
	private User user2;
	
	@Before
	public void setUp() {
		app = Application.getApplication();
		
		user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        user2 = new User("paco12", "1231234A", "mipassword");
        user2.validate();
        
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p1.validate();
        p3 = new SocialProject("P3", "Proyecto social p3", 25000, "españoles ", typeSocial.INTERNACIONAL, user1);
        p3.validate();
        
        p1.vote(user2);
        
	}
	
	@Test
	public final void testPopularityReport(){
		String spectOutput = "1. P1\n2. P3\n";
		
		assertEquals(spectOutput, app.popularityReport());
	}

}
