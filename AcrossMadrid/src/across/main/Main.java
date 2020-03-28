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
    public static Application loadData(){
        Application app = null;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("app.objectData"));
            app = (Application)input.readObject();
            input.close();
        }catch(FileNotFoundException exc) {
        	app = Application.getApplication();
        }catch(IOException | ClassNotFoundException exc){
            exc.printStackTrace();
        }
        return app;
    }


    
    /**
     * Funcion principal de la aplicacion Across Madrid
     * 
     */
    public static void main(String[] args) {
        Application.setApplication(loadData());
        Application app = Application.getApplication();
        app.setLogOut(false);

        System.out.println(" ------------------------------------------------------");
        System.out.println(" ------------- BIENVENIDO A ACROSS MADRID -------------");
        System.out.println(" ------------------------------------------------------");
        if (!app.pantallaLogin()){
            System.out.println("No se ha podido iniciar la app");
            return; 
        }

        while (!app.getLogOut()){
            app.pantallaPrincipal();
        }

        saveData(app);

    }
}