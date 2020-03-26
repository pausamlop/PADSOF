package across.project;

import across.enumerations.*;
import java.util.*;

/**
 * Clase abstracta UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
public class SocialProject extends Project implements Serializable{
    private String group;
    private typeSocial type;

    public SocialProject(String name, String dcp, double cost, String group, typeSocial type){
        super(name, dcp, cost);
        this.group = group;
        this.type = type;
    }

    public void setGroup(String group) { this.group = group; }
    public void settypeSocial(typeSocial type) { this.type = type; }

    public String getGroup() { return group; }
    public typeSocial gettypeSocial() { return type; }


    @Override
    public String toString(){
        String resumen = super.toString();
        resumen += "\nPROYECTO TIPO SOCIAL";
        resumen += "\nAmbito: " + type.name();
        resumen += "\nDirigido a: " + group;
        return resumen;
    }

}