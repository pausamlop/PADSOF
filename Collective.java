/**
 * ...
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/


// package ;


/**
 * Clase abstracta Collective que hereda de de UserCollective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/
public class Collective extends UserCollective {

    private String name;
    private String description;

    private Collective children[];
    private Collective parent;
    // private User manager;
    // private User members[];

    /**
     * Constructor
     * @param name nombre del colectivo
     * @param decription descripcion del colectivo
     */
    public Collective(String name, String description, Collective parent){
        this.name = name;
        this.description = description;
        this.children = null;
        this.parent = parent;
        if (parent != null){
            parent.addChild(this);
        }
    }

    // GETTERS
    /**
     * Devuelve el nombre del colectivo
     * @return name
     */
    public String getName(){ return name; }

    /**
     * Devuelve la descipcion del colectivo
     * @return description
     */
    public String getDescription(){ return description; }

    /**
     * Devuelve el usuario representante del colectivo
     * @return manager
     */
    // public User getManager(){ return manager; }

    /**
     * Devuelve un array con los usuarios pertenecientes al colectivo
     * @return members[]
     */
    // public User[] getMembers(){ return members; }


    // SETTERS
    /**
     * Actualiza el nombre del colectivo
     * @param name nombre del colectivo
     */
    public void setName(String name){ this.name = name; }

    /**
     * Actualiza la decripcion del colectivo
     * @param description descripcion del colectivo
     */
    public void setDescription(String description){ this.description = description; }

    /**
     * Actualiza el representante del colectivo
     * @param manager Usuario que crea el colectivo
     */
    // public void setManager(User manager){ this.manager = manager; }

    /**
     * Actualiza los miembrios del colectivo
     * @param members arrays de los usuarios miembros 
     */
    // public void setMembers(User[] members){ this.members = members; }
   

    // METODOS

    // addChild

    // /**
    //  * Permite a un usuario unirse al colectivo
    //  * @param u usuario a unirse
    //  * @return true si el usuario se anade correctamente
    //  * @return false si el usuario no puede unirse al colectivo
    //  */
    // public boolean join(User u){
    //     // checkar si usuario esta en colectivos padres o hijos
    //     // return false

    //     members.add(u);
    //     // actualizar en user los colectivos a los que pertenece
    //     return true;
    // }

    // /**
    //  * Permite a un usuario borrarse del colectivo
    //  * @param u usuario a borrarse
    //  * @return true si el usuario es borrado correctamente
    //  * @return false si el usuario no puede borrarse al colectivo
    //  */
    // public boolean disjoin(User u){
    //     // check u == manager: return false
    //     if (this.checkMember(u)){
    //         members.remove(u); //ask teacher pq no se si existe
    //     }
    //     return true;
    // }

    // /**
    //  * Comprueba si un usuario pertenece al colectivo
    //  * @param u usuario
    //  * @return true si el usuario pertenece al colectivo
    //  * @return false si el usuario no pertenece al colectivo
    //  */
    // public boolean checkMember(User u){
    //     for (User memb: members){
    //         if (u.equals(memb))
    //             return true;
    //     }
    //     return false;
    // }
}
