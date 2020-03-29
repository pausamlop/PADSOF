package across.user;

import java.io.*;
import java.util.*;

import across.application.Application;
import across.enumerations.*;
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
        String perfil = "";
        perfil += " - Nombre de usuario: " + username;
        perfil += "\n - NIF: " + NIF;
        perfil += "\n - Proyectos creados:";
        for (Project p: getCreatedProjects()) perfil += "\n    * " + p.toString();
        perfil += "\n\n - Proyectos votados:";
        for (Project p: getVotedProjects()) perfil += "\n    * " + p.toString();
        perfil += "\n\n - Proyectos seguidos:";
        for (Project p: followedProjects) perfil += "\n    * " + p.toString();
        perfil += "\n\n - Colectivos creados: ";
        for (Collective c: createdCollectives) perfil += "\n    * " + c.toString();
        perfil += "\n\n - Colectivos a los que pertenece: ";
        for (Collective c: memberCollectives) perfil += "\n    * " + c.toString();
        return perfil;

    }



    /************************************************************************/
    /************ FUNCIONES AUXILIARES PARA PROBAR FUNCIONAMIENTO ***********/
    /************************************************************************/

    /**
     * Metodo con el menu del usuario logueado
     */
    public void principalUser(){
        Application app = Application.getApplication();
        if (blocked){
                System.out.println("Ha sido bloqueado por el administrador");
                System.out.println(blockedMssg);
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    reader.readLine();
                }catch(IOException ex){
                    ex.printStackTrace();
                } 
                app.logout();
        } 
        else{
            if (notifications.size() != 0)
                displayNotifications();
            
            System.out.println("\n\n ------------------ AREA DE USUARIO -------------------");
            System.out.println("Ver mi perfil (yo)"); 
            System.out.println("Solicitar informe popularidad (ip)");
            System.out.println("Solicitar informe afinidad (ia)"); 
            System.out.println("Crear colectivo (c)");
            System.out.println("Buscar colectivos (bc)");
            System.out.println("Crear proyecto (p)");
            System.out.println("Buscar proyectos (bp)");
            System.out.println("Filtrar proyectos (fp)");
            System.out.println("Enviar proyecto a financiacion (ef)");

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String opc = reader.readLine();

                switch (opc) {
                    case "yo": //perfil
                        System.out.println(this);
                        reader.readLine(); //enter para continuar
                        break;

                    case "ip": // popularidad
                        System.out.println(popularityReport()); 
                        reader.readLine(); //enter para continuar
                        break;

                    case "ia": // informe afinidad
                        System.out.println(affinityReport()); 
                        reader.readLine(); //enter para continuar
                        break;

                    case "c": // crear colectivo
                        createCollective();
                        break;

                    case "bc": // buscar colectivo
                        System.out.println("Buscar colectivo: ");
                        displayCollectives(app.searchCollective(reader.readLine()));
                        break;

                    case "p": // crear proyecto
                        createProject();
                        break;

                    case "bp": // buscar proyecto
                        System.out.println("Escribe palabra a buscar: ");
                        displayProjects(app.searchProject(reader.readLine()));
                        break;

                    case "fp": // filtrar proyecto
                        filterProject();
                        break;

                    case "ef": //enviar a financiar
                        sendToFinance();
                        break;

                    case "q": //cerrar sesion
                        app.logout();
                    }
            }catch(IOException exception){
                exception.printStackTrace();
            }
        }
    }


    /**
     * Metodo para mostrar por pantallas las notificaciones por pantalla
     */
    private void displayNotifications(){
        System.out.println("\n ------------------- NOTIFICACIONES -------------------");
        
        for (Notification n: notifications){
            System.out.println(n.getMessage());
        }
        notifications.clear();
    }

    /**
     * Metodo para mostrar proyectos por pantalla 
     * 
     * @param projects proyectos a mostrar por pantalla
     */
    private void displayProjects(ArrayList<Project> projects){
        if (projects.size() == 0){
            System.out.println("No hay proyectos");
            return;
        }

        int cont = 1;
        for (Project p: projects){
            System.out.println(cont + ". " + p);
            cont++;
        }

        try{ 
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opcs = reader.readLine();
            if(opcs.equals("")) return;
            int opc = 0;
            try{
                opc = Integer.parseInt(opcs) - 1;
                if (opc >= projects.size() || opc < 0){
                    System.out.println("Seleccione numero de proyecto valido");
                    displayProjects(projects);
                }
            }catch(NumberFormatException exc){
                return;
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
                    break;
        
                case "vc": //votar como colectivo
                    if (createdCollectives.size() == 0){
                        System.out.println("No has creado ningun colectivo todavia");
                        return;
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
                    break;
        
                case "s": //seguir
                    p.follow(this);
                    break;
        
                case "ds": //dejar de seguir
                    p.unfollow(this);
                    break;
        
                default: //volver a principalUser
                    return;
        
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }   
    }

    /**
     * Metodo para mostrar colectivos por pantalla
     * 
     * @param collectives colectivos a mostrar por pantalla
     */
    private void displayCollectives(ArrayList<Collective> collectives){
        if (collectives.size() == 0){
            System.out.println("No hay colectivos");
            return;
        }

        int cont = 1;
        for(Collective c: collectives){
            System.out.println(cont + ". " + c.getName());
            cont++;
        }
    
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opcs = reader.readLine();
            if(opcs.equals("")) return;
            int opc = 0;
            try{
                opc = Integer.parseInt(opcs) - 1;
                if (opc >= collectives.size() || opc < 0){
                    System.out.println("Seleccione un numero de colectivo valido");
                    displayCollectives(collectives);
                }
            }catch(NumberFormatException exc){
                return;
            }

            Collective c = collectives.get(opc);
            System.out.println(c);
            if (memberCollectives.contains(c)){
                System.out.println("Pertecences a este colectivo");
                System.out.println("Escribir 'b' para borrarse");
                if (reader.readLine().equals("b"))
                    c.disjoin(this);
            }
            else{
                System.out.println("Escribir 'u' para unirse");
                if (reader.readLine().equals("u"))
                    c.join(this);
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

    /**
     * Devuelve el output de una solicitud de un informe de popularidad relizada por el usuario
     * 
     * @return cadena de caracteres con el output a mostrar por pantalla
     */
    private String popularityReport(){
        String output = Application.getApplication().popularityReport();
        if (output.length() == 0){
            return "No hay proyectos disponibles para generar un informe";
        }
        return output;
    }

    /**
     * Devuelve el output de una solicitud de un informe de afinidad relizada por el usuario
     * 
     * @return cadena de caracteres con el output a mostrar por pantalla
     */
    private String affinityReport(){
        String output = "";

        if (memberCollectives.size() == 0){
            System.out.println("Todavia no perteneces a ningun colectivo");
            return "";
        }

        System.out.println("Selecciones colectivo a analizar: ");
        
        int cont = 1;
        for(Collective c: memberCollectives){
            System.out.println(cont + ". " + c.getName());
            cont++;
        }
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opc = reader.readLine();
            if(opc.equals("")) return "";
            int num = 0;
            try{
                num = Integer.parseInt(opc) - 1;
                if (num >= memberCollectives.size() || num < 0){
                    System.out.println("Seleccione un numero de colectivo valido");
                    affinityReport();
                }
            }catch(NumberFormatException excep){
                return "";
            }

            output = Application.getApplication().affinityReport(memberCollectives.get(num));
        }catch(IOException exc){
            exc.printStackTrace();
        }
        return output;
    }

    /**
     * Metodo para crear un colectivo a partir de los parametros que va introduciendo el usuario logueado
     * por terminal
     */
    private void createCollective(){
        System.out.println("Nombre del colectivo, descripcion, padre:");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            String dcp = reader.readLine();

            System.out.println("Seleccione padre (o enter)");
            int cont = 1;
            for(Collective c: Application.getApplication().getCollectives()){
                System.out.println(cont + ". " + c.getName());
                cont++;
            }
            
            Collective parent;
            String parent_str = reader.readLine();
            if (parent_str.equals("")) parent = null;
            else {
                try{
                    int parent_int = Integer.parseInt(parent_str) - 1;
                    if (parent_int >= Application.getApplication().getCollectives().size() || parent_int < 0){
                        System.out.println("Seleccione un numero de colectivo valido");
                        createCollective();
                    }
                    parent = Application.getApplication().getCollectives().get(parent_int);
                }catch(NumberFormatException excep){
                    return;
                }
            }
            if (parent == null){
                //Application.getApplication().addCollectives(new Collective(name, dcp));
                new Collective(name, dcp);
            } else {
                //Application.getApplication().addCollectives(new Collective(name, dcp, parent));
                new Collective(name, dcp, parent);
            }
            
        }catch(IOException exc){
            exc.printStackTrace();
        }

    }

    /**
     * Metodo para crear un proyecto a partir de los parametros que va introduciendo el usuario
     * por terminal
     */
    private void createProject(){
        UserCollective creator;
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Crear proyecto como colectivo (c) o  como usuario (u)");
            String opc = reader.readLine();
            if (opc.equals("c")){
                System.out.println("Seleccione colectivo");
                int cont = 1;
                for(Collective c: getCreatedCollectives()){
                    System.out.println(cont + ". " + c.getName());
                    cont++;
                }

                String opc2 = reader.readLine();
                if(opc2.equals("")) return;
                int col = 0;
                try{
                    col = Integer.parseInt(opc2) - 1;
                    if (col >= getCreatedCollectives().size() || col < 0){
                        System.out.println("Seleccione un numero de colectivo valido");
                        createProject();
                    }
                }catch(NumberFormatException excep){
                    return;
                }
                creator = getCreatedCollectives().get(col);
            }else{
                creator = this;
            }
            

            System.out.println("Nombre del proyecto, descripcion, coste, tipo (S/I):");
            String name2 = reader.readLine();
            String dcp2 = reader.readLine();
            double cost;
            try{
                cost = Double.parseDouble(reader.readLine());
            }catch(NumberFormatException excep){
                return;
            }
            String tipo = reader.readLine();
            if (tipo.equals("I")){
                System.out.println("Imagen y distrito: ");
                String image = reader.readLine();
                String d = reader.readLine();
                if (!Application.getApplication().getDistricts().contains(d)){
                    System.out.println("Ese distrito no existe");
                    return;
                }
                Application.getApplication().addNewProject(new InfraestructureProject(name2, dcp2, cost, image,d, creator));
            }
            else if (tipo.equals("S")){
                System.out.println("Grupo social y ambito (N/I):");
                String group = reader.readLine();
                typeSocial type = (reader.readLine() == "N")?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                Application.getApplication().addNewProject(new SocialProject(name2, dcp2, cost, group, type, creator));
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

    /**
     * Metodo para filtrar una busqueda de proyectos
     */
    private void filterProject(){
    	Application app = Application.getApplication();
        System.out.println("Filtrar proyecto por estado (e), tipo social (ts) o infraestructura (ti)");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filtro = reader.readLine();
            if (filtro.equals("e")){
                System.out.println("Escriba estado a buscar: ACEPTADO(1), VOTOSALCANZADOS(3), ENVIADO(4), FINANCIADO(5), CADUCADO(7):");
                int state;
                try{
                    state = Integer.parseInt(reader.readLine());
                    if (state < 1 || state > 7 || state == 2 || state == 6){
                        System.out.println("Numero de estado no valido");
                        filterProject();
                    }
                }catch(NumberFormatException ex){
                    return;
                }
                displayProjects(app.filterProject(projectState.values()[state]));
            }
            else if (filtro.equals("ts")){
                System.out.println("Buscar por ambito (N/I), por grupo (escribir grupo) o nada (enter):");
                String opc2 = reader.readLine();
                if (opc2.equals("N"))
                    displayProjects(app.filterSocialProject(typeSocial.NACIONAL));
                else if(opc2.equals("I"))
                    displayProjects(app.filterSocialProject(typeSocial.INTERNACIONAL));
                else 
                    displayProjects(app.filterSocialProject(opc2));   
            }
            else if (filtro.equals("ti")){
                System.out.println("Esciba distrito a buscar o pulse enter ");
                displayProjects(app.filterInfrProject(reader.readLine()));
            }
            
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

    /**
     * Metodo para enviar un proyecto a financiacion
     */
    private void sendToFinance(){
        ArrayList<Project> toFinance = Application.getApplication().filterProject(projectState.VOTOSALCANZADOS);
        
        int cont = 1;
        for(Project p: toFinance){
            if (p.getCreator() instanceof User){
                if(((User)p.getCreator()).equals(this)){
                    System.out.println(cont + ". " + p.getName());
                    cont++;
                }
            }
            else{
                if (((Collective)p.getCreator()).getManager().equals(this)){
                    System.out.println(cont + ". " + p.getName());
                    cont++;
                }
            }
        }

        System.out.println("Selecciona proyecto para enviar a financiar");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opc = reader.readLine();
            Project p;
            if (opc.equals("")) return;
            try{
                int num = Integer.parseInt(opc) - 1;
                if (num >= toFinance.size() || num < 0){
                    System.out.println("Seleccione un numero de proyecto valido");
                    createCollective();
                }
                p = toFinance.get(num);
                p.sendToFinance();
            }catch(NumberFormatException excep){
                return;
            }
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

}
