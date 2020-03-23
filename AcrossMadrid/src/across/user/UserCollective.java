package across.user;

import across.collective.*;
import java.util.*;


/**
 * Clase abstracta UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
public abstract class UserCollective implements Serializable {

    private ArrayList<Project> createdProjects = new ArrayList<Project>();
    private ArrayList<Project> votedProjects = new ArrayList<Project>();
   
    
    /**
     * Devuelve los proyectos creados por el objeto usuario o colectivo
     *
     * @return array con los proyectos creados
     */
    public ArrayList<Project> getCreatedProjects(){
        return this.createdProjects;
    }

    /**
     * Devuelve los proyectos votados por el objeto usuario o colectivo
     * 
     * @return array con los proyectos votados
     */
    public ArrayList<Project> getVotedProjects(){
        return this.votedProjects;
    }

    /**
     * ...
     * @return set de objetos UserCollective ...
     */
    public int makeSet(List<User> users, Collective c){
        Set<User> set = new TreeSet<>();

        for (User u:c.getMembers()) set.add(u);
        for (User u:users) set.add(u);
        return set.size();
    }


}
