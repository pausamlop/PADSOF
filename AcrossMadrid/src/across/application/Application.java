package across.application;

import across.collective.*;
import across.enumerations.*;
import across.project.*;
import across.user.*;
import jdk.internal.jline.internal.InputStreamReader;

import java.util.*;
import java.io.*;
import java.lang.Integer;


/**
 * Clase Application
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/
public class Application implements Serializable, Comparable<Project>{

    private static Application application;
    private User currentUser = null;
    private boolean currentAdmin = false;

    private int daysExpiration = 30;
    private static int minVotes = 1000; /* hay que preguntar al profe */

    private Admin admin;
    private ArrayList<Project> projects;
    private ArrayList<Project> nonValidatedProjects;
    private ArrayList<Collective> collectives;
    private ArrayList<User> users;
    private ArrayList<User> nonValidatedUsers;


    /**
     * Constructor
    * @param daysExp numeros de dias sin votos para que caduque un proyecto
    * @param minV minimo numero de votos en un proyecto para poder financiar
    */
    public Application(){
        admin = new Admin();
        projects = new ArrayList<Project>();
        nonValidatedProjects = new ArrayList<Project>();
        collectives = new ArrayList<Collective>();
        users = new ArrayList<User>();
        nonValidatedUsers = new ArrayList<User>();
    }


    /**
     * Crea un nuevo objeto Application ni no ha sido creado con anterioridad o,
     * sino, devuelve la aplicacion ya creada
     * 
     * @return objeto aplicacion unico
     */
    public static Application getApplication(){
        if (application == null){
            application = new Application();
        }
        return application;
    }

    /**
     * Actualiza el objeto aplicacion al parametro de entrada
     * 
     * @param app aplicacion
     */
    public static void setApplication(Applicacion app){
        application = app;
    }

    /**
     * Devuelve el usuario logueado en la aplicacion en el momento en el que se llama a esta funcion
     * 
     * @return usuario conectado actualmente
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Actualiza el usuario logueado en la aplicacion
     * 
     * @param current usuario conectado
     */
    public void setCurrentUser(User current){
        currentUser = user;
    }

    /**
     * Devuelve si el usuario logueado es el administrador o no
     * 
     * @return true, si el administrador esta conectado
     * @return false, en otro caso 
     */
    public boolean getCurrentAdmin(){
        return currentAdmin;
    }

    /**
     * Modifica si el administrador esta logueado o no
     * 
     * @param is_admin administrador conectado o no
     */
    public void setCurrentAdmin(boolean is_admin){
        currentAdmin = is_admin;
    }

    /**
     * Devuelve el numero de dias sin votos nuevos para que un proyecto caduque
     * @return daysExpiration
     */
    public int getDaysExpiration(){ 
        return daysExpiration; 
    }

    /**
     * Actualiza el numero de dias sin votos nuevos para que un proyecto caduque
     * @param daysExp numero de dias
     */
    public void setDaysExpiration(int daysExp){
        daysExpiration = daysExp;
    }

    /**
     * Devuelve el minimo numero de votos en un proyecto para poder financiar
     * @return minVotes
     */
    public int getMinVotes(){ 
        return minVotes; 
    }

    /**
     * Actualiza el minimo numero de votos en un proyecto para poder financiar
     * @param minV numero de votos
     */
    public void setMinVotes(int minV){ 
        minVotes = minV; 
    }


    public ArrayList<User> getUsers() {
        return this.users;
    }
    

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


    public ArrayList<User> getNonValidatedUsers() {
        return this.nonValidatedUsers;
    }


    public void setNonValidatedUsers(ArrayList<User> nonValidatedUsers) {
        this.nonValidatedUsers = nonValidatedUsers;
    }


    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Project> getNonValidatedProjects() {
        return this.nonValidatedProjects;
    }

    public void setNonValidatedProjects(ArrayList<Project> nonValidatedProjects) {
        this.nonValidatedProjects = nonValidatedProjects;
    }
    

    /**
     * Pantalla de inicio de la aplicacion.
     * Da opcion de registrarse como nuevo usario o iniciar sesion como usuario o administrador
     * 
     * @return true, si la accion se ejecuto con exito
     * @return false, si no se ha podido realizar la accion
     */
    public boolean pantallaLogin(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1. Registro\n2. Login\n3. Login como administrador");
            String opc = reader.readLine();
            if (opc == "1") {
                System.out.println("Nombre de usuario:");
                String username = reader.readLine();
                System.out.println("NIF:");
                String NIF = reader.readLine();
                System.out.println("Contrasena:");
                String password = reader.readLine();
                return register(username, NIF, password);
            }
            else if(opc == "2") {
                System.out.println("Nombre de usuario:");
                String username = reader.readLine();
                System.out.println("Contrasena:");
                String password = reader.readLine();
                return login(username, password);
            }
            else {
                System.out.println("Contrasena de administrador:");
                String password = reader.readLine();
                if (admin.login(password)){
                    currentAdmin = true;
                    return true;
                }
                return false;
            }
        }catch(IOException exception){
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Permite a un usuario registrarse en la aplicacion
     * @param username nombre de usuario
     * @param NIF NIF del usuario a registrar
     * @param password contrasena del usuario
     * @return true, si ...
     * @return false, si ...
     */
    public boolean register(String username, String NIF, String password){
        //comprobar que el username no existe
        for (User u: users) {
            if (username.equals(u.getUsername())) return false;
        }
        User u = new User(username, NIF, password, false);

        // mandar al admin a validar
        nonValidatedUsers.add(u);
        return true;
    }

    /**
     * Permite a un usuario loguearse en la aplicacion
     * @param username nombre de usuario
     * @param password contrasena del usuario
     * @return true, si la contrasena coincide con la del nombre de usuario
     * @return false, en otro caso
     */
    public boolean login(String username, String password){
        for (User u: users){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())){
                activeUser = u;
                return true;
            }
        }
        return false;
    }

    
    public void pantallaPrincipal(){
        if (currentUser)
            currentUser.PrincipalUser();
        else
            Admin.PrincipalAdmin();
    }

    


    
    // METODOS DE PROJECT Y COLECTIVOS

    /**
     * Filtra un proyecto social por el tipo y el grupo
     * 
     * @param type tipo de proyecto social
     * @param group Grupo social al que va dirigido
     * @return ArrayList de proyectos qye cumplen las condiciones
     */
    public ArrayList<Project> filterSocialProject(typeSocial type, String group){
        ArrayList<Project> output = new ArrayList<Project>();

        for (Project p: projects){
            if (p.getClass().equals(SocialProject.class)){
                if (p.getGroup().equals(group) && p.gettypeSocial().equals(type)) output.add(p);
            }
        }

        return output;
    }

    /**
     * Filtra un proyecto de infraestructira por distrito y su estado
     * 
     * @param d distrito
     * @param state estado del proyecto
     * @return ArrayList de proyectos qye cumplen las condiciones
     */
    public ArrayList<Project> filterInfrProject(nameDistrict d, projectState state){
        ArrayList<Project> output = new ArrayList<Project>();

        for (Project p: projects){
            if (p.getClass().equals(InfraestructureProject.class)){ 
                if (p.getDistrict().equals(d) && p.getProjectState().equals(state)) output.add(p);
            }
        }

        // NO SE SI LO DE DISTRICT ESTA BIEN PQ NOS LO HA PUESTO MAL EN EL DIAGRAMA

        return output;

    }

    /**
     * Busca un proyecto segun si el input esta contenido en el nombre
     * o en la descripcion del proyecti
     * 
     * @param subj
     * @return ArrayList de proyectos qye cumplen las condiciones
     */
    public ArrayList<Project> searchProject(String subj){
        ArrayList<Project> output = new ArrayList<Project>();
        for (Project p: projects){
            if (p.getName().contains(subj) || p.getDescription().contains(str)) output.add(p);
        }
        
        return output;
    }

    /**
     * Busca un colectivo segun si el input esta contenido en el nombre
     * o en la descripcion del proyecti
     * 
     * @param subj
     * @return ArrayList de colectivos qye cumplen las condiciones
     */
    public ArrayList<Collective> searchCollective(String subj){
        ArrayList<Collective> output = new ArrayList<Collective>();
        for (Collective c: collectives){
            if (p.getName().contains(subj) || p.getDescription().contains(str)) output.add(p);
        }
        
        return output;
    }



   /**
     * Sobreescribe el metodo compareTo (proyectos)
     * 
     * @param p
     * @return entero dependiendo de si es mayor menor o igual
     */
    @Override
    public int compareTo(Project p){
        if (this.getVotes() > p.getVotes()) return -1;
        else if(this.getVotes() < p.getVotes()) return 1;
        else return 0;
    }


    /**
     * Ordena los proyectos en base a su numero de votos
     * 
     * @return ArrayList proyectos
     */
    public ArrayList<Project> popularityReport(){

        ArrayList<Project> output = new ArrayList<Project>();
        output.addAll(projects);
        Array.sort(output);

        return output;
    }


    // /**
    //  * Sobreescribe el metodo compareTo (colectivos)
    //  * 
    //  * @param c
    //  * @return entero dependiendo de si es mayor menor o igual
    //  */
    // @Override
    // public int compareTo(Collective c){
    //     a = 
    //     if (this.getVotes() > p.getVotes()) return -1;
    //     else if(this.getVotes() < p.getVotes()) return 1;
    //     else return 0;
    // }

    // /**
    //  * Devuelve un reporte de afinidad de un colectivo
    //  * 
    //  * @param c colectivo sobre el que se hace el reporte
    //  * @return ArrayList de los colectivos ordenados segun afinidad
    //  */
    // public ArrayList<Collective> affinityReport(Collective c){
    //     if (currentUser.getMemberCollectives().contains(c) == false){
    //         return null;
    //     }
    //     ArrayList<Collectives> output = new ArrayList<Collectives>();
    //     output.addAll(collectives);
    //     Array.sort(output);

    //     return output;
    // }

}
