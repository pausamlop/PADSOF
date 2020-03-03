import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public abstract class Admin{

    private String password;


	/**
     * Constructor de un objeto de la clase Admin
	 * 
	 * @param password contraseña del objeto
     */
    public Admin(String password) {
        this.password = password;
	}


    /* GETTERS */
	public String getPassword() { return password; }


    /* SETTERS */
	public void setPassword(String password) { this.password = password; }



	/**
     * Comprueba que el username y el password coinciden con los del objeto
	 * 
	 * @param pass password del objeto
     * @return true si coinciden, false si no coinciden
     */
    public boolean login(String pass){
        if (password.equals(pass)) return true;
        else return false;
    }


}