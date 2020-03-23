package across.user;

import java.util.ArrayList;


/**
 * Clase abstracta Collective que hereda de de UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class Collective implements Serializable { // add extedns UserCollective

    private String name;
    private String description;

    private ArrayList<Collective> children = new ArrayList<Collective>();
    private Collective parent;
    private User manager;
    private ArrayList<User> members = new ArrayList<>();


    /**
     * Constructor de un objeto Collective sin padre
     * 
     * @param name       nombre del colectivo
     * @param decription descripcion del colectivo
     */
    public Collective(String name, String description) {
        this.name = name;
        this.description = description;
        this.parent = null;        
    }


    /**
     * Constructor de un objeto Collective hijo
     * 
     * @param name       nombre del colectivo
     * @param decription descripcion del colectivo
     */
    public Collective(String name, String description, Collective parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        parent.addChild(this);
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
    // public User getManager(){ return manager; }

    /**
     * Devuelve un array con los usuarios pertenecientes al colectivo
     * 
     * @return members[]
     */
    // public User[] getMembers(){ return members; }



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

    /**
     * Actualiza el representante del colectivo
     * 
     * @param manager Usuario que crea el colectivo
     */
    // public void setManager(User manager){ this.manager = manager; }

    /**
     * Actualiza los miembrios del colectivo
     * 
     * @param members arrays de los usuarios miembros
     */
    // public void setMembers(User[] members){ this.members = members; }

    // METODOS

    private void addChild(Collective c) {
        if (c == null) return;
        children.add(c);
    }

    // /**
    // * Permite a un usuario unirse al colectivo
    // * @param u usuario a unirse
    // * @return true si el usuario se anade correctamente
    // * @return false si el usuario no puede unirse al colectivo
    // */
    // public boolean join(User u){
    // // checkar si usuario esta en colectivos padres o hijos
    // // return false

    // members.add(u);
    // // actualizar en user los colectivos a los que pertenece
    // return true;
    // }

    // /**
    // * Permite a un usuario borrarse del colectivo
    // * @param u usuario a borrarse
    // * @return true si el usuario es borrado correctamente
    // * @return false si el usuario no puede borrarse al colectivo
    // */
    // public boolean disjoin(User u){
    // // check u == manager: return false
    // if (this.checkMember(u)){
    // members.remove(u); //ask teacher pq no se si existe
    // }
    // return true;
    // }

    // /**
    // * Comprueba si un usuario pertenece al colectivo
    // * @param u usuario
    // * @return true si el usuario pertenece al colectivo
    // * @return false si el usuario no pertenece al colectivo
    // */
    // public boolean checkMember(User u){
    // for (User memb: members){
    // if (u.equals(memb))
    // return true;
    // }
    // return false;
    // }


    public String toString() {
        String resumen = "";
        resumen += "Nombre: "+ name;
        resumen += "\nDescipcion"+ description;
        if (padre){
            resumen += "\n  Padre:\n" + padre.toString();
        }
        resumen += "\n  Hijos:";
        for (Collective c: children) resumen += "\n" + c.toString();
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
