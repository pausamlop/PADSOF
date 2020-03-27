package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;
import across.application.Application;

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
    private User user;
    private ArrayList<User> receivers = new ArrayList<User>();
    private boolean isTypeProject;


	/**
     * Constructor de la clase notificacion, para la notificacion acerca de proyectos
     * 
     * @param project
     */
    public Notification(Project project) { 
        this.isTypeProject = true;
        this.project = project;
        this.message = messageByProjectState(project.getProjectState());
        notify();
    }

    /**
     * Constructor de la clase notificacion, para la notificacion acerca de usuarios
     * @param user
     */
    public Notification(User user) { 
        this.isTypeProject=false;
        this.user = user;
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

    /**
     * Metodo que dependiendo del estado del proyecto a notificar, genera un mensaje personalizado
     * @param ps estado del proyecto
     * @return  mensaje a enviar
     */
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
    /**
     * Metodo para obtener quienes son aquellos a los que enviar la notificacion
     * @param ps estado del proyecto a notificar
     * @return receptores de la notificacion
     */
    private ArrayList<User> receiversByProjectState(projectState ps){
        ArrayList<User> receivers = new ArrayList<>();

        if(User.class == this.project.getCreator().getClass()){
            receivers.add((User) this.project.getCreator());
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
     * Notifica a los usuarios en cuestion acerca del proceso en funcion del estado del mismo.
     * En caso de ser un proyecto recien creado, por ende, en el estado inicial tambien le será notificado al administrador
	 * 
     */
    public void notifyProject(){
        projectState ps = this.project.getProjectState();

        this.setReceivers(receiversByProjectState(ps));
        this.setMessage(messageByProjectState(ps));

        for(User user : this.receivers){
            user.addNotification(this);
        }

        if(ps == projectState.ENVALIDACION){
            Notification admin = new Notification(this.project);
            admin.setMessage("El proyecto:" + this.project.getName() + ", esta listo para ser validado.");
            Application.getApplication().getAdmin().addNotification(admin);
        }

    }

    /**
     * Notifica acerca del usuario con el cual la notificacion ha sido inicializada.
     * En caso de que se acabe de registrar, se usará para enviar la notificación al admin para que se le valide.
     * En caso de que haya sido bloqueado, será utilizada para que el admin le envíe un mensaje de bloqueo.
     * 
     */
    public void notifyUser(){

        Application app = Application.getApplication();

        if(app.getNonValidatedUsers().contains(this.user)){
            this.setMessage("El usuario:" + user.getUsername() + ",\n de NIF:" + user.getNIF() + ", esta pendiente de validacion");
            app.getAdmin().addNotification(this);
        }

        if(this.user.getBlocked()){
                this.setMessage(user.getBlockedMssg());
                user.addNotification(this);
        }

    }




}