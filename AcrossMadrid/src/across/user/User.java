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
            System.out.println("Ver mi perfil (yo)"); 
            System.out.println("Solicitar informe popularidad (ip)");
            System.out.println("Solicitar informe afinidad (ia)"); 
            System.out.println("Crear colectivo (c)");
            System.out.println("Buscar colectivos (bc)");
            System.out.println("Crear proyecto (p)");
            System.out.println("Buscar proyectos (bp)");
            System.out.println("Filtrar proyectos (fp)");

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String opc = reader.readLine();

                switch (opc) {
                    case "yo": //perfil
                        toString();

                    case "ip": // popularidad
                        System.out.println(app.popularityReport()); 

                    case "ia": // informe afinidad
                        System.out.println("Selecciones colectivo a anlizar: ");
                        if (memberCollectives.size() == 0){
                            System.out.println("Todavia no perteneces a ningun colectivo");
                            PrincipalUser();
                        }
                        for (int i = 0; i < memberCollectives.size(); i++)
                            System.out.println("%d. %s", i+1, memberCollectives.get(i).getName());
                        String num_col = reader.readLine();
                        try{
                            System.out.println(app.affinityReport(memberCollectives.get(Integer.parseInt(num_col)-1))); 
                        }
                        catch(NumberFormatException excep){
                            System.out.println(app.affinityReport(memberCollectives.get(0))); 
                        }

                    case "c": // crear colectivo
                        System.out.println("Nombre del colectivo, descripcion, padre:");
                        String name = reader.readLine();
                        String dcp = reader.readLine();
                        for (int i = 0; i < app.getCollectives().size(); i++) 
                            System.out.println("%d. %s", i+1, app.getCollectives().get(i).getName());
                        String parent_str = reader.readLine();
                        Collective parent;
                        if (parent_str == "") parent = null;
                        else parent = app.getCollectives().get(Integer.parseInt(parent_str)-1);
                        ArrayList<Collective> aux = app.getCollectives();
                        aux.add(new Collective(name, dcp, parent));
                        app.setCollectives(aux);

                    case "bc": // buscar colectivo
                        System.out.println("Buscar proyecto: ");
                        displayCollectives(app.searchProject(reader.readLine()));

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
                        String tipo = reader.readLine();
                        if (tipo == "I"){
                            System.out.println("Imagen: ");
                            String image = reader.readLine();
                            String d = new District("Retiro");
                            app.addNewProject(new InfraestructureProject(name2, dcp2, cost, image,d));
                        }
                        else if (tipo == "S"){
                            System.out.println("Grupo social y ambito (N/I):");
                            String group = reader.readLine();
                            typeSocial type = (reader.readLine() == "N")?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                            app.addNewProject(new SocialProject(name2, dcp2, cost, group, type));
                        }
                        else{
                            reader.close();
                            PrincipalUser();
                        }

                    case "bp": // buscar proyecto
                        System.out.println("Buscar proyecto: ");
                        displayProjects(app.searchProject(reader.readLine()));

                    case "fp": // filtrar proyecto
                        System.out.println("Filtrar proyecto por estado (e), tipo social (ts) o infraestructura (ti)");
                        String filtro = reader.readLine();
                        if (filtro == "e"){
                            System.out.println("Escriba estado a buscar: ACEPTADO(1), VOTOSALCANZADOS(3), ENVIADO(4), FINANCIADO(5), CADUCADO(7):");
                            int state;
                            try{
                                state = Integer.parseInt(reader.readLine());
                            }catch(NumberFormatException ex){
                                state = 1;
                            }
                            displayProjects(app.filterProject(state));
                        }
                        else if (filtro == "ts"){
                            System.out.println("Buscar por ambito (N/I), por grupo (escribir grupo) o nada (enter):");
                            String opc2 = reader.readLine();
                            if (opc2 == "N")
                                displayProjects(app.filterSocialProject(typeSocial.NACIONAL));
                            else if(opc2 == "I")
                                displayProjects(app.filterSocialProject(typeSocial.INNACIONAL));
                            else 
                                displayProjects(app.filterSocialProject(opc2));   
                        }
                        else if (filtro == "ti"){
                            System.out.println("Esciba distriro a buscar o pulse enter ");
                            displayProjects(app.filterSocialProject(reader.readLine()));
                        }
                        else{
                            reader.close();
                            PrincipalUser();
                        }

                    case "q": //cerrar sesion
                        /*****************************/
                    default: // otro
                        PrincipalUser();
                    reader.close();
                }
            }catch(IOException exception){
                exception.printStackTrace();
            }
            PrincipalUser();
        }
    }


    private void displayProjects(ArrayList<Project> projects){
        if (projects.size()){
            System.out.println("No hay proyectos");
            PrincipalUser();
        }
        for (int i = 0; i < projects.size(); i++){
            System.out.println("%d. %s", i+1, projects.get(i).getName());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opc = Integer.parseInt(reader.readLine());
            if (opc >= projects.size() || opc < 0)
                displayProjects(projects);
        }catch(IOException exc){
            exc.printStackTrace();
        }catch(NumberFormatException exc){
            displayProjects(projects);
        }
        Project p = project.get(opc);
        System.out.println(p);
        if (getVotedProjects().contains(p))
            System.out.println("Has votado este proyecto");
        if (followedProjects.contains(p))
            System.out.println("Sigues a este proyecto");
        
        System.out.println("\nElige una opcion: votar (v), votar como colectivo(vc), seguir (s) o dejar de seguir (d)");
        
        try{
            String opc = reader.readLine();

            switch (opc) {
                case "v": //vote
                    p.vote(this);

                case "vc": //votar como colectivo
                    if (createdCollectives.size() == 0){
                        System.out.println("No has creado ningun colectivo todavia");
                        displayProjects(projects);
                    }
                    for (int i = 0; i < createdCollectives.size(); i++)
                        System.out.println("%d. %s", i+1, createdCollectives.get(i).getName());
                    
                    int num_col;
                    try{
                        num_col = Integer.pargeInteger(reader.readLine()) - 1;
                    }catch(NumberFormatException ex){
                        num_col = 0;
                    } finally{
                        p.vote(createdCollectives.get(num_col));
                    }

                case "s": //seguir
                    p.follow(this);

                case "ds": //dejar de seguir
                    p.unfollow(this);

                case "q": //cerrar sesion
                    /**************************/

                default: // otro
                    displayProjects();
            }
            reader.close();
        }
        catch(IOException exc){
            exc.printStackTrace();
        }
    }


    private void displayCollectives(ArrayList<Collective> collectives){
        if (collectives.size()){
            System.out.println("No hay colectivos");
            PrincipalUser();
        }
        for (int i = 0; i < collectives.size(); i++){
            System.out.println("%d. %s", i+1, collectives.get(i).getName());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opc = Integer.parseInt(reader.readLine()) - 1;
            if (opc >= collectives.size() || opc < 0)
                displayCollectives(collectives);

            Collective c = collectives.get(opc);
            System.out.println(c);
            if (memberCollectives.contains(c)){
                System.out.println("Pertecences a este colectivo");
                System.out.println("Escribir 'b' para borrarse");
                if (reader.readLine() == "b")
                    c.disjoin(this);
            }
            else{
                System.out.println("Escribir 'u' para unirse");
                if (reader.readLine() == "u")
                    c.disjoin(this);
            }
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }catch(NumberFormatException exc){
            displayCollectives(collectives);
        }
        PantallaUser();
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