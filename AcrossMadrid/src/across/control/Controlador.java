package across.control;

import across.model.application.Application;
import across.gui.MainFrame;
import across.control.admin.ControladorAdminConfig;
import across.control.admin.ControladorAdminProyectos;
import across.control.admin.ControladorAdminUsuarios;
import across.control.start.*;
import across.control.user.*;

/**
 * Clase Controlador
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class Controlador {

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin login;
    private ControladorRegistro reg;

    private ControladorUserCrearProyecto userCrearProyecto;
    private ControladorUserDisplayProject userDisplayProject;
    
    private ControladorAdminUsuarios adminUsuarios;
    private ControladorAdminConfig adminConfig;
    private ControladorAdminProyectos adminProyectos;

    private ControladorNewProject nuevoProyecto;
    private ControladorLoadImage cargarImagen;

    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase Controlador
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public Controlador(MainFrame frame, Application model){
        this.frame = frame;
        this.model = model;
        setupControladores();
    }

    /**
     * Crea todos los controladores de la aplicacion
     */
    private void setupControladores(){
        inicioRegistro = new ControladorInicioRegistro(frame, model);
        inicioLogin = new ControladorInicioLogin(frame, model);
        login = new ControladorLogin(frame, model);
        reg = new ControladorRegistro(frame, model);

        userCrearProyecto = new ControladorUserCrearProyecto(frame, model);
        userDisplayProject = new ControladorUserDisplayProject(frame, model);
        
        adminUsuarios = new ControladorAdminUsuarios(frame, model);
        adminConfig = new ControladorAdminConfig(frame, model);
        adminProyectos = new ControladorAdminProyectos(frame, model);

        nuevoProyecto = new ControladorNewProject(frame, model);
        cargarImagen = new ControladorLoadImage(frame, model);
    }

    /**
     * Devuelve el controlador que lleva del panel inicial al de registro
     * 
     * @return controlador de incio a registro
     */
    public ControladorInicioRegistro getInicioRegistro(){
        return this.inicioRegistro;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial al de login
     * 
     * @return controlador de inicio a login
     */
    public ControladorInicioLogin getInicioLogin(){
        return this.inicioLogin;
    }

    /**
     * Devuelve el controlador que maneja el inicio de sesion
     * 
     * @return controlador de login
     */
    public ControladorLogin getLogin(){
        return this.login;
    }
    
    /**
     * Devuelve el controlador que maneja el registro de un usuario
     * 
     * @return controlador de registro
     */
    public ControladorRegistro getRegistro(){
        return this.reg;
    }

    /**
     * Devuelve el controlador que maneja la creacion de un proyecto nuevo
     * 
     * @return controlador para crear proyectos
     */
    public ControladorNewProject getNuevoProyecto(){
        return nuevoProyecto;
    }

    /**
     * Devuelve el controlador que permite cargar una imagen a la aplicacion
     * 
     * @return controlador de cargar imagenes
     */
    public ControladorLoadImage getCargarImagen(){
        return cargarImagen;
    }

    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de crear proyecto
     * 
     * @return controlador de inicio a crear proyecto
     */
    public ControladorUserCrearProyecto getUserCrearProyecto(){
        return userCrearProyecto;
    }
    
    /**
     * Devuelve el controlador que lleva del panel inicial del usuario al de visualizar proyecto
     * 
     * @return controlador de inicio a visualizar proyecto
     */
    public ControladorUserDisplayProject getUserDisplayProject(){
        return userDisplayProject;
    }
    
    /**
     * Devuelve el controladro que lleva de la pantalla inicial del admin a la pantalla de gestion de usuarios
     * 
     * @return controlador de inicioadmin a adminUsuarios
     */
    public ControladorAdminUsuarios getAdminUsuarios() {
    	return adminUsuarios;
    }
    
    /**
     * Devuelve el controladro que lleva de la pantalla inicial del admin a la pantalla de configuracion
     * 
     * @return controlador de inicioadmin a adminConfig
     */
    public ControladorAdminConfig getAdminConfig() {
    	return adminConfig;
    }
    
    public ControladorAdminProyectos getAdminProyectos() {
    	return adminProyectos;
    }
    

}