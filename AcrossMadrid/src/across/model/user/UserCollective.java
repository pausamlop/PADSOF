package across.model.user;

import across.model.project.*;
import java.util.*;
import java.io.*;


/**
 * Clase abstracta UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
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
     * Actualiza los proyectos creados por el objeto usuario o colectivo
     * 
     * @param createdProjects proyectos creados a establecer como atributo
     */
    public void setCreatedProjects(ArrayList<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

    /**
     * Actualiza los proyectos votados por el objeto usuario o colectivo
     * 
     * @param votedProjects proyectos votados a establecer como atributo
     */
    public void setVotedProjects(ArrayList<Project> votedProjects) {
        this.votedProjects = votedProjects;
    }
}
