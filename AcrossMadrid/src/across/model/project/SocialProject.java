package across.model.project;

import across.model.enumerations.*;
import across.model.notification.*;
import across.model.user.*;


/**
 * Clase SocialProject que hereda de Project
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class SocialProject extends Project {
    private String group;
    private typeSocial type;

    
    /**
     * Contructor de un proyecto social
     * 
     * @param name nombre del proyecto
     * @param dcp descripcion del proyecto
     * @param cost coste aproximado del proyecto
     * @param group grupo al que ira destinado el proyecto
     * @param type alcance del proyecto (Nacional, Internacional)
     * @param creator creador del proyecto
     */
    public SocialProject(String name, String dcp, double cost, String group, typeSocial type, UserCollective creator){
        super(name, dcp, cost, creator);
        this.group = group;
        this.type = type;
        new NotificationAdminProject(this);
    }

    /**
     * Establece el grupo al que pertenece el proyecto
     * 
     * @param group cadena de caracteres
     */
    public void setGroup(String group) { this.group = group; }

    /**
     * Establece el tipo del proyecto en cuestion (Nacional, Internacional)
     * 
     * @param type typeSocial
     */
    public void settypeSocial(typeSocial type) { this.type = type; }

    /**
     * Devuelve el grupo asociado al proyecto
     * 
     * @return grupo al que afecta el proyecto
     */
    public String getGroup() { return group; }

    /**
     * Devuelve el alcance del proyecto (Nacional, Internacional)
     * 
     * @return typeSocial del proyecto
     */
    public typeSocial gettypeSocial() { return type; }


    @Override
    public String toString(){
        return super.toString();
    }

}