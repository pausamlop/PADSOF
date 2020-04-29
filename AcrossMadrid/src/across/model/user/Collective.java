package across.model.user;

import across.model.application.*;
import across.model.project.*;

import java.util.*;

/**
 * Clase abstracta Collective que hereda de de UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class Collective extends UserCollective { 

    private String name;
    private String description;

    private ArrayList<Collective> children = new ArrayList<Collective>();
    private Collective parent;
    private final User manager; 
    private ArrayList<User> members = new ArrayList<>();


    /**
     * Constructor de un objeto Collective sin padre
     * 
     * @param name       nombre del colectivo
     * @param decription descripcion del colectivo
     */
    public Collective(String name, String description) {
        this.manager = Application.getApplication().getCurrentUser();
        this.name = name;
        this.description = description;
        this.parent = null;    
        
        // array de colectivos de la aplicacion
        Application.getApplication().addCollectives(this);

        // array creados del manager
        ArrayList<Collective> c1 = new ArrayList<Collective>();
        c1.addAll(manager.getCreatedCollectives());
        c1.add(this);
        manager.setCreatedCollectives(c1);

        // array de los colectivos a los que pertenece el manager
        ArrayList<Collective> c2 = new ArrayList<Collective>();
        c2.addAll(manager.getMemberCollectives());
        c2.add(this);
        manager.setMemberCollectives(c2);


        // unir al manager
        members.add(manager);
    }

    /**
     * Constructor de un objeto Collective hijo
     * 
     * @param name       nombre del colectivo
     * @param decription descripcion del colectivo
     */
    public Collective(String name, String description, Collective parent) {
        this.manager = Application.getApplication().getCurrentUser();
        this.name = name;
        this.description = description;
        this.parent = parent;
        parent.addChild(this);

        // array de colectivos de la aplicacion
        Application.getApplication().addCollectives(this);

        // array creados del manager
        ArrayList<Collective> c1 = new ArrayList<Collective>();
        c1.addAll(manager.getCreatedCollectives());
        c1.add(this);
        manager.setCreatedCollectives(c1);

        // array de los colectivos a los que pertenece el manager
        ArrayList<Collective> c2 = new ArrayList<Collective>();
        c2.addAll(manager.getMemberCollectives());
        c2.add(this);
        manager.setMemberCollectives(c2);

        // unir al manager
        members.add(manager);
    }


    /**
     * Devuelve el nombre del colectivo
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la descipcion del colectivo
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }
    
    
    /**
     * Devuelve el padre
     * 
     * @return description
     */
	public Collective getParent() {

		return parent;
	}


    /**
     * Devuelve un ArrayList de los colectivos hijos
     * 
     * @return hijos 
     */
    public ArrayList<Collective> getChildren() {
        return this.children;
    }

    /**
     * Devuelve el usuario representante del colectivo
     * 
     * @return manager
     */
    public User getManager(){ return manager; }
    
    /**		
      * Devuelve un ArrayList de los usuarios miembros del colectivo		
      * 		
      * @return miembros del colectivo		
      */		
     public ArrayList<User> getMembers(){		
         return this.members;		
     }

    /**
     * Anade a un objeto colectivo un hijo, el pasado como argumento
     * 
     * @param c colectivo hijo
     */
    public void addChild(Collective c) {
        if (c == null) return;
        children.add(c);
    }

    /**
     * Crea una lista con los usuarios que pertenecen tanto al colectivo desde el que
     * se llama a este metodo como a cada uno de sus hijos
     * 
     * @return ArrayList con todos los miembros del colectivo (directos e indirectos)
     */
    public Set<User> getChildrenMembers(){
        Set<User> all = new HashSet<>();
        all.addAll(members);

        for(Collective auxCol : this.getChildren()){
            all.addAll(auxCol.members);
            all.addAll(auxCol.getChildrenMembers());
        }

        return all; 
    }

    /**
     * Crea una lista con los ususarios que pertenecen al colectivo desde el que se llama
     * al metodo, a los colectivos hijos y a los colectivos padres (y padres de padres)
     * 
     * @return ArrayList con todos los miembros del colectivo, de sus hijos y padres
     */
    public Set<User> getAllFamilyMembers(){
        Set<User> all = getChildrenMembers();
        Collective par = parent;

        while (par != null){
            all.addAll(par.members);
            par = par.parent;
        }

        return all;
    }

    /**
     * Actualiza los los votos de los proyectos a los que apoya un colectivo
     * y sus padres (y padres de padres)
     * 
     */
    public void updateFamilyVotes() {
        Collective par = parent;

        // bucle actualizando votos de proyectos del colectivo
        for (Project p: getVotedProjects()) p.updateVotes();

        // Update de los proyectos apoyados por padres, abuelos, ...
        while (par != null){
            for (Project p: par.getVotedProjects()) p.updateVotes();
            par = par.parent;
        }

    }

    /**
     * Permite a un usuario unirse al colectivo
     *
     * @param u usuario a unirse
     */
    public void join(User u){
        
        // checkear si usuario esta en colectivos padres o hijos
        if (getAllFamilyMembers().contains(u)) {
            System.out.println("No se puede unir a este colectivo");
            return;
        }

        // añadirlo al array de members de collective
        members.add(u);

        // array de los colectivos a los que pertenece el manager
        ArrayList<Collective> c2 = new ArrayList<Collective>();
        c2.addAll(u.getMemberCollectives());
        c2.add(this);
        u.setMemberCollectives(c2);

        // actualizar votos
        updateFamilyVotes();

    }

    /**
     * Permite a un usuario borrarse del colectivo
     * 
     * @param u usuario a borrarse
     */
    public void disjoin(User u){
        if (members.contains(u) == false) return;

        //comprobar que el que quiere salir no sea el manager
        if (u.equals(manager)) return;

        //quitar de array de members de collective
        members.remove(u);

        //quitarlo del array de colectivos de u
        ArrayList<Collective> col = new ArrayList<Collective>();
        col.addAll(u.getMemberCollectives());
        col.remove(this);
        u.setMemberCollectives(col);

        // actualizar votos
        updateFamilyVotes();
    }
    
    /**
     * Crea una cadena de texto con la informacion mas relevante de un colectivo
     * 
     * @return String con la informacion del objeto Collective
     */
    public String toString() {
        // String resumen = "";
        // resumen += "Nombre: "+ name;
        // resumen += ", descipcion: "+ description;
        // if (parent != null){
        //     resumen += ", padre: " + parent.getName();
        // }
        // if (children.size() > 0){
        //     resumen += ", hijos: ";
        //     for (Collective c: children) resumen += c.getName() + " ";
        // }

        // return resumen;
        return name;
    }

}
