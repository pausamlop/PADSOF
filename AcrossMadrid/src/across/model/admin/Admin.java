package across.model.admin;

import across.model.notification.*;
import java.io.*;
import java.util.*;

/**
 * Clase Administrador
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class Admin implements Serializable {

    private String password = "soyadmin";
    private ArrayList<Notification> notifications = new ArrayList<Notification>();

    /**
     * Devuelve la contraseña del administrador
     * 
     * @return contraseña del admin
     */
    public String getPassword() { return password; }
    
    /**
     * Establece la contraseña del administrador
     * 
     * @param password nueva contraseña
     */
	public void setPassword(String password) { this.password = password; }

	/**
     * Comprueba que la contrasena coincida con la del administrador
	 * 
	 * @param pass password del objeto
     * @return true si la contrasena es correcta, false sino
     */
    public boolean login(String pass){
        if (password.equals(pass)) return true;
        else return false;
    }

    /**
     * Metodo para obtener el array de notificaciones del administrador
     * @return notificaciones del admin
     */
    public ArrayList<Notification> getNotifications(){
        return notifications;
    }
    /**
     * Metodo para establecer el array de notificaicones
     * @param notifications lista de notificaciones
     */
    public void setNotifications(ArrayList<Notification> notifications){
        this.notifications = notifications;
    }

    /**
     * Metodo para añadir una notificacion a la lista de notificaciones del administrador
     * 
     * @param notificacion notificacion a añadir
     */
    public void addNotification(Notification notificacion){
        this.notifications.add(notificacion);
    }

}