package tests;

import static org.junit.Assert.*;

import across.application.*;
import across.enumerations.projectState;
import across.enumerations.typeSocial;
import across.project.InfraestructureProject;
import across.project.SocialProject;
import across.user.User;

import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
	private Application app;
	private User user1;
	private User user2;
	private InfraestructureProject p1;
	private SocialProject p2;
	
	@Before
	public void setUp() {
		app = Application.getApplication();
		
		user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        user2 = new User("pablo88", "98765432Z", "hola");
        app.addNonValidatedUsers(user2);
        
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p2 = new SocialProject("P2", "Proyecto social p2", 44000, "grupo ", typeSocial.NACIONAL, user2);
        p1.validate();

	}

	@Test
	public final void testReadDistricts() {
		assertTrue(app.readDistricts().size() == 21);
	}

	@Test
	public final void testRegister() {
		assertFalse(app.register("", "2314433E", "pssw"));
		assertFalse(app.register("maria01", "12345678A", "mipassword"));
		assertFalse(app.register("pablo88", "98765432Z", "hola"));
		
		assertTrue(app.register("paco", "12345678Z", "pass"));
	}

	@Test
	public final void testLogin() {
		assertFalse(app.login("maria01", "mipass"));
		assertFalse(app.login("pablo88","hola"));
		
		assertTrue(app.login("maria01","mipassword"));
	}

	@Test
	public final void testFilterProject() {
		assertEquals(app.filterProject(projectState.ACEPTADO).get(0),(InfraestructureProject) p1);
		assertEquals(app.filterProject(projectState.ENVALIDACION).get(0),p2);
	}

	/*@Test
	public final void testFilterSocialProjectTypeSocial() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFilterSocialProjectString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFilterInfrProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSearchProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSearchCollective() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPopularityReport() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAffinityReport() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckFinance() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckExpired() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPantallaLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPantallaPrincipal() {
		fail("Not yet implemented"); // TODO
	}*/

}
