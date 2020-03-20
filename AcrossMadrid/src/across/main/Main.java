package across.main;

import across.application.*;
import across.collective.*;
import across.enumerations.*;
import across.project.*;
import across.user.*;

import java.util.*;

import com.apple.eawt.Application;

import java.io.*;

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
        Application.setApplication(loadData());
        Application app = Application.getApplication();

        System.out.println(" ----- BIENVENIDO A ACROSS MADRID -----");
        if (!app.inicio()){
            System.out.println("No se ha podido iniciar la app"); // hay que añadir lo de worng password
            System.exit(1);
        }

    }
}