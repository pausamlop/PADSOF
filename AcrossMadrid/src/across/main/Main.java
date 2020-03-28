package across.main;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

import across.application.*;
import across.enumerations.*;
import across.project.*;
import across.user.*;

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
            System.out.println("La aplicacion se ha guardado con exito");
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

        LocalDate date = LocalDate.now();
        date = date.plusDays(8);
        app.setCCGGDate(date);

        System.out.println(" ------------------------------------------------------");
        System.out.println(" ------------- BIENVENIDO A ACROSS MADRID -------------");
        System.out.println(" ------------------------------------------------------");

       
    
        while (true){
            // comprobaciones constantes
            app.checkFinance();
            app.checkExpired();

            app.pantallaLogin();
            if (app.getLogOut()){
                System.out.println("\nSaliendo de la aplicacion");
                break;
            }
            app.pantallaPrincipal();
            app.setLogOut(false);
            saveData(app);
        }


       

    }
}