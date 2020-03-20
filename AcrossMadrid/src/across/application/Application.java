package across.application;

import across.collective.*;
import across.enumerations.*;
import across.project.*;
import across.user.*;
import java.util.*;
import java.lang.Integer;


/**
 * Clase Application
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/
public class Application {

    private int daysExpiration;
    private int minVotes;

    private Admin admin;
    private ArrayList<Project> projects;
    private ArrayList<Collective> collectives;
    private ArrayList<User> users;
    private ArrayList<User> nonValidatedUsers;
    

    /**
     * Constructor
     * @param daysExp numeros de dias sin votos para que caduque un proyecto
     * @param minV minimo numero de votos en un proyecto para poder financiar
     */
    public Application(int daysExp, int minV){
        daysExpiration = daysExp;
        minVotes = minV;
        projects = new ArrayList<Project>();
        collectives = new ArrayList<Collective>();
        users = new ArrayList<User>();
        nonValidatedUsers = new ArrayList<User>();
    }


    // GETTERS
    /**
     * Devuelve el numero de dias sin votos nuevos para que un proyecto caduque
     * @return daysExpiration
     */
    public int getDaysExpiration(){ return daysExpiration; }

    /**
     * Devuelve el minimo numero de votos en un proyecto para poder financiar
     * @return minVotes
     */
    public int getMinVotes(){ return minVotes; }


    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<User> getNonValidatedUsers() {
        return this.nonValidatedUsers;
    }


    
    // SETTERS
    /**
     * Actualiza el numero de dias sin votos nuevos para que un proyecto caduque
     * @param daysExp numero de dias
     */
    public void setDaysExpiration(int daysExp){ daysExpiration = daysExp; }

    /**
     * Actualiza el minimo numero de votos en un proyecto para poder financiar
     * @param minV numero de votos
     */
    public void setMinVotes(int minV){ minVotes = minV; }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setNonValidatedUsers(ArrayList<User> nonValidatedUsers) {
        this.nonValidatedUsers = nonValidatedUsers;
    }
   



    /**
     * Permite a un usuario registrarse en la aplicacion
     * @param username nombre de usuario
     * @param NIF NIF del usuario a registrar
     * @param password contrasena del usuario
     * @return true si ...
     * @return false si ...
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
     * @return el usuario
     */
    public User login(String username, String password){
        //public Boolean login(String username, String password){
        // for (User u: users){
        //     if (username.equals(u.getUsername()) && password.equals(u.getPassword()))
        //         return true;
        // }

        for (User u: users) {
            if (u.login(username, password)) return u;
        }

        return null;
    }

    
    
    // METODOS DE PROJECT Y COLECTIVOS

    /*
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

    /*
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


    /*
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


    /*
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


    // no se como arreglar este
    public ArrayList<Project> popularityReport(){


        ArrayList<Project> output = new ArrayList<Project>();
        for(Project p: projects) output.add(p);

        Collections.sort(output, new Comparator(){
            @Override
            public int compare(Project p1, Project p2) {
                //NO ENTIENDO PQ COMPARETO NO FUNCIONA
                int result = (p2.getVotes()).compareTo((p1.getVotes()));
                return result;
            }

        });

        return output;


    }



    public ArrayList<Collective> affinityReport(Collective c){

    }

    // DATA

    public boolean saveData(){
        return false;
    }

    public boolean loadData(){
        return false;
    }



   
}
