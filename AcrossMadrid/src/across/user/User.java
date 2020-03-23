package across.user;

import across.enumerations.*;
import across.collective.*;
import across.project.*;
import across.notification.*;
import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class User extends UserCollective implements Serializable {

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
        setUsers(getUsers().add(this));
        setNonValidatedUsers(getNonValidatedUsers().remove(this));
    }


	/**
     * Rechaza un usuario
	 * 
     * @return boolean para comprobar errores
     */
    public void reject(){
        setNonValidatedUsers(getNonValidatedUsers().remove(this));
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


    public void PrincipalUser(){
        if (blocked){
                System.out.println("Ha sido bloqueado por el administrador");
                // mensaje de Admin
                // solo puede cerrar sesion
        }
        else{
            System.out.println("Ver mi perfil (yo)"); // hecho
            System.out.println("Solicitar informe popularidad (ip)");
            System.out.println("Solicitar informe afinidad (ia)"); // listado de sus colectivos
            System.out.println("Crear colectivo (c)");
            System.out.println("Buscar colectivos (bc)");
            System.out.println("Crear proyecto (p)");
            System.out.println("Buscar proyectos (bp)");
            System.out.println("Notificaciones (n)"); 
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String opc = reader.readLine();
                switch (opc) {
                    case "yo":
                        toString();
                        break;
                    case "ip":
                        // informe pop
                        break;
                    case "ia":
                        // informe afinidad
                        break;
                    case "c":
                        // crear colectivo
                        break;
                    case "bc":
                        // buscar colectivo
                        break;
                    case "p":
                        // crear proyecto
                        break;
                    case "bp":
                        // buscar proyecto
                        break;
                    case "n":
                        // ver notificaciones
                        break;
                }
            }catch(IOException exception){
                exception.printStackTrace();
            }
        }
    }

    public String toString(){
        String perfil = "";
        perfil += "Nombre de usuario: " + username;
        perfil += "\nNIF: " + NIF;
        perfil += "\nProyectos creados:";
        for (Project p: getCreatedProjects()) resumen += "\n" + p.toString();
        perfil += "\n\nProyectos votados:";
        for (Project p: getVotedProjects()) resumen += "\n" + p.toString();
        perfil += "\n\nProyectos seguidos:";
        for (Project p: followedProjects) resumen += "\n" + p.toString();
        perfil += "\n\nColectivos creados: ";
        for (Collective c: createdCollectives) resumen += "\n" + c.toString();
        perfil += "\n\nColectivos a los que pertenezco: ";
        for (Collective c: memberCollectives) resumen += "\n" + c.toString();
        return perfil;

    }

}