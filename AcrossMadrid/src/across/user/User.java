package across.user;

import across.application.Application;
import across.enumerations.*;
import across.user.*;
import across.project.*;
import across.notification.*;

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
     * Rechaza un usuario
	 * 
     * @return boolean para comprobar errores
     */
    public void reject(){
        ArrayList<User> u1 = new ArrayList<User>();
        u1.addAll(Application.getApplication().getNonValidatedUsers());
        u1.remove(this);

        Application.getApplication().setNonValidatedUsers(u1);

    }
    /**
     * Añade una notificacion a la lista
     * 
     * @param notification
     */
    public void addNotification(Notification notification){
        this.notifications.add(notification);
    }
    /**
     * Nos permite acceder al mensaje asociado a la notificacion
     * 
     * @param notification
     * @return mensaje de la notificacion
     */
    public String getNotificationMessage(Notification notification){
        return notification.getMessage();
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
        Application app = Application.getApplication();
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
            System.out.println("Filtrar colectivos(fc)");
            System.out.println("Crear proyecto (p)");
            System.out.println("Buscar proyectos (bp)");
            System.out.println("Filtrar proyectos (fp)");
            System.out.println("Notificaciones (n)"); 
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String opc = reader.readLine();
                switch (opc) {
                    case "yo":
                        toString();
                        break;
                    case "ip": // popularidad
                        System.out.println(app.popularityReport()); 
                        break;
                    case "ia": // informe afinidad
                        System.out.println("Selecciones colectivo a anlizar: ");
                        for (int i = 0; i < memberCollectives.size(); i++)
                            System.out.println("%d. %s", i+1, memberCollectives.get(i).getName());
                        opc = reader.readLine();
                        try{
                            System.out.println(app.affinityReport(memberCollectives.get(Integer.parseInt(opc)-1))); 
                        }
                        catch(NumberFormatException excep){
                            System.out.println(app.affinityReport(memberCollectives.get(0))); 
                        }
                        break;
                    case "c": // crear colectivo
                        System.out.println("Nombre del colectivo, descripcion, padre:");
                        String name = reader.readLine();
                        String dcp = reader.readLine();
                        for (int i = 0; i < app.getCollectives().size(); i++) 
                            System.out.println("%d. %s", i+1, app.getCollectives().get(i).getName());
                        opc = reader.readLine();
                        Collective parent;
                        if (opc == "") parent = null;
                        else parent = app.getCollectives().get(Integer.parseInt(opc)-1);
                        ArrayList<Collective> aux = app.getCollectives();
                        aux.add(new Collective(name, dcp, parent));
                        app.setCollectives(aux);
                        break;
                    case "bc":
                        // buscar colectivo
                        break;
                    case "fc":
                        // buscar colectivo
                        break; 
                    case "p": // crear proyecto
                        System.out.println("Nombre del proyecto, descripcion, coste, tipo (S/I):");
                        String name2 = reader.readLine();
                        String dcp2 = reader.readLine();
                        double cost;
                        try{
                            cost = Double.parseDouble(reader.readLine());
                        }
                        catch(NumberFormatException excep){
                            cost = 5000;
                        }
                        if (reader.readLine() == "I"){
                            System.out.println("Imagen: ");
                            String image = reader.readLine();
                            District d = new District("Retiro");
                            app.addNewProject(new InfraestructureProject(name2, dcp2, cost, image,d));
                        }
                        else{
                            System.out.println("Grupo social y ambito (N/I):");
                            String group = reader.readLine();
                            typeSocial type = (reader.readLine() == "N")?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                            app.addNewProject(new SocialProject(name2, dcp2, cost, group, type));
                        }
                        break;
                    case "bp": // buscar proyecto
                        System.out.println("Buscar proyecto: ");
                        // app.searchProject(reader.readLine());
                        break;
                    case "fp":
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


    public String displayProjects(ArrayList<Project> projects){
        String out = "";
        for (int i = 0; i < projects.size(); i++){
            System.out.println("%d. %s", i+1, projects.get(i).getName());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opc = Integer.parseInt(reader.readLine());
        }catch(IOException exc){
            exc.printStackTrace();
        }catch(NumberFormatException exc){
            opc = 1;
        }
        Project p = project.get(i+1);
        System.out.println(p);
        if (getVotedProjects().contains(p))
            System.out.println("Has votado este proyecto");
        if (followedProjects.contains(p))
            System.out.println("Sigues a este proyecto");
        
        System.out.println("\nElige una opcion: votar (v), votar como colectivo(vc), seguir (s) o dejar de seguir (d)");
        String opc = reader.readLine();
        switch (opc) {
            case "v": //vote
                p.vote(this);
            case "vc": //votar como colectivo

            case "s": //seguir
                p.follow(this);
            case "ds": //dejar de seguir
                p.unfollow(this);
            case "q":
                PrincipalUser();
        }
        close(reader);
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