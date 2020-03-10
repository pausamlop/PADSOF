import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class Notification{

    private String message;
    private Project project;
    private ArrayList<User> receivers = new ArrayList<User>();


	/**
     * Constructor de un objeto de la clase Admin
	 * 
	 * @param password contraseña del objeto
     */
    public Notification(String message, Project project, ArrayList<User> receivers) { 
        this.message = message; 
        this.project = project;
        this.receivers = receivers;
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
     * NO SE QUE HACE
	 * 
	 * @param projectState
     */
    public void notify(projectState ps){
        // NO SEEEEEE
    }


}