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

    private String passwor = "soyadmin";


    /* GETTERS */
	public String getPassword() { return password; }


    /* SETTERS */
	public void setPassword(String password) { this.password = password; }


    

	/**
     * Comprueba que la contrasena coincida con la del administrador
	 * 
	 * @param pass password del objeto
     * @return true si coinciden, false si no coinciden
     */
    public boolean login(String pass){
        if (password.equals(pass)) return true;
        else return false;
    }


    public static void PantallaAdmin(){
        // elegir entre: Ver proyectos (pa validar y demas), ver usuarios (igual), configuracion
    }
}