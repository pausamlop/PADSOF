package across;
import java.util.*;


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

    private Admin admin;
    // PROJECT[]
    private ArrayList<Collective> collectives;
    private ArrayList<User> users;
    private ArrayList<User> nonValidatedUsers;
    

    /**
     * Constructor
     * @param daysExp numeros de dias sin votos para que caduque un proyecto
     * @param minV minimo numero de votos en un proyecto para poder financiar
     */
    public Application(int daysExp, int minV){
        daysExpiration = daysExp;
        minVotes = minV;
        collectives = new ArrayList<Collective>();
        users = new ArrayList<User>();
        nonValidatedUsers = new ArrayList<User>();
    }


    // GETTERS
    /**
     * Devuelve el numero de dias sin votos nuevos para que un proyecto caduque
     * @return daysExpiration
     */
    public int getDaysExpiration(){ return daysExpiration; }

    /**
     * Devuelve el minimo numero de votos en un proyecto para poder financiar
     * @return minVotes
     */
    public int getMinVotes(){ return minVotes; }


    
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
        User u = new User(username, NIF, password, false);
        nonValidatedUsers.add(u);
        // mandar al admin a validar
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
        for (User u: users){
            if (username.equals(u.getUsername()) && password.equals(u.getPassword()))
                return true;
        }

        // no sabemos muy bien como funciona esto
        return false;
    }

    
    
    // METODOS DE PROJECT Y COLECTIVOS



   
}
