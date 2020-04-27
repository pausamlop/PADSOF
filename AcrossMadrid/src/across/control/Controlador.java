package across.control;

import across.model.application.Application;
import across.gui.MainFrame;
//import across.control.menu.*;
import across.control.admin.ControladorAdminConfig;
import across.control.admin.ControladorAdminConfigCaducidad;
import across.control.admin.ControladorAdminConfigVotes;
import across.control.admin.ControladorAdminProyectos;
import across.control.admin.ControladorAdminProyectosGuardar;
import across.control.admin.ControladorAdminUsuarios;
import across.control.start.*;
import across.control.user.*;
//import across.control.user.project.*;

/**
 * Clase Controlador
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class Controlador {

    /* inicio de la app */
    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin login;
    private ControladorRegistro reg;

    /* menu de user */
    /*private ControladorToInicio toInicio;
    private ControladorToPerfil toPerfil;
     */
    /* inicio de User */
    private ControladorUserCrearProyecto userCrearProyecto;
    private ControladorUserDisplayProject userDisplayProject;
    
    /* control nuevo proyecto */
    private ControladorNewProject nuevoProyecto;
    private ControladorLoadImage cargarImagen;

    /* botones de DisplayProject */
    /*private ControladorVotar votar;
    private ControladorSeguir seguir;
    private ControladorDejarSeguir dejarSeguir;
*/
    /* inicio de admin */
    private ControladorAdminUsuarios adminUsuarios;
    private ControladorAdminConfig adminConfig;
    private ControladorAdminProyectos adminProyectos;
    private ControladorAdminConfigVotes adminConfigVotes;
    private ControladorAdminConfigCaducidad adminConfigCaducidad;
    private ControladorAdminProyectosGuardar adminProyectosGuardar;

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
        /* inicio de la app */
        inicioRegistro = new ControladorInicioRegistro(frame, model);
        inicioLogin = new ControladorInicioLogin(frame, model);
        login = new ControladorLogin(frame, model);
        reg = new ControladorRegistro(frame, model);

        /* menu user */
        /*toInicio = new ControladorToInicio(frame, model);
        toPerfil = new ControladorToPerfil(frame, model);
		/*
        /* inicio de user */
        userCrearProyecto = new ControladorUserCrearProyecto(frame, model);
        userDisplayProject = new ControladorUserDisplayProject(frame, model);

        /* display proyecto */
        /*votar = new ControladorVotar(frame, model);
        seguir = new ControladorSeguir(frame, model);
        dejarSeguir = new ControladorDejarSeguir(frame, model);
         */
        /* inicio de admin */
        adminUsuarios = new ControladorAdminUsuarios(frame, model);
        adminConfig = new ControladorAdminConfig(frame, model);
        adminProyectos = new ControladorAdminProyectos(frame, model);
        adminConfigVotes = new ControladorAdminConfigVotes(frame, model);
        adminConfigCaducidad = new ControladorAdminConfigCaducidad(frame, model);
        adminProyectosGuardar = new ControladorAdminProyectosGuardar(frame, model);
        
        /* nuevo proyecto */
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
     * Devuelve el controlador que lleva a la pantalla principal de usuario
     * 
     * @return controlador de inicio
     */
    /*public ControladorToInicio getToInicio(){
        return this.toInicio;
    }*/

    /**
     * Devuelve el controlador que lleva al perfil del usuario
     * 
     * @return controlador de perfil
     */
    /*public ControladorToPerfil getToPerfil(){
        return this.toPerfil;
    }*/

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
     * Devuelve el controlador que permite votar un proyecto
     * 
     * @return controlador de votar proyecto
     */
    /*public ControladorVotar getVotar(){
        return votar;
    }*/

    /**
     * Devuelve el controlador que permite seguir un proyecto
     * 
     * @return controlador de votar proyecto
     */
    /*public ControladorSeguir getSeguir(){
        return seguir;
    }*/

    /**
     * Devuelve el controlador que permite votar un proyecto
     * 
     * @return controlador de votar proyecto
     */
    /*public ControladorDejarSeguir getDejarSeguir(){
        return dejarSeguir;
    }*/
    
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
    
    public ControladorAdminConfigVotes getAdminConfigVotes() {
    	return adminConfigVotes;
    }
    
    public ControladorAdminConfigCaducidad getAdminConfigCaducidad() {
    	return adminConfigCaducidad;
    }
    
    public ControladorAdminProyectosGuardar getAdminProyectosGuardar() {
    	return adminProyectosGuardar;
    }

}