package across.model.notification;

import java.io.*;

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

    /**
     * Devuelve el mensaje asociado a la notificacion 
     * 
     * @return mensaje de la notificacion
     */
    public String getMessage() { return message; }


    /**
     * Establece el mensaje de una notificacion
     * 
     * @param message cadena de caracteres 
     */
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