package across.main;

import java.util.*;
import java.io.*;

import across.application.*;
import across.enumerations.*;
import across.project.*;
import across.user.*;
import across.application.*;

/**
 * Clase Main
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class Main{

    /**
     * Guarda el objecto Application pasado como parametro  
     * 
     * @param app aplicacion que se quiere guardar
     */
    public static void saveData(Application app){
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("app.objectData"));
            output.writeObject(app);
            output.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * Carga la informacion de un objeto Application guardada anteriormente en un archivo
     * 
     * @return Application con la informacion del archivo
     */
    public static Object loadData(){
        Object app = null;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("app.objectData"));
            app = input.readObject();
            input.close();
        }catch(IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return app;
    }


    
    /**
     * Funcion principal de la aplicacion Across Madrid
     * 
     */
    public static void main() {
    	Application app = Application.getApplication();
        app.setApplication(loadData());

        System.out.println(" ----- BIENVENIDO A ACROSS MADRID -----");
        if (!app.pantallaLogin()){
            System.out.println("No se ha podido iniciar la app"); // hay que añadir lo de wrong password
            System.exit(1);
        }

        app.pantallaPrincipal();

//        User user = app.getCurrentUser();
//        if (user != null){
//            if (user.getBlocked()){
//                System.out.println("Ha sido bloqueado por el administrador");
//                // mensaje de Admin
//                // solo puede cerrar sesion
//            }
//            else{
//                System.out.println();
//                user.pantallaUser();
//                // elegir entre: MiPerfil, SolicitarInforme, CrearColectivo, CrearProyecto, Buscar, Notificaciones
//            }
//        }
//        else{ /* admin */
//        	app.getAdmin().pantallaAdmin();
//            // elegir entre: Ver proyectos (pa validar y demas), ver usuarios (igual), configuracion
//        }
    }
}