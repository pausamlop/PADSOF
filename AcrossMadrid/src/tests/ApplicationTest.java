package tests;

import static org.junit.Assert.*;

import java.io.File;

import across.application.*;
import across.enumerations.projectState;
import across.enumerations.typeSocial;
import across.project.InfraestructureProject;
import across.project.SocialProject;
import across.user.*;

import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {
	private Application app;
	private User user1;
	private User user2;
	private User user3;
	private InfraestructureProject p1;
	private SocialProject p2;
	private SocialProject p3;
	private Collective c1;
	private Collective c2;
	
	
	@Before
	public void setUp() {
		app = Application.getApplication();
		
		user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        user2 = new User("pablo88", "98765432Z", "hola");
        app.addNonValidatedUsers(user2);
        user3 = new User("jose21", "44455566K", "0000");
        user3.validate();
        
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p2 = new SocialProject("P2", "Proyecto social p2", 44000, "grupo ", typeSocial.NACIONAL, user3);
        p1.validate();
        p3 = new SocialProject("P3", "Proyecto social p3", 25000, "españoles ", typeSocial.INTERNACIONAL, user3);
        p3.validate();
        
        
        app.setMinVotes(2);
        
        p3.vote(user1);
        p1.sendToFinance();
        
     // user1 se loguea y crea 3 colectivos
        Application.getApplication().setCurrentUser(user1);
        c1 = new Collective("COLECTIVO 1", "Descripcion del colectivo 1");
        c2 = new Collective("COLECTIVO 2", "Descripcion del colectivo 2");
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
		
		assertTrue(app.filterProject(projectState.ACEPTADO).contains(p1));
		assertTrue(app.filterProject(projectState.VOTOSALCANZADOS).contains(p3));
		
		//No hay ninguno que haya caducado
		assertEquals(app.filterProject(projectState.NOFINANCIADO).size(),0);
	}

	@Test
	public final void testFilterSocialProjectTypeSocial() {
		
		assertTrue(app.filterSocialProject(typeSocial.INTERNACIONAL).contains(p3));
	}

	@Test
	public final void testFilterSocialProjectString() {
		
		assertTrue(app.filterSocialProject("españoles").contains(p3));
		
		assertEquals(app.filterSocialProject("estudiantes").size(), 0);
	}

	@Test
	public final void testFilterInfrProject() {
		assertTrue(app.filterInfrProject("Retiro").contains(p1));
		
		assertEquals(app.filterInfrProject("Arganzuela").size(),0);
	}

	@Test
	public final void testSearchProject() {
		assertTrue(app.searchProject("Proyecto").contains(p1) && app.searchProject("Proyecto").contains(p3));
		assertTrue(app.searchProject("P1").contains(p1));
		
		assertFalse(app.searchProject("P2").contains(p1));
	}

	@Test
	public final void testSearchCollective() {
		assertTrue(app.searchCollective("colectivo").contains(c1) && app.searchCollective("colectivo").contains(c2));
		assertTrue(app.searchCollective("COLECTIVO 1").contains(c1));
		
		assertFalse(app.searchCollective("2").contains(c1));
	}
	
    /*@Test
	public final void testAffinityReport(){
		
	}*/
	
	@Test
	public final void testCheckFinance(){
		assertTrue(Application.getApplication().getPendingFinance().containsKey(p3));
		Application.getApplication().checkFinance();
		
		// no se financia hasta que pasa al menos 1 dia
		assertTrue(Application.getApplication().getPendingFinance().containsKey(p3));
		
	}

    
    @Test 
 	public void testLoadData() {
         Application app = Application.loadData();
         assertNotNull(app);
 	}

      @Test
 	public void testSaveData() {
         File before = new File("app.objectData");
         Application.saveData(Application.getApplication());

          File after = new File("app.objectData");
         assertNotSame(before, after);
 	}

}