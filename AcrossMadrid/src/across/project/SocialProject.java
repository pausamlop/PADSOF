package across.project;

import across.enumerations.*;
import across.notification.*;
import across.user.*;


/**
 * Clase SocialProject que hereda de Project
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
public class SocialProject extends Project {
    private String group;
    private typeSocial type;

    public SocialProject(String name, String dcp, double cost, String group, typeSocial type, UserCollective creator){
        super(name, dcp, cost, creator);
        this.group = group;
        this.type = type;
        new NotificationAdminProject(this);
    }

    public void setGroup(String group) { this.group = group; }
    public void settypeSocial(typeSocial type) { this.type = type; }

    public String getGroup() { return group; }
    public typeSocial gettypeSocial() { return type; }


    @Override
    public String toString(){
        String resumen = super.toString();
        resumen += "\n      PROYECTO TIPO SOCIAL";
        resumen += "\n      + Ambito: " + type.name();
        resumen += "\n      + Dirigido a: " + group;
        return resumen;
    }

}