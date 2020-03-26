package across.admin;

import java.io.*;

/**
 * Clase Administrador
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public class Admin implements Serializable {

    private String password = "soyadmin";
    private ArrayList<Notification> notifications = new ArrayList<Notification>();


	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }


	/**
     * Comprueba que la contrasena coincida con la del administrador
	 * 
	 * @param pass password del objeto
     * @return true si la contrasena es correcta
     * @return false si la contrasena es incorrecta
     */
    public boolean login(String pass){
        if (password.equals(pass)) return true;
        else return false;
    }


    /************************************************************************/
    /************ FUNCIONES AUXILIARES PARA PROBAR FUNCIONAMIENTO ***********/
    /************************************************************************/
    
    public void PantallaAdmin(){
        // notifications
        // elegir entre: Ver proyectos (pa validar y demas), ver usuarios (pa block y unblock y validar) y configuracion
    }
}