package across.user;

import java.io.*;
import java.util.*;

import across.application.Application;
import across.enumerations.*;
import across.user.*;
import across.project.*;
import across.notification.*;

/**
 * Clase User que hereda de UserCollective
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
    public String getBlockedMssg() { return this.blockedMssg; }
    public ArrayList<Collective> getCreatedCollectives() { return this.createdCollectives;}
    public ArrayList<Collective> getMemberCollectives() { return this.memberCollectives;}
    public ArrayList<Notification> getNotifications() { return this.notifications; }
    public ArrayList<Project> getFollowedProjects() { return this.followedProjects; }


    public void setUsername(String username) { this.username = username; }
    public void setNIF(String NIF) { this.NIF = NIF; }
    public void setPassword(String password) { this.password = password; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
    public void setBlockedMssg(String blockedMssg) { this.blockedMssg = blockedMssg; }
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
     */
    public void reject(){
        ArrayList<User> u1 = new ArrayList<User>();
        u1.addAll(Application.getApplication().getNonValidatedUsers());
        u1.remove(this);

        Application.getApplication().setNonValidatedUsers(u1);
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
     * Nos permite acceder al mensaje asociado a la notificacion
     * 
     * @param notification
     * @return mensaje de la notificacion
     */
    public String getNotificationMessage(Notification notification){
        return notification.getMessage();
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
     * Desbloquea a un usuario
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
        String perfil = "";
        perfil += "Nombre de usuario: " + username;
        perfil += "\nNIF: " + NIF;
        perfil += "\nProyectos creados:";
        for (Project p: getCreatedProjects()) perfil += "\n" + p.toString();
        perfil += "\n\nProyectos votados:";
        for (Project p: getVotedProjects()) perfil += "\n" + p.toString();
        perfil += "\n\nProyectos seguidos:";
        for (Project p: followedProjects) perfil += "\n" + p.toString();
        perfil += "\n\nColectivos creados: ";
        for (Collective c: createdCollectives) perfil += "\n" + c.toString();
        perfil += "\n\nColectivos a los que pertenece: ";
        for (Collective c: memberCollectives) perfil += "\n" + c.toString();
        return perfil;

    }



    /************************************************************************/
    /************ FUNCIONES AUXILIARES PARA PROBAR FUNCIONAMIENTO ***********/
    /************************************************************************/

    public void principalUser(){
        Application app = Application.getApplication();
        if (blocked){
                System.out.println("Ha sido bloqueado por el administrador");
                System.out.println(blockedMssg);
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    reader.readLine();
                    reader.close();
                }catch(IOException ex){
                    ex.printStackTrace();
                } 
                app.logout();
        } 
        else{
            displayNotifications();
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
                        affinityReport();

                    case "c": // crear colectivo
                        createCollective();

                    case "bc": // buscar colectivo
                        System.out.println("Buscar colectivo: ");
                        displayCollectives(app.searchCollective(reader.readLine()));

                    case "p": // crear proyecto
                        createProject();

                    case "bp": // buscar proyecto
                        System.out.println("Buscar proyecto: ");
                        displayProjects(app.searchProject(reader.readLine()));

                    case "fp": // filtrar proyecto
                        filterProject();

                    case "q": //cerrar sesion
                        reader.close();
                        app.logout();
                    }
                reader.readLine(); // dar a enter para salir
                reader.close();
            }catch(IOException exception){
                exception.printStackTrace();
            }
            principalUser();
        }
    }


    private void displayNotifications(){
        if (notifications.size() == 0) 
            return;
        
        System.out.println(" ------------- NOTIFICACIONES -------------");
        for (Notification n: notifications){
            System.out.println(n.getMessage());
        }
        notifications.clear();
    }

    private void displayProjects(ArrayList<Project> projects){
        if (projects.size() == 0){
            System.out.println("No hay proyectos");
            principalUser();
        }

        int cont = 1;
        for (Project p: projects){
            System.out.println(cont + ". " + p);
            cont++;
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opc = 0;
            try{
                opc = Integer.parseInt(reader.readLine());
                if (opc >= projects.size() || opc < 0)
                    displayProjects(projects);
            }catch(NumberFormatException exc){
                displayProjects(projects);
            }

            Project p = projects.get(opc);
            System.out.println(p);
            if (getVotedProjects().contains(p))
                System.out.println("Has votado este proyecto");
            if (followedProjects.contains(p))
                System.out.println("Sigues a este proyecto");
            
            System.out.println("\nElige una opcion: votar (v), votar como colectivo(vc), seguir (s) o dejar de seguir (d)");
            
            String opc2 = reader.readLine();
        
            switch (opc2) {
                case "v": //vote
                    p.vote(this);
        
                case "vc": //votar como colectivo
                    if (createdCollectives.size() == 0){
                        System.out.println("No has creado ningun colectivo todavia");
                        displayProjects(projects);
                    }

                    cont = 1;
                    for (Collective c: createdCollectives){
                        System.out.println(cont + ". " + c);
                        cont++;
                    }
                    
                    int num_col = 0;
                    try{
                        num_col = Integer.parseInt(reader.readLine()) - 1;
                    }catch(NumberFormatException ex){
                        num_col = 0;
                    }finally{
                        p.vote(createdCollectives.get(num_col));
                    }
        
                case "s": //seguir
                    p.follow(this);
        
                case "ds": //dejar de seguir
                    p.unfollow(this);
        
                case "q": //cerrar sesion
                    Application.getApplication().logout();
        
                default: // otro
                    displayProjects(projects);
            }
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }
        
    }


    private void displayCollectives(ArrayList<Collective> collectives){
        if (collectives.size() == 0){
            System.out.println("No hay colectivos");
            principalUser();
        }
        for (int i = 0; i < collectives.size(); i++){
            int j = i + 1 ;
            System.out.println("%d. %s" + j + collectives.get(i).getName());
        }
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opc = 0;
            try{
                opc = Integer.parseInt(reader.readLine()) - 1;
                if (opc >= collectives.size() || opc < 0)
                    displayCollectives(collectives);
            }catch(NumberFormatException exc){
                reader.close();
                return;
            }

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
        }
    }

    private void affinityReport(){
        if (memberCollectives.size() == 0){
            System.out.println("Todavia no perteneces a ningun colectivo");
            return;
        }

        System.out.println("Selecciones colectivo a anlizar: ");
        
        for (int i = 0; i < memberCollectives.size(); i++){
            int j = i + 1 ;
            System.out.println("%d. %s" + j + memberCollectives.get(i).getName());
        }
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
            int num = 0;
            try{
                num = Integer.parseInt(reader.readLine()) - 1;
                if (num >= memberCollectives.size() || num < 0){
                    reader.close();
                    affinityReport();
                }
            }catch(NumberFormatException excep){
                reader.close();
                return;
            }

            System.out.println(Application.getApplication().affinityReport(memberCollectives.get(num)));
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }


    private void createCollective(){
        System.out.println("Nombre del colectivo, descripcion, padre:");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            String dcp = reader.readLine();

            for (int i = 0; i < Application.getApplication().getCollectives().size(); i++){ 
                int j = i + 1;
                System.out.println("%d. %s" + j + Application.getApplication().getCollectives().get(i).getName());
            }
            String parent_str = reader.readLine();
            reader.close();

            Collective parent;
            if (parent_str == "") parent = null;
            else {
                try{
                    int parent_int = Integer.parseInt(parent_str) - 1;
                    if (parent_int >= Application.getApplication().getCollectives().size() || parent_int < 0){
                        reader.close();
                        createCollective();
                    }
                    parent = Application.getApplication().getCollectives().get(parent_int);
                }catch(NumberFormatException excep){
                    reader.close();
                    return;
                }
            }
            Application.getApplication().addCollectives(new Collective(name, dcp, parent));
        }catch(IOException exc){
            exc.printStackTrace();
        }

    }


    private void createProject(){
        System.out.println("Nombre del proyecto, descripcion, coste, tipo (S/I):");
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name2 = reader.readLine();
            String dcp2 = reader.readLine();
            double cost;
            try{
                cost = Double.parseDouble(reader.readLine());
            }catch(NumberFormatException excep){
                reader.close();
                return;
            }
            String tipo = reader.readLine();
            if (tipo == "I"){
                System.out.println("Imagen y distrito: ");
                String image = reader.readLine();
                String d = reader.readLine();
                Application.getApplication().addNewProject(new InfraestructureProject(name2, dcp2, cost, image,d));
            }
            else if (tipo == "S"){
                System.out.println("Grupo social y ambito (N/I):");
                String group = reader.readLine();
                typeSocial type = (reader.readLine() == "N")?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                Application.getApplication().addNewProject(new SocialProject(name2, dcp2, cost, group, type));
            }
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }


    private void filterProject(){
    	Application app = Application.getApplication();
        System.out.println("Filtrar proyecto por estado (e), tipo social (ts) o infraestructura (ti)");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filtro = reader.readLine();
            if (filtro == "e"){
                System.out.println("Escriba estado a buscar: ACEPTADO(1), VOTOSALCANZADOS(3), ENVIADO(4), FINANCIADO(5), CADUCADO(7):");
                int state;
                try{
                    state = Integer.parseInt(reader.readLine());
                }catch(NumberFormatException ex){
                    reader.close();
                    return;
                }
                displayProjects(app.filterProject(projectState.values()[state]));
            }
            else if (filtro == "ts"){
                System.out.println("Buscar por ambito (N/I), por grupo (escribir grupo) o nada (enter):");
                String opc2 = reader.readLine();
                if (opc2 == "N")
                    displayProjects(app.filterSocialProject(typeSocial.NACIONAL));
                else if(opc2 == "I")
                    displayProjects(app.filterSocialProject(typeSocial.INTERNACIONAL));
                else 
                    displayProjects(app.filterSocialProject(opc2));   
            }
            else if (filtro == "ti"){
                System.out.println("Esciba distrito a buscar o pulse enter ");
                displayProjects(app.filterSocialProject(reader.readLine()));
            }
            else{
                reader.close();
                principalUser();
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

    

}