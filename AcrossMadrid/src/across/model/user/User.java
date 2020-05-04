package across.model.user;

import java.io.*;
import java.util.*;

import across.model.application.Application;
import across.model.enumerations.*;
import across.model.project.*;
import across.model.notification.*;

/**
 * Clase User que hereda de UserCollective
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class User extends UserCollective {

    private String username;
    private String NIF;
    private String password;
    private boolean blocked;
    private String blockedMssg = "";
    private ArrayList<Collective> createdCollectives = new ArrayList<Collective>();
    private ArrayList<Collective> memberCollectives = new ArrayList<Collective>();
    private ArrayList<Notification> notifications = new ArrayList<Notification>();
    private ArrayList<Project> followedProjects = new ArrayList<Project>();


	/**
     * Constructor de un objeto de la clase User
	 * 
     * @param username username del objeto
	 * @param NIF NIF del objeto
	 * @param password contraseña del objeto
	 * @param blocked si el objeto esta o no bloqueado
     * @param createdCollectives colectivos creados por el usuario
	 * @param memberCollectives colectivos de los que el usuario es miembro
	 * @param notifications notificaciones del usuario
	 * @param followedProjects proyectos seguidos por el usuario
     */
    public User(String username, String NIF, String password) {
        this.username = username;
        this.NIF = NIF;
        this.password = password;
        this.blocked = false;
    }

    /**
     * Devuelve el nombre del usuario
     * 
     * @return nombre del usuario
     */
    public String getUsername() { return this.username; }

    /**
     * Devuelve el NIF del usuario
     * 
     * @return NIF del usuario
     */
    public String getNIF() { return this.NIF; }

    /**
     * Devuelve la contraseña del usuario
     * 
     * @return contraseña del usuario
     */
    public String getPassword() { return this.password; }

    /**
     * Devuelve si el usuario esta en estado de bloqueado
     * 
     * @return true en caso de que este bloqueado, false en caso contrario
     */
    public boolean getBlocked() { return this.blocked; }

    /**
     * Devuelve el mensaje de bloqueo del usuario, en caso de que este lo haya sido
     * 
     * @return mensaje de bloque, en caso de que lo haya
     */
    public String getBlockedMssg() { return this.blockedMssg; }

    /**
     * Devuelve un array con todos lo colectivos creados por el usuario
     * 
     * @return array de colectivos creados
     */
    public ArrayList<Collective> getCreatedCollectives() { return this.createdCollectives;}

    /**
     * Devuelve un array con todos los colectivos de los cuales el usuario es miembro
     * 
     * @return array de colectivos a los que se pertence
     */
    public ArrayList<Collective> getMemberCollectives() { return this.memberCollectives;}

    /**
     * Devuelve un array con las notificaciones recibidas y aun no vistas por el usuario
     * 
     * @return array de notificaciones del usuario
     */
    public ArrayList<Notification> getNotifications() { return this.notifications; }

    /**
     * Devuelve un array con los proyectos seguidos por el usuario
     * 
     * @return array de proyectos seguidos
     */
    public ArrayList<Project> getFollowedProjects() { return this.followedProjects; }

    /**
     * Establece el array de colectivos creados por el usuario
     * 
     * @param createdCollectives array de colectivos
     */
    public void setCreatedCollectives(ArrayList<Collective> createdCollectives) { this.createdCollectives = createdCollectives; }

    /**
     * Establece el array de colectivos de los que el usuario es miembro
     * 
     * @param memberCollectives array de colectivos
     */
    public void setMemberCollectives(ArrayList<Collective> memberCollectives) { this.memberCollectives = memberCollectives; }

    /**
     * Establece el array de proyectos seguidos por el usuario
     * 
     * @param followedProjects array de proyectos
     */
    public void setFollowedProjects(ArrayList<Project> followedProjects) { this.followedProjects = followedProjects; }
    
    /**
     * Establece las notificaciones del usuario
     * 
     * @param notif notificaciones del usuario
     */
	public void setNotifications(ArrayList<Notification> notif) {
		notifications = notif;	
	}
	
    /**
     * Añade una notificacion la lista de notificaciones del usuario
     * 
     * @param notification
     */
    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }

	/**
     * Metodo para validar un usuario
     */
    public void validate(){
        ArrayList<User> u1 = new ArrayList<User>();
        u1.addAll(Application.getApplication().getUsers());
        u1.add(this);

        ArrayList<User> u2 = new ArrayList<User>();
        
        u2.addAll(Application.getApplication().getNonValidatedUsers());
        u2.remove(this);

        Application.getApplication().setUsers(u1);
        Application.getApplication().setNonValidatedUsers(u2);
    }

	/**
     * Metodo para rechazar un usuario
     */
    public void reject(){
        ArrayList<User> u1 = new ArrayList<User>();
        u1.addAll(Application.getApplication().getNonValidatedUsers());
        u1.remove(this);

        Application.getApplication().setNonValidatedUsers(u1);
    }

	/**
     * Bloquea a un usuario
     * 
     * @param mssg mensaje que le envia el administrador al usuario cuando lo bloquea
     */
    public void block(String mssg){ 
        blocked = true; 
        blockedMssg = mssg;

        // update todos los votos como colectivo
        for (Collective c: memberCollectives) c.updateFamilyVotes();

        // update todos los votos como usuario
        for (Project p: getVotedProjects()) p.updateVotes();

    }

	/**
     * Metodo para desbloquear a un usuario
     */
    public void unblock(){ 
        blocked = false;
        blockedMssg = "";

        // update todos los votos como colectivo
        for (Collective c: memberCollectives) c.updateFamilyVotes();

        // update todos los votos como usuario
        for (Project p: getVotedProjects()) p.updateVotes();
    }

    /**
     * Forma un String para poder visualizar correctamente toda la informacion
     * importante relativa a User al imprimirlo por pantalla
     * 
     * @return infomacion del objeto User
     */
    public String toString(){
        return username;

    }
}
