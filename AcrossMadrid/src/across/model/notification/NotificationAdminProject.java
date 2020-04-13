package across.model.notification;

import across.model.project.*;
import across.model.user.*;
import across.model.application.Application;


/**
 * Clase NotificationAdminProject que hereda de Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class NotificationAdminProject extends Notification {

    private Project project;


	/**
     * Constructor de la clase notificacion para administrador, para la notificacion
     * cuando un proyecto es creado y esta pendiente de validacion
     * 
     * @param project proyecto del que se va notificar
     */
    public NotificationAdminProject(Project p) { 
        this.project = p;
        setMessage(generateMessage());
        sendNotification();
    }


    /**
     * Devuelve el proyecto del cual se esta notificando
     * 
     * @return proyecto notificado
     */
    public Project getProject() { return project; }

    /**
     * Establece el proyecto de la notificaion
     * 
     * @param p proyecto
     */
    public void setProject(Project p) { this.project = p; }


    /**
     * Metodo que genera un mensaje para la notificacion
     * 
     * @return  mensaje a enviar
     */
    public String generateMessage(){
        String mssg = "El proyecto: " + project.getName() + " creado por ";
        if (project.getCreator() instanceof Collective)
            mssg += "el colectivo " + ((Collective)project.getCreator()).getName();
        else
            mssg += "el usuario " + ((User)project.getCreator()).getUsername();
            
        mssg += " esta listo para ser validado";
        return mssg;
    }


    /**
     * Método que envia la notificacion al administrador
     */
    public void sendNotification(){
        Application.getApplication().getAdmin().addNotification(this);
    }

}