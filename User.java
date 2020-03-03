import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public abstract class User extends UserCollective{

    private String username;
    private String NIF;
    private String password;
    private boolean blocked;
    private Collective createdCollectives[];
    private Collective memberCollectives[];
    private Notification notifications[];
    private Project followedProjects[];


	/**
     * Constructor de un objeto de la clase User
	 * 
     * @param username username del objeto
	 * @param NIF NIF del objeto
	 * @param password contraseña del objeto
	 * @param blocked si el objeto esta o no bloqueado
     */

    public User(String username, String NIF, String password, boolean blocked,
                Collective createdCollectives[], Collective memberCollectives[], 
                Notification notifications[], Project followedProjects[]) {
        this.username = username;
        this.NIF = NIF;
        this.password = password;
        this.blocked = blocked;
        this.createdCollectives = createdCollectives;
        this.memberCollectives = memberCollectives;
        this.notifications = notifications;
        this.followedProjects = followedProjects;
	}


    /* GETTERS */
	public String getUsername() { return username; }
	public String getNIF() { return NIF; }
	public String getPassword() { return password; }
    public boolean getBlocked() { return blocked; }
    public Collective[] getCreatedCollectives() { return createdCollectives; }
    public Collective[] getMemberCollectives() { return memberCollectives; }
    public Notification[] getNotifications() { return notifications; }
    public Project[] getFollowedProjects() { return followedProjects; }


    /* SETTERS */
    public void setUsername(String username) { this.username = username; }
	public void setNIF(String NIF) { this.NIF = NIF; }
	public void setPassword(String password) { this.password = password; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public void setCreatedCollectives(Collective createdCollectives[]) { this.createdCollectives = createdCollectives; }
    public void setMemberCollectives(Collective memberCollectives[]) { this.memberCollectives = memberCollectives; }
    public void setNotifications(Notification notifications[]) { this.notifications = notifications; }
    public void setFollowedProjects(Project followedProjects[]) { this.followedProjects = followedProjects; }



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



}