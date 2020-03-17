package across;
import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class User extends UserCollective{

    private String username;
    private String NIF;
    private String password;
    private boolean blocked;
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


    public User(String username, String NIF, String password, boolean blocked) {
        this.username = username;
        this.NIF = NIF;
        this.password = password;
        this.blocked = blocked;
    }


    /* GETTERS Y SETTERS */

    public String getUsername() { return this.username; }
    public String getNIF() { return this.NIF; }
    public String getPassword() { return this.password; }
    public boolean getBlocked() { return this.blocked; }
    public ArrayList<Collective> getCreatedCollectives() { return this.createdCollectives;}
    public ArrayList<Collective> getMemberCollectives() { return this.memberCollectives;}
    public ArrayList<Notification> getNotifications() { return this.notifications; }
    public ArrayList<Project> getFollowedProjects() { return this.followedProjects; }


    public void setUsername(String username) { this.username = username; }
    public void setNIF(String NIF) { this.NIF = NIF; }
    public void setPassword(String password) { this.password = password; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public void setCreatedCollectives(ArrayList<Collective> createdCollectives) { this.createdCollectives = createdCollectives; }
    public void setMemberCollectives(ArrayList<Collective> memberCollectives) { this.memberCollectives = memberCollectives; }
    public void setNotifications(ArrayList<Notification> notifications) { this.notifications = notifications; }
    public void setFollowedProjects(ArrayList<Project> followedProjects) { this.followedProjects = followedProjects; }



	/**
     * Valida un usuario
     */
    public void validate(){

    }


	/**
     * Rechaza un usuario
	 * 
     * @return boolean para comprobar errores
     */
    public void reject(){

    }


	/**
     * Comprueba que el username y el password coinciden con los del objeto
	 * 
     * @param name username del objeto
	 * @param pass password del objeto
     * @return true si coinciden, false si no coinciden
     */
   //@Override
    public boolean login(String name, String pass){
        if (username.equals(name) && password.equals(pass)) return true;
        else return false;
    }


	/**
     * Bloquea a un usuario
     */
    public void block(){ blocked = true; }


	/**
     * Desbloquea a un usuario
     */
    public void unblock(){ blocked = false; }

    
    /************* MAIN DE PRUEBA **************/

    public static void main(String[] args) {

        User u = new User("laura", "23685412D", "hola22", false);

        System.out.println(u);


    }




}