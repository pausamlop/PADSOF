package tests;

import across.model.application.Application;
import across.model.notification.*;
import across.model.user.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NotificationAdminUserTest {
	
	private User user1;
	private NotificationAdminUser notification;
	
	@Before
	public void setUp() {
		user1 = new User("maria01", "12345678A", "mipassword");
		user1.validate();
		
		
		notification = new NotificationAdminUser(user1);
	}

	@Test
	public final void testGenerateMessage() {
		//Lo hacemos asi, ya que cuando creamos la noticacion, ya se hace la funcion generateMessage
		assertEquals(notification.generateMessage(), notification.getMessage());
	}

	@Test
	public final void testSendNotification() {
		//Lo hacemos asi, ya que cuando creamos la notificacion, ya se hace la llamada a la funcion SendNotification
		assertEquals(Application.getApplication().getAdmin().getNotifications().get(0), notification);
	}

}
