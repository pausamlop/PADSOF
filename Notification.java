import java.util.*;

/**
 * COMENTAR
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public abstract class Notification{

    private String message;


	/**
     * Constructor de un objeto de la clase Admin
	 * 
	 * @param password contraseña del objeto
     */
    public Notification(String message) { this.message = message; }


    /* GETTERS */
    public String getMessage() { return message; }


    /* SETTERS */
	public void setMessage(String message) { this.message = message; }



	/**
     * NO SE QUE HACE
	 * 
	 * @param projectState
     */
    public void notify(projectState ps){
        // NO SEEEEEE
    }


}