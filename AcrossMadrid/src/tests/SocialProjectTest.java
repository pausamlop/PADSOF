package tests;

import across.model.application.Application;
import across.model.enumerations.*;
import across.model.project.*;
import across.model.user.*;

import static org.junit.Assert.*;

import java.time.*;

import org.junit.Before;
import org.junit.Test;





public class SocialProjectTest {
	
    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private SocialProject p1;
    private SocialProject p2;
    private SocialProject p3;
    
    private Collective c1;


    @Before
    public void setUp(){

        user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        
        user2 = new User("pablo88", "98765432Z", "hola");
        user2.validate();
        
        user3 = new User("jose21", "44455566K", "0000");
        user3.validate();
        
        user4 = new User("lucia21", "98725432Z", "contrasena");
        user4.validate();
        user4.block("Envia proyectos que solo le benefician a el");
 

        // user1 se loguea y crea 1 colectivo
        Application.getApplication().setCurrentUser(user1);
        
        c1 = new Collective("COLECTIVO 1", "Descripcion del colectivo 1");
        c1.join(user3);


        // Se crea y se valida un proyecto
        p1 = new SocialProject("P1", "Descripcion p1", 25000, "Ninos", typeSocial.NACIONAL, user1);
        p1.validate();
        
        // Se crea y se valida un proyecto
        p2 = new SocialProject("P2", "Descripcion p2", 5000, "Ancianos", typeSocial.NACIONAL, user1);
        p2.validate();
        
        // Se crea y se valida un proyecto
        p3 = new SocialProject("P3", "Descripcion p3", 50000, "Alimentacion", typeSocial.INTERNACIONAL, user2);
        p3.validate();
        
        p1.follow(user1);
 

    }
	
	
	
	@Test
	public void testSetProjectState() {
		// Hacer que user 1 siga al proyecto
		p1.follow(user1);
		
		//Cambiar el estado
		p1.setProjectState(projectState.ENVIADO);
		assertEquals(projectState.ENVIADO, p1.getProjectState() );
		
		//Enviar notificacion
		assertEquals(1, user1.getNotifications().size());
		
		
		
	}
		
	

	@Test
	public void testUpdateState() {
		
		p1.setProjectState(projectState.ACEPTADO);
		p1.setVotes(Application.getApplication().getMinVotes() - 1);
		p1.updateState();
		
		p3.setProjectState(projectState.ACEPTADO);
		p3.setVotes(Application.getApplication().getMinVotes() + 1);
		p3.updateState();
		
		p2.setProjectState(projectState.VOTOSALCANZADOS);
		p2.setVotes(Application.getApplication().getMinVotes() - 1);
		p2.updateState();
		
		assertEquals(projectState.ACEPTADO, p1.getProjectState());
		assertEquals(projectState.VOTOSALCANZADOS, p3.getProjectState());
		assertEquals(projectState.ACEPTADO, p2.getProjectState());

		
	}
	
	



	@Test
	public void testValidate() {
		
		SocialProject proyecto1 = new SocialProject("Project", "Descripcion", 40000, "Grupo", typeSocial.NACIONAL, user1);
		assertFalse(Application.getApplication().getProjects().contains(proyecto1));
		
		proyecto1.validate();
		
		assertTrue(Application.getApplication().getProjects().contains(proyecto1));
		assertEquals(0, Application.getApplication().getNonValidatedProjects().size());
		assertEquals(3, user1.getCreatedProjects().size());
		assertEquals(projectState.ACEPTADO, proyecto1.getProjectState());
		assertEquals(1, proyecto1.getVotes());

	}

	
	
	@Test
	public void testCompareTo() {
		p1.setVotes(3);
		p2.setVotes(1);
		p3.setVotes(1);
		
		assertEquals(-1, p1.compareTo(p2));
		assertEquals(1, p2.compareTo(p1));
		assertEquals(0, p2.compareTo(p3));
		
		
	}
	
	
	
	@Test
	public void testReject() {
		SocialProject proj = new SocialProject("Project", "Descripcion", 40000, "Grupo", typeSocial.NACIONAL, user1);
        
        assertTrue(Application.getApplication().getNonValidatedProjects().contains(proj));
        proj.reject();
        assertFalse(Application.getApplication().getNonValidatedProjects().contains(proj));
		
        assertEquals(0, Application.getApplication().getNonValidatedProjects().size());
        assertEquals(2, user1.getCreatedProjects().size());
        assertEquals(1, Application.getApplication().getRejectedProjects().size());
        assertEquals(projectState.RECHAZADO, proj.getProjectState());
 
		
	}

	
	
	
	@Test
	public void testCheckFollow() {
		
		assertTrue(p1.checkFollow(user1));
		assertFalse(p1.checkFollow(user2));
		
	}

	
	
	@Test
	public void testFollow() {
		p2.follow(user1);
		assertEquals(2, user1.getFollowedProjects().size());
		assertEquals(1, p2.getFollowers().size());
		
		// Se impiden seguidores repetidos
		p1.follow(user1);
		assertEquals(2, user1.getFollowedProjects().size());
		assertEquals(1, p1.getFollowers().size());
		
	}

	
	
	@Test
	public void testUnfollow() {
		p2.unfollow(user1);
		assertEquals(1, user1.getFollowedProjects().size());
		assertEquals(0, p2.getFollowers().size());
		
		p1.unfollow(user1);
		assertEquals(0, user1.getFollowedProjects().size());
		assertEquals(0, p1.getFollowers().size());
	}

	
	
	@Test
	public void testVote() {
		
		// miembros user1, user3
		p1.vote(c1);
		// user1 pertenece c1
		p1.vote(user1);
		p1.vote(user2);
		//user4 bloqueado
		user4.getBlocked();
		p1.vote(user4);
		
		assertEquals(3, p1.getVotes());
		
		//Actualizar proyectos votados en uc
		assertEquals(1, c1.getVotedProjects().size());
		assertEquals(2, user1.getVotedProjects().size());
		assertEquals(2, user2.getVotedProjects().size());
		assertEquals(0, user3.getVotedProjects().size());
		assertEquals(0, user4.getVotedProjects().size());

		if (p1.getVotes() < Application.getApplication().getMinVotes()) {
			assertEquals(projectState.ACEPTADO, p1.getProjectState());
		} else {
			assertEquals(projectState.VOTOSALCANZADOS, p1.getProjectState());
		}
		
	}


	@Test
	public void testUpdateVotes() {
		
		p2.vote(c1);
		p2.updateVotes();
		assertEquals(2, p2.getVotes());
		
		c1.disjoin(user3);
		p2.updateVotes();
		assertEquals(1, p2.getVotes());
		
		c1.join(user2);
		p2.updateVotes();
		assertEquals(2, p2.getVotes());
		
		user2.block("Pruebas");
		p2.updateVotes();
		assertEquals(1, p2.getVotes());
		
	}

	
	
	@Test
	public void testSendToFinance() {
		
		p1.setVotes(Application.getApplication().getMinVotes() - 1);
		assertFalse(p1.sendToFinance());
		
		p2.setVotes(Application.getApplication().getMinVotes() + 1);
		assertTrue(p2.sendToFinance());
		
		assertEquals(1, Application.getApplication().getPendingFinance().size());
		assertEquals(projectState.ENVIADO, p2.getProjectState());
		
		
	}

	
	
	@Test
	public void testIsExpired() {

		p1.setLastVote(LocalDate.of(2018, 10, 30));
		p2.setLastVote(LocalDate.now());
		
		assertTrue(p1.isExpired());
		assertFalse(p2.isExpired());
		
		assertEquals(projectState.CADUCADO, p1.getProjectState());
		assertEquals(projectState.ACEPTADO, p2.getProjectState());
		
	}
	
	
	@Test
	public void testFinanced() {
		Application app = Application.getApplication();
		p2.setVotes(app.getMinVotes() + 1);
		p2.sendToFinance();
        
        assertNull(p2.financed(app.getPendingFinance().get(p2)));
        
	}
	




}
