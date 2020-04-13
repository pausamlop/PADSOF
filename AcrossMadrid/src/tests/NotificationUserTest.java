package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import across.model.notification.NotificationUser;
import across.model.project.InfraestructureProject;
import across.model.user.User;

public class NotificationUserTest {
	
	private User user1;
	private User user2;
	private NotificationUser notification;
	private InfraestructureProject p1;
	
	@Before
	public void setUp() {
		user1 = new User("maria01", "12345678A", "mipassword");
		user1.validate();
		user2 = new User("pablo88", "98765432Z", "hola");
        user2.validate();
		p1 = new InfraestructureProject("P1", "Proyecto de infraestructura p1", 25000, "p1.jpg", "Retiro", user2);
		p1.validate();
		
		p1.follow(user1);
		
		notification = new NotificationUser(p1);
	}

	@Test
	public final void testGenerateMessage() {
		assertEquals(notification.getMessage(), user1.getNotifications().get(0).getMessage());
	}

	@Test
	public final void testSendNotification() {
		assertEquals(notification, user1.getNotifications().get(0));
	}

	@Test
	public final void testGenerateReceivers() {
		//Las notificaciones de un determinado proyecto son recibidas por sus seguidores asi:
		assertEquals(user1, notification.getReceivers().get(0));
	}

}
