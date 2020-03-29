package across.user;

import across.application.*;
import across.project.*;

import java.util.ArrayList;

/**
 * Clase abstracta Collective que hereda de de UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

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



    // GETTERS
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
     * Devuelve un ArrayList de los colectivos hijos
     * 
     * @return hijos 
     */
    public ArrayList<Collective> getChildren() {
        return this.children;
    }

    /**
     * Devuelve el colectivo padre
     * 
     * @return padre 
     */
    public Collective getParent() {
        return this.parent;
    }

    /**
     * Devuelve el usuario representante del colectivo
     * 
     * @return manager
     */
    public User getManager(){ return manager; }

    /**
     * Devuelve un array con los usuarios pertenecientes al colectivo
     * 
     * @return members[]
     */
    public ArrayList<User> getMembers(){ return members; }



    // SETTERS
    /**
     * Actualiza el nombre del colectivo
     * 
     * @param name nombre del colectivo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Actualiza la decripcion del colectivo
     * 
     * @param description descripcion del colectivo
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Actualiza el array de hijos del colectivo
     * 
     * @param children ArrayList de colectivos hijos
     */ 
    public void children(ArrayList<Collective> children) {
        this.children = children;
    }

    /**
     * Actualiza el colectivo padre
     * 
     * @param parent colectivo padre
     */
    public void parent(Collective parent) {
        this.parent = parent;
    }

    // /**
    //  * Actualiza el representante del colectivo
    //  * 
    //  * @param manager Usuario que crea el colectivo
    //  */
    // public void setManager(User manager){ this.manager = manager; }

    /**
     * Actualiza los miembrios del colectivo
     * 
     * @param members arrays de los usuarios miembros
     */
    public void setMembers(ArrayList<User> members){ this.members = members; }

    // METODOS

    private void addChild(Collective c) {
        if (c == null) return;
        children.add(c);
    }

    public ArrayList<User> getChildrenMembers(){
        ArrayList<User> all = new ArrayList<>();
        all.addAll(members);

        for(Collective auxCol : this.getChildren()){
            all.addAll(auxCol.members);
            all.addAll(auxCol.getChildrenMembers());
        }

        return all; 
    }

    public ArrayList<User> getAllFamilyMembers(){
        ArrayList<User> all = getChildrenMembers();
        Collective par = parent;

        while (par != null){
            all.addAll(par.members);
            par = par.parent;
        }

        return all;
    }


    
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
    * Comprueba si un usuario pertenece al colectivo
    * @param u usuario
    * @return true si el usuario pertenece al colectivo
    * @return false si el usuario no pertenece al colectivo
    */
    public boolean checkMember(User u){
    for (User memb: members){
    if (u.equals(memb))
    return true;
    }
    return false;
    }

    


    public String toString() {
        String resumen = "";
        resumen += "Nombre: "+ name;
        resumen += ", descipcion: "+ description;
        if (parent != null){
            resumen += ", padre: " + parent.getName();
        }
        if (children.size() > 0){
            resumen += ", hijos: ";
            for (Collective c: children) resumen += c.getName() + " ";
        }

        return resumen;
    }

    

    // para probar si funcionaba lo de padres e hijos
    public static void main(String[] args) {
        Collective a = new Collective("Padre", "soy el papi", null);
        Collective b = new Collective("Hijo 1", "holiiiii", a);
        Collective c = new Collective("Hijo 2", "pppppppp", a);

        System.out.println(b);
        System.out.println(c);
        System.out.println(a);
        System.out.println("\n   CHILDREN");
        for (Collective col: a.getChildren()){
            System.out.println(col);
        }
        System.out.println("\n   PARENT");
        System.out.println(b.parent);
    }

}
