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

public class CollectiveTest {

    User u1;
    User u2;
    User u3;
    User u4;
    User u5;
    User u6;

    Collective c1;
    Collective c11;
    Collective c12;
    Collective c111;

    Project p1;
    Project p2;

	@Before
	public void setUp(){

        // crear, validar y loguear u1
        u1 = new User("maria11", "12345678J", "password");
        u1.validate();
        Application.getApplication().setCurrentUser(u1);

        // crear familia de colectivos, todos creados por currentUser, que es u1
        c1 = new Collective("COLECTIVO 1","Descripcion c1");
        c11 = new Collective("COLECTIVO 1.1","Hijo de c1",c1);
        c12 = new Collective("COLECTIVO 1.2","Hijo de c1",c1);
        c111 = new Collective("COLECTIVO 1.1.1", "Hijo de c11, nieto de c1", c11);

        // crear usuarios y validarlos
        u2 = new User("U2","11111111A", "soyu2");
        u3 = new User("U3","22222222B", "soyu3");
        u4 = new User("U4","33333333C", "soyu4");
        u5 = new User("U5","44444444D", "soyu5");
        u6 = new User("U6","55555555E", "soyu6");
        u2.validate();
        u3.validate();
        u4.validate();
        u5.validate();
        u6.validate();

        // unir usuarios a colectivos
        c1.join(u6);
        c11.join(u2);
        c12.join(u2);
        c12.join(u3);
        c111.join(u4);
        c111.join(u5);

        // crear y validar proyectos
        p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", u3);
        p2 = new SocialProject("P2", "Proyecto social p2", 44000, "grupo ", typeSocial.NACIONAL, u5);
        p1.validate();
        p2.validate();

        // votar proyectos
        p1.vote(c1);
        
    }
    
    @Test
	public void testAddChild() {
        Collective c121 = new Collective("COLECTIVO 1.2.1", "Hijo de c12, nieto de c1");
        assertFalse(c12.getChildren().contains(c121));
        c12.addChild(c121);
        assertTrue(c12.getChildren().contains(c121));
	}

	@Test
	public void testGetChildrenMembers() {
        Set<User> members = c11.getChildrenMembers();
        assertTrue(members.contains(u1) && members.contains(u2) && members.contains(u4) && 
                    members.contains(u5));
        assertFalse(members.contains(u3));
        assertEquals(4, members.size());
	}

	@Test
	public void testGetAllFamilyMembers() {
		Set<User> family = c11.getAllFamilyMembers();
        assertTrue(family.contains(u1) && family.contains(u2) && family.contains(u4) && 
                    family.contains(u5) && family.contains(u6));
        assertFalse(family.contains(u3));
        assertEquals(5, family.size());
	}

	@Test
	public void testUpdateFamilyVotes() {
        assertEquals(6, p1.getVotes());
        u5.block("Prueba de actualizacion de votos");
        c111.updateFamilyVotes(); 
        assertEquals(5, p1.getVotes());
        c11.disjoin(u2);
        //c11.updateFamilyVotes();
        //assertEquals(4, p1.getVotes());
	}

	@Test
	public void testJoin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisjoin() {
		fail("Not yet implemented");
	}

}
