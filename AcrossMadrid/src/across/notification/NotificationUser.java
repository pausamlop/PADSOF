package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;

import java.util.*;

/**
 * Clase NotificationUser que hereda de Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class NotificationUser extends Notification {

    private Project project;
    private ArrayList<User> receivers;


	/**
     * Constructor de la clase notificacion para usuario, para la notificacion
     * acerca del cambio de estado de proyectos
     * 
     * @param project proyecto del cual se notificara
     */
    public NotificationUser(Project p) { 
        receivers = new ArrayList<User>();
        this.project = p;
        generateReceivers();
        setMessage(generateMessage());
        sendNotification();
    }


    /**
     * Devuelve el proyecto asociado a la notificacion
     * 
     * @return proyecto del que se notifica
     */
    public Project getProject() { return project; }

    /**
     * Devuelve el array de usuarios que reciviran esta notificacion
     * 
     * @return array de usuarios notificados
     */
    public ArrayList<User> getReceivers() { return receivers; }

    /**
     * Establece el proyecto del que se notifica
     * 
     * @param p proyecto 
     */
    public void setProject(Project p) { this.project = p; }
    
    /**
     * Establece el array de usuarios que recibiran la notificacion
     * 
     * @param receivers array de usuarios
     */
    public void setReceivers(ArrayList<User> receivers) { this.receivers = receivers; }


    /**
     * Metodo que dependiendo del estado del proyecto a notificar, genera un mensaje personalizado
     * 
     * @return  mensaje a enviar
     */
    public String generateMessage(){
        String message = " ";
        projectState ps = project.getProjectState();

        switch (ps) {
            case ACEPTADO:
                message = "El proyecto:" + this.project.getName() +  ", se encuentra en estado de aceptado y puede ser votado";
                break;
                
            case VOTOSALCANZADOS:
                message = "El proyecto:" + this.project.getName() +  ", ha alcanzado el numero minimo de votos, su creador podra proceder a enviarlo para su financiación cuando vea oportuno";
                break;
                
            case ENVIADO:
                message = "El proyecto:" + this.project.getName() +  ", ha sido enviado a financiación";
                break;
                
            case FINANCIADO:
                message = "El proyecto:" + this.project.getName() +  ", va a recibir una financiacion de " + this.project.getCost() + "€";
                break;
                
            case NOFINANCIADO:
                message = "El proyecto:" + this.project.getName() +  ", no va a recibir financiacion";
                break;
                
            case CADUCADO:
                message = "El proyecto:" + this.project.getName() +  ", ha caducado, demasiado tiempo sin recibir apoyos de la comunidad";
                break;
                
            default:
                break;
        }

        return message;
    }

    /**
     * Metodo para obtener quienes son aquellos a los que enviar la notificacion
     */
    public void generateReceivers(){
  
        for(User user : project.getFollowers()){
            receivers.add(user);
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