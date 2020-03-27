package across.admin;

import across.application.*;
import across.enumerations.*;
import across.notification.*;
import across.project.*;
import across.user.*;

import java.io.*;
import java.util.*;

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

    /**
     * Metodo para obtener el array de notificaciones del administrador
     * @return notificaciones del admin
     */
    public ArrayList<Notification> getNotifications(){
        return notifications;
    }
    /**
     * Metodo para establecer el array de notificaicones
     */
    public void setNotifications(ArrayList<Notification> notifications){
        this.notifications = notifications;
    }

    /**
     * Metodo para añadir una notificacion a la lista de notificaciones del administrador
     * 
     * @param notification notificacion a añadir
     */
    public void addNotification(Notification notificacion){
        this.notifications.add(notificacion);
    }


    /************************************************************************/
    /************ FUNCIONES AUXILIARES PARA PROBAR FUNCIONAMIENTO ***********/
    /************************************************************************/
    
    public void principalAdmin(){
        displayNotifications();
        System.out.println("Ver notificaciones pendientes (n), ver usuarios (u),ir a configuracion de la app (c):");
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opc = reader.readLine();
            switch (opc) {
                case "n":
                    displayNotifications();
                
                case "u":
                    displayUsers();

                case "c":
                    configuracion();

                case "q":
                    reader.close();
                    Application.getApplication().logout();
            }   

            reader.close();  
        }catch(IOException exc){
            exc.printStackTrace();
        }

    }

    
    private void displayNotifications(){
        if (notifications.size() == 0){
            System.out.println("notif");
            return;
        } 
        
        System.out.println(" ------------- NOTIFICACIONES -------------");

        int count = 1;
        for (Notification n: notifications){
            System.out.println(count + ". " + n.getMessage());
            count++;
        }

        System.out.println("Elige notificacion (por numero): ");
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int opc = 0;
            try{
                opc = Integer.parseInt(reader.readLine()) - 1;

                if (opc >= notifications.size() || opc < 0){
                    //reader.close();
                    displayNotifications();
                }
            }catch(NumberFormatException exc){
                //reader.close();
                displayNotifications();
            }

            Notification notif = notifications.get(opc);
            String opc2;
            if (notif instanceof NotificationAdminUser){
                System.out.println("Validar (v) o rechazar (r) usuario:");
                opc2 = reader.readLine();
                if (opc2 == "v")
                    ((NotificationAdminUser) notif).getUser().validate();
                else 
                    ((NotificationAdminUser) notif).getUser().reject();
            }
            else{ //notificacion de proyecto
                System.out.println("Validar (v) o rechazar (r) proyecto: ");
                opc2 = reader.readLine();
                if (opc2 == "v")
                    ((NotificationAdminProject) notif).getProject().validate();
                else 
                    ((NotificationAdminProject) notif).getProject().reject();
            }
            
            //eliminar notificacion
            notifications.remove(notif);


        }catch(IOException exc){
            exc.printStackTrace();
        }

    }


    private void displayUsers(){
        Application app = Application.getApplication();

        if (app.getUsers().size() == 0){
            System.out.println("Todavia no hay ningun usuario registrado");
            return;
        }

        System.out.println("Seleccione usuario:");
         
        int cont = 1;
        for (User u: app.getUsers())
            System.out.println(cont + ". " + u.getUsername());
        
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int num = 0;
            try{
                num = Integer.parseInt(reader.readLine()) - 1;
                if (num >= cont || num < 0){
                    reader.close();
                    displayUsers();
                }
            }catch(NumberFormatException excep){
                reader.close();
                displayUsers();
            }

            User u = app.getUsers().get(num);
            String ans;

            System.out.println(u);
            if (u.getBlocked()){
                System.out.println("\nEste usuario ha sido bloqueado. ¿Desea desbloquearlo? (y/n)");
                ans = reader.readLine();
                if (ans == "y"){
                    u.unblock();
                    System.out.println("Usuario desbloqueado");
                }
            }else{
                System.out.println("\n¿Desea bloquear este usuario? (y/n)");
                ans = reader.readLine();
                if (ans == "y"){
                    System.out.println("Escriba motivo de bloqueo:");
                    String motivo = reader.readLine();
                    u.block(motivo);
                    System.out.println("Usuario bloqueado. No podra acceder al sistema hasta que sea desbloqueado");
                }
            }
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }

    private void configuracion(){
        Application app = Application.getApplication();
        System.out.println(" ------------- CONFIGURACION -------------");
        
        System.out.println("\nEl numero minimo de votos para enviar un proyecto a financiacion es de " + app.getMinVotes() + "votos.");
        System.out.println("Han de pasar " + app.getDaysExpiration() +" dias para que un proyecto caduque.");

        System.out.println("Escriba 'v' para editar el numero minimo de votos y 'd' para cambiar los dias que ha de pasar para que un proyecto caduque:");

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opc = reader.readLine();

            if (opc == "v"){
                System.out.println("Escriba numero de votos minimo:");
                try{
                    int num = Integer.parseInt(reader.readLine());
                    if (num < 0){
                        System.out.println("No puede introducir un numero negativo");
                        reader.close();
                        configuracion();
                    }
                    app.setMinVotes(num);
                }catch(NumberFormatException excep){
                    reader.close();
                    configuracion();
                }
            }else if (opc == "d"){
                System.out.println("Escriba numero de dias para caducidad:");
                try{
                    int num = Integer.parseInt(reader.readLine());
                    if (num < 0){
                        System.out.println("No puede introducir un numero negativo");
                        reader.close();
                        configuracion();
                    }
                    app.setDaysExpiration(num);
                }catch(NumberFormatException excep){
                    reader.close();
                    configuracion();
                }
            }
            reader.close();
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }
}