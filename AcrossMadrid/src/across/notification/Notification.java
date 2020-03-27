package across.notification;

import across.project.*;
import across.user.*;
import across.enumerations.*;
import across.application.Application;

import java.io.*;
import java.util.*;

/**
 * Clase abstracta Notification
 * 
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */

public abstract class Notification implements Serializable{

    private String message;

    /* GETTERS */
    public String getMessage() { return message; }


    /* SETTERS */
	public void setMessage(String message) { this.message = message; }


    /**
     * Metodo que dependiendo del estado del proyecto a notificar, genera un mensaje personalizado
     * 
     * @param ps estado del proyecto
     * @return  mensaje a enviar
     */
    public abstract String generateMessage();


    /**
     * Metodo que genera la notificicacion, enviandose a sus correspondientes receptores
     */
    public abstract void sendNotification();

}