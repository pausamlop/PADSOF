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
 * Clase Application
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
*/
public class Application {

    private int daysExpiration;
    private int minVotes;

    // ADMIN
    // PROJECT[]
    // COLLECTIVES[]
    // USERS[]
    // NOTVALIDATEDUSERS[]
    

    /**
     * Constructor
     * @param daysExp numeros de dias sin votos para que caduque un proyecto
     * @param minV minimo numero de votos en un proyecto para poder financiar
     */
    public Application(int daysExp, int minV){
        daysExpiration = daysExp;
        minVotes = minV;
    }


    // GETTERS
    /**
     * Devuelve el numero de dias sin votos nuevos para que un proyecto caduque
     * @return daysExpiration
     */
    public String getDaysExpiration(){ return daysExpiration; }

    /**
     * Devuelve el minimo numero de votos en un proyecto para poder financiar
     * @return minVotes
     */
    public String getMinVotes(){ return minVotes; }


    // SETTERS
    /**
     * Actualiza el numero de dias sin votos nuevos para que un proyecto caduque
     * @param daysExp numero de dias
     */
    public void setDaysExpiration(int daysExp){ daysExpiration = daysExp; }

    /**
     * Actualiza el minimo numero de votos en un proyecto para poder financiar
     * @param minV numero de votos
     */
    public void setMinVotes(int minV){ minVotes = minV; }
   

    // METODOS
    /**
     * Permite a un usuario registrarse en la aplicacion
     * @param username nombre de usuario
     * @param NIF NIF del usuario a registrar
     * @param password contrasena del usuario
     * @return true si ...
     * @return false si ...
     */
    public boolean register(String username, String NIF, String password){
        // crea un usuario no validado y se lo envia al admin pa validar
        return true;
    }

    /**
     * Permite a un usuario loguearse en la aplicacion
     * @param username nombre de usuario
     * @param password contrasena del usuario
     * @return true si ...
     * @return false si ...
     */
    public boolean login(String username, String password){
        //llama a login de user o algo asi(creo)
        return true;
    }

    
    
    // METODOS DE PROJECT Y COLECTIVOS



   
}
