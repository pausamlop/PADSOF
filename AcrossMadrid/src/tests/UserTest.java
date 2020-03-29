package tests;

import across.application.Application;
import across.enumerations.projectState;
import across.notification.*;
import across.project.*;
import across.user.*;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;


public class UserTest {

    private User user1;
    private User user2;
    private User user3;

    private Collective c1;
    private Collective c2;
    private Collective c3;

    private Project p1;

    @Before
    public void setUp(){

        // Crear 3 usuarios, uno bloqueado y dos no
        user1 = new User("maria01", "12345678A", "mipassword");
        user2 = new User("pablo88", "98765432Z", "hola");
        user2.block("Envia proyectos que solo le benefician a el");
        user3 = new User("jose21", "44455566K", "0000");

        // user1 se loguea y crea 3 colectivos
        Application.getApplication().setCurrentUser(user1);
        c1 = new Collective("COLECTIVO 1", "Descripcion del colectivo 1");
        c2 = new Collective("COLECTIVO 2", "Descripcion del colectivo 2");
        c3 = new Collective("COLECTIVO 3", "Soy hijo de COLECTIVO 2", c2);
        
        // user3 se une al colectivo c2
        c2.join(user3);

        // Se crea y se valida un proyecto
        p1 = new InfraestructureProject("P1", "Descripcion p1", 25000, "p1.jpg", "Retiro", user1);
        p1.validate();
        
        // user3 sigue al proyecto p para que pueda recibir notificaciones
        p1.follow(user3);

        // se cambia el estado de p para que se generen y envien notificaciones
        p1.setProjectState(projectState.VOTOSALCANZADOS);
        p1.setProjectState(projectState.ACEPTADO);

    }

	@Test
	public void testGetUsername() {
		assertEquals("maria01", user1.getUsername());
	}

	@Test
	public void testGetNIF() {
		assertEquals("12345678A", user1.getNIF());
	}

	@Test
	public void testGetPassword() {
		assertEquals("mipassword", user1.getPassword());
	}

	@Test
	public void testGetBlocked() {
        assertFalse(user1.getBlocked());
        assertTrue(user2.getBlocked());
	}

	@Test
	public void testGetBlockedMssg() {
        assertEquals("",user1.getBlockedMssg());
        assertEquals("Envia proyectos que solo le benefician a el",user2.getBlockedMssg());

        user2.unblock();
        assertEquals("",user2.getBlockedMssg());
	}

	@Test
	public void testGetCreatedCollectives() {
        assertTrue(user1.getCreatedCollectives().contains(c1));
        assertFalse(user1.getCreatedCollectives().isEmpty());
        assertEquals(3, user1.getCreatedCollectives().size());
	}

	@Test
	public void testGetMemberCollectives() {
        assertTrue(user1.getMemberCollectives().contains(c1));
        assertFalse(user2.getMemberCollectives().contains(c1));
        assertEquals(3, user1.getMemberCollectives().size());
        assertEquals(1, user3.getMemberCollectives().size());
	}

	@Test
	public void testGetNotifications() {
        assertTrue(user2.getNotifications().isEmpty());
        assertFalse(user3.getNotifications().isEmpty());
        assertEquals(2, user3.getNotifications().size());
	}

	@Test
	public void testGetFollowedProjects() {
        assertTrue(user2.getFollowedProjects().isEmpty());
        assertTrue(user3.getFollowedProjects().contains(p1));
        assertEquals(1, user3.getFollowedProjects().size());
	}

	@Test
	public void testSetCreatedCollectives() {
        Collective c4 = new Collective("COLECTIVO 4", "Descripcion c4");
        ArrayList<Collective> created = new ArrayList<Collective>();
        created.add(c4);
        user1.setCreatedCollectives(created);
        
        assertTrue(user1.getCreatedCollectives().contains(c4));
        assertEquals(1, user1.getCreatedCollectives().size());
	}

	@Test
	public void testSetMemberCollectives() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFollowedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidate() {
		fail("Not yet implemented");
	}

	@Test
	public void testReject() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddNotification() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNotificationMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnblock() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrincipalUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCreatedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVotedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCreatedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetVotedProjects() {
		fail("Not yet implemented");
	}

}
