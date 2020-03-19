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
public abstract class UserCollective {
   
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
