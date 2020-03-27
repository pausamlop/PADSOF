package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;
import across.application.Application;

import java.io.*;
import java.util.*;

/**
 * Clase NotificationAdminUser que hereda de Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class NotificationAdminUser extends Notification implements Serializable{

    private User user;


	/**
     * Constructor de la clase notificacion para el administrador, para la notificacion
     * cuando un usuario se ha registrado y esta pendiende de validacion
     * 
     * @param project
     */
    public NotificationAdminUser(User u) { 
        super();
        this.user = u;
        sendNotification();
    }


    /* GETTERS */
    public Project getUser() { return user; }

    /* SETTERS */
    public void setUser(User u) { this.user = u; }


    /**
     * Metodo que genera un mensaje para la notificacion
     * 
     * @return  mensaje a enviar
     */
    private String generateMessage(){
        return "El usuario:" + user.getUsername() + ",\n de NIF:" + user.getNIF() + ", esta pendiente de validacion";
    }


    /**
     * Método que envia la notificacion a cada uno de los usuarios receptores
     */
    public void sendNotification(){
        Application.getApplication().getAdmin().addNotification(this);
    }

}