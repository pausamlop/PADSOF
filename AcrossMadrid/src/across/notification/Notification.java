package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;

import java.lang.*;
import java.io.*;
import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class Notification implements Serializable{

    private String message;
    private Project project;
    private ArrayList<User> receivers = new ArrayList<User>();


	/**
     * Constructor de un objeto de la clase Admin
	 * 
	 * @param password contraseña del objeto
     */
    public Notification(Project project) { 
        this.project = project;
        this.message = messageByProjectState(project.getProjectState());
        notify();
    }



    /* GETTERS */
    public String getMessage() { return message; }
    public Project getProject() { return project; }
    public ArrayList<User> getReceivers() { return receivers; }


    /* SETTERS */
	public void setMessage(String message) { this.message = message; }
    public void setProject(Project project) { this.project = project; }
    public void setReceivers(ArrayList<User> receivers) { this.receivers = receivers; }


    private String messageByProjectState(projectState ps){
        String message = " ";

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

    private ArrayList<User> receiversByProjectState(projectState ps){
        ArrayList<User> receivers = new ArrayList<>();

        if(User.class == this.project.getCreator().getClass()){
            receivers.add(this.project.getCreator());
        } else{
            Collective collective = (Collective) this.project.getCreator();

            receivers.add(collective.getManager());
        }
        
        if (ps == projectState.VOTOSALCANZADOS || ps == projectState.ENVIADO || ps==projectState.FINANCIADO || ps==projectState.NOFINANCIADO || ps==projectState.CADUCADO){
           
            for(User user : this.project.getFollowers()){
                receivers.add(user);
            }
        }

        return receivers;
    }

	/**
     * Notifica a los usuarios en cuestion el mensaje en cuestion, todo esto, en funcion del
     * estado del proceso
	 * 
	 * @param projectState
     */
    public void notify(projectState ps){

        this.setReceivers(receiversByProjectState(ps));

        for(User user : this.receivers){
            user.addNotification(this);
        }
    }




    public String toString() {
        return message;
    }
}