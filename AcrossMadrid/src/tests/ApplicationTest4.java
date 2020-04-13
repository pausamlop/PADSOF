package tests;

import static org.junit.Assert.*;

import org.junit.Before;

import across.model.application.*;
import across.model.enumerations.typeSocial;
import across.model.project.*;
import across.model.user.Collective;
import across.model.user.User;

import org.junit.Test;


public class ApplicationTest4 {
	private Application app;
	private Project p1;
	private Project p2;
	private Project p3;
	private User user1;
	private User user2;
	private User user3;
	private Collective c1;
	private Collective c2;
	private Collective c3;
	
	@Before
	public void setUp() {
		app = Application.getApplication();
		
		user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        user2 = new User("paco12", "1231234A", "mipassword");
        user2.validate();
        user3 = new User("paco12", "1231234A", "mipassword");
        user3.validate();
        
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p1.validate();
        p3 = new SocialProject("P3", "Proyecto social p3", 25000, "españoles ", typeSocial.INTERNACIONAL, user1);
        p3.validate();
        p2 = new SocialProject("P2", "Proyecto social p2", 25000, "españoles ", typeSocial.INTERNACIONAL, user1);
        p2.validate();

        
        
     // user1 se loguea y crea 3 colectivos
        Application.getApplication().setCurrentUser(user1);
        c1 = new Collective("COLECTIVO 1", "Descripcion del colectivo 1");
        c2 = new Collective("COLECTIVO 2", "Descripcion del colectivo 2");
        c3 = new Collective("COLECTIVO 3", "Descripcion del colectivo 3");
        
        c1.join(user2);
        c1.join(user3);
        
        c2.join(user3);
        
        p3.vote(c1);
        
        p2.vote(c1);
        p2.vote(c2);
        
        p1.vote(c1);
        p1.vote(c2);
        p1.vote(c3);
        
	}
	
	@Test
	public final void testAffinityReport(){
		String case1 = null;
		
		Application.getApplication().setCurrentUser(user2);
		assertEquals(app.affinityReport(c2), case1);
		
		System.out.print(app.affinityReport(c1));
		
		String case2 ="1. COLECTIVO 2, 1.6666666666666667\n"+"2. COLECTIVO 3, 1.3333333333333333\n";
		assertEquals(app.affinityReport(c1), case2);
	}

}
