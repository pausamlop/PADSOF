package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;
import across.application.Application;

import java.io.*;
import java.util.*;

/**
 * Clase NotificationAdminProject que hereda de Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class NotificationAdminProject extends Notification implements Serializable{

    private Project project;


	/**
     * Constructor de la clase notificacion para administrador, para la notificacion
     * cuando un proyecto es creado y esta pendiente de validacion
     * 
     * @param project
     */
    public NotificationAdminProject(Project p) { 
        super();
        this.project = p;
        sendNotification();
    }


    /* GETTERS */
    public Project getProject() { return project; }

    /* SETTERS */
    public void setProject(Project p) { this.project = p; }


    /**
     * Metodo que genera un mensaje para la notificacion
     * 
     * @return  mensaje a enviar
     */
    public String generateMessage(){
        return "El proyecto:" + project.getName() + ", esta listo para ser validado.";
    }


    /**
     * Método que envia la notificacion al administrador
     */
    public void sendNotification(){
        Application.getApplication().getAdmin().addNotification(this);
    }

}