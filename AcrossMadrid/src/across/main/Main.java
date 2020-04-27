package across.main;


import across.model.application.*;
import across.model.enumerations.projectState;
import across.model.project.Project;

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
     * Funcion principal de la aplicacion Across Madrid
     * 
     */
    public static void main(String[] args) {
        /*Application.setApplication(Application.loadData());
        Application app = Application.getApplication();
        app.setLogOut(false);

        System.out.println(" ------------------------------------------------------");
        System.out.println(" ------------- BIENVENIDO A ACROSS MADRID -------------");
        System.out.println(" ------------------------------------------------------");  
    
        while (true){
            
            app.checkFinance();
            app.checkExpired();

            app.pantallaLogin();
            if (app.getLogOut()){
                System.out.println("\nSaliendo de la aplicacion");
                break;
            }
            app.pantallaPrincipal();
            app.setLogOut(false);
            Application.saveData(app);*/
    	
    	Application.setApplication(Application.loadData());
        Application app = Application.getApplication();
        
        for(Project aux : app.getNonValidatedProjects()) {
        	aux.setProjectState(projectState.ENVALIDACION);
        }
        
        Application.saveData(app);
        }

        

    }