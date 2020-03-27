package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;
import across.application.Application;

import java.io.*;
import java.util.*;

/**
 * Clase NotificationUser que hereda de Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class NotificationUser extends Notification implements Serializable{

    private Project project;
    private ArrayList<User> receivers = new ArrayList<User>();


	/**
     * Constructor de la clase notificacion para usuario, para la notificacion
     * acerca del cambio de estado de proyectos
     * 
     * @param project
     */
    public NotificationUser(Project p) { 
        super();
        this.project = p;
        generateReceivers();
        sendNotification();
    }


    /* GETTERS */
    public Project getProject() { return project; }
    public ArrayList<User> getReceivers() { return receivers; }

    /* SETTERS */
	public void setProject(Project p) { this.project = p; }
    public void setReceivers(ArrayList<User> receivers) { this.receivers = receivers; }


    /**
     * Metodo que dependiendo del estado del proyecto a notificar, genera un mensaje personalizado
     * 
     * @return  mensaje a enviar
     */
    private String generateMessage(){
        String message = " ";
        projectState ps = project.getProjectState();

        switch (ps) {
            case ENVALIDACION:
                message = "El proyecto:" + this.project.getName() +  ", ha sido enviado a nuestro servicio de validación, pronto será notificado acerca del nuevo estado del mismo";
            case ACEPTADO:
                message = "El proyecto:" + this.project.getName() + ", ha sido aceptado, la comunidad podrá acceder a el y darle su apoyo";
            case RECHAZADO:
                message = "El proyecto:" + this.project.getName() +  ", ha sido rechazado";
            case VOTOSALCANZADOS:
                message = "El proyecto:" + this.project.getName() +  ", ha alcanzado el numero minimo de votos, podra proceder a enviarlo para su financiación cuando vea oportuno";
            case ENVIADO:
                message = "El proyecto:" + this.project.getName() +  ", ha sido enviado a financiación";
            case FINANCIADO:
                message = "El proyecto:" + this.project.getName() +  ", va a recibir financiacion";
            case NOFINANCIADO:
                message = "El proyecto:" + this.project.getName() +  ", no va a recibir financiacion";
            case CADUCADO:
                message = "El proyecto:" + this.project.getName() +  ", ha caducado, demasiado tiempo sin recibir apoyos de la comunidad";
        }

        return message;
    }

    /**
     * Metodo para obtener quienes son aquellos a los que enviar la notificacion
     */
    private void generateReceivers(){
        projectState ps = project.getProjectState();
        receivers = new ArrayList<>();

        if(User.class == project.getCreator().getClass()){
            receivers.add(this.project.getCreator());
        } else{
            Collective collective = this.project.getCreator();

            receivers.add(collective.getManager());
        }
        
        if (ps == projectState.VOTOSALCANZADOS || ps == projectState.ENVIADO || 
            ps==projectState.FINANCIADO || ps==projectState.NOFINANCIADO || 
            ps==projectState.CADUCADO){
           
            for(User user : project.getFollowers()){
                receivers.add(user);
            }
        }
    }

    /**
     * Método que envia la notificacion a cada uno de los usuarios receptores
     */
    public void sendNotification(){
        for(User user : this.receivers){
            user.addNotification(this);
        }
    }

}