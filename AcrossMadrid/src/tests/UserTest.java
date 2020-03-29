package tests;

import across.application.Application;
import across.enumerations.projectState;
import across.enumerations.typeSocial;
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

    private Project p1;
    private Project p2;

    @Before
    public void setUp() {

        // Crear 3 usuarios, uno bloqueado y dos no
        user1 = new User("maria01", "12345678A", "mipassword");
        user1.validate();
        user2 = new User("pablo88", "98765432Z", "hola");
        user2.validate();
        user2.block("Envia proyectos que solo le benefician a el");
        user3 = new User("jose21", "44455566K", "0000");
        user3.validate();

        // user1 se loguea y crea 3 colectivos
        Application.getApplication().setCurrentUser(user1);
        c1 = new Collective("COLECTIVO 1", "Descripcion del colectivo 1");
        c2 = new Collective("COLECTIVO 2", "Descripcion del colectivo 2");

        // user3 se une al colectivo c2
        c2.join(user3);

        // Se crean y se validan dos proyectos
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p2 = new SocialProject("P2", "Proyecto social p2", 44000, "grupo ", typeSocial.NACIONAL, user3);
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
        assertEquals("", user1.getBlockedMssg());
        assertEquals("Envia proyectos que solo le benefician a el", user2.getBlockedMssg());

        user2.unblock();
        assertEquals("", user2.getBlockedMssg());
    }

    @Test
    public void testGetCreatedCollectives() {
        assertTrue(user1.getCreatedCollectives().contains(c1));
        assertFalse(user1.getCreatedCollectives().isEmpty());
        assertEquals(2, user1.getCreatedCollectives().size());
    }

    @Test
    public void testGetMemberCollectives() {
        assertTrue(user1.getMemberCollectives().contains(c1));
        assertFalse(user2.getMemberCollectives().contains(c1));
        assertEquals(2, user1.getMemberCollectives().size());
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

        user1.setCreatedCollectives(created);
        assertTrue(user1.getCreatedCollectives().isEmpty());

        created.add(c4);
        user1.setCreatedCollectives(created);

        assertTrue(user1.getCreatedCollectives().contains(c4));
        assertEquals(1, user1.getCreatedCollectives().size());
    }

    @Test
    public void testSetMemberCollectives() {
        ArrayList<Collective> member = new ArrayList<Collective>();
        member.addAll(user3.getMemberCollectives());
        member.add(c1);
        user3.setMemberCollectives(member);

        assertTrue(user3.getMemberCollectives().contains(c1) && user3.getMemberCollectives().contains(c2));
        assertEquals(2, user3.getMemberCollectives().size());

    }

    @Test
    public void testSetFollowedProjects() {
        ArrayList<Project> followed = new ArrayList<Project>();
        followed.addAll(user3.getFollowedProjects());
        followed.add(p2);
        user3.setFollowedProjects(followed);

        assertTrue(user3.getFollowedProjects().contains(p1) && user3.getFollowedProjects().contains(p2));
        assertEquals(2, user3.getFollowedProjects().size());
    }

    @Test
    public void testAddNotification() {
        // addNotification() se llama desde el constructor de Notification

        assertEquals(2, user3.getNotifications().size());

        // nueva notificacion de un proyecto al que user3 sigue
        new NotificationUser(p1);
        assertEquals(3, user3.getNotifications().size());

        // nueva notificacion de un proyecto al que user3 no sigue
        new NotificationUser(p2);
        assertEquals(3, user3.getNotifications().size());
    }

    @Test
    public void testValidate() {
        Application.getApplication().register("ana", "98798798P", "contr");
        User u = Application.getApplication().getNonValidatedUsers().get(0);

        assertTrue(Application.getApplication().getNonValidatedUsers().contains(u));
        assertFalse(Application.getApplication().getUsers().contains(u));
        u.validate();
        assertFalse(Application.getApplication().getNonValidatedUsers().contains(u));
        assertTrue(Application.getApplication().getUsers().contains(u));
    }

    @Test
    public void testReject() {
        Application.getApplication().register("ana2", "98798799L", "contr");
        User u = Application.getApplication().getNonValidatedUsers().get(0);

        assertTrue(Application.getApplication().getNonValidatedUsers().contains(u));
        u.reject();
        assertFalse(Application.getApplication().getNonValidatedUsers().contains(u));
    }

    @Test
    public void testBlock() {
        assertFalse(user3.getBlocked());
        assertEquals(1, p1.getVotes());

        p1.vote(user3);
        assertEquals(2, p1.getVotes());
        p1.vote(c2);
        assertEquals(2, p1.getVotes());

        user3.block("Probando metodo block de User");
        assertTrue(user3.getBlocked());
        assertEquals(1, p1.getVotes());
    }

    @Test
    public void testUnblock() {
        p1.vote(user3);
        p1.vote(c2);

        assertEquals(2, p1.getVotes());
        user3.block("Probando metodo unblock de User");
        assertEquals(1, p1.getVotes());
        user3.unblock();

        assertFalse(user3.getBlocked());
        assertEquals(2, p1.getVotes());
    }

    // Metodos UserCollective: super clase de User
    @Test
    public void testGetCreatedProjects() {
        assertEquals(1, user1.getCreatedProjects().size());
        assertTrue(user1.getCreatedProjects().contains(p1));
    }

    @Test
    public void testGetVotedProjects() {
        p1.vote(user3);
        p1.vote(c2);
        assertTrue(user3.getVotedProjects().contains(p1));
        assertTrue(c2.getVotedProjects().contains(p1));
    }

    @Test
    public void testSetCreatedProjects() {
        ArrayList<Project> created = new ArrayList<Project>();
        created.addAll(user1.getCreatedProjects());
        created.add(p2);
        user1.setCreatedProjects(created);

        assertTrue(user1.getCreatedProjects().contains(p1) && user1.getCreatedProjects().contains(p2));
        assertEquals(2, user1.getCreatedProjects().size());
    }

    @Test
    public void testSetVotedProjects() {
        ArrayList<Project> voted = new ArrayList<Project>();
        voted.addAll(user1.getVotedProjects());
        voted.add(p2);
        user1.setVotedProjects(voted);

        assertTrue(user1.getVotedProjects().contains(p1) && user1.getVotedProjects().contains(p2));
        assertEquals(2, user1.getVotedProjects().size());
    }

}
