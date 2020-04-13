package tests;

import across.model.application.Application;
import across.model.enumerations.typeSocial;
import across.model.notification.*;
import across.model.user.*;
import across.model.project.*;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NotificationAdminProjectTest {
	private User user1;
    private User user2;
	private Project p1;
	private Project p2;
	private NotificationAdminProject notification1;
	private NotificationAdminProject notification2;

	@Before
	public void setUp() {
		user1 = new User("maria01", "12345678A", "mipassword");
        user2 = new User("pablo88", "98765432Z", "hola");
        
		p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user1);
        p2 = new SocialProject("P2", "Proyecto social p2", 44000, "grupo ", typeSocial.NACIONAL, user2);
		
		notification1 = new NotificationAdminProject(p1);
		notification2 = new NotificationAdminProject(p2);
	}

	@Test
	public final void testGenerateMessage() {
		//Lo hacemos asi, ya que cuando creamos la noticacion, ya se hace la funcion generateMessage
		assertEquals(notification1.generateMessage(), notification1.getMessage());
		assertEquals(notification2.generateMessage(), notification2.getMessage());
	}

	@Test
	public final void testSendNotification() {
		//Lo hacemos asi, ya que cuando creamos la notificacion, ya se hace la llamada a la funcion SendNotification
		//Cogemos las posiciones 2 y 3 del array ya que los constructores de los proyectos, envian cada uno una notificacion
		assertEquals(notification1, Application.getApplication().getAdmin().getNotifications().get(2));
		assertEquals(notification2, Application.getApplication().getAdmin().getNotifications().get(3));
	}

}
