package across.gui;

import across.control.*;
import across.control.admin.*;
//import across.control.menu.*;
import across.control.start.*;
import across.control.user.*;
//import across.control.user.project.*;
import across.gui.start.*;
import across.gui.user.*;
import across.gui.admin.*;
import across.gui.general.*;
import across.model.application.Application;

import javax.swing.*;
import java.awt.*;


/**
 * Clase MainFrame de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame{

    //private UserMenu userMenu = new UserMenu();

    /****************** PANELES ******************/
    private PanelInicio inicio = new PanelInicio();
    private PanelRegistro registro = new PanelRegistro();
    private PanelLogin login = new PanelLogin();
    /* user */
    private PanelInicioUser inicioUser = new PanelInicioUser();
    private PanelNewCollective nuevoColectivo = new PanelNewCollective();
    private PanelNewProject nuevoProyecto = new PanelNewProject();
    /* admin */
    private PanelInicioAdmin inicioAdmin = new PanelInicioAdmin();
    private PanelAdminUsuarios adminUsuarios = new PanelAdminUsuarios();
    private PanelAdminConfig adminConfig = new PanelAdminConfig();
    /* generales */
    private PanelDisplayProject displayProject = new PanelDisplayProject();
    
    /*************** CONTROLADORES ***************/
    /* inicio */
    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin contLogin;
    private ControladorRegistro contRegistro;
    /* menu user */
    /*private ControladorToInicio contToInicio;
    private ControladorToPerfil contToPerfil;*/
    /* inicio user */
    private ControladorUserCrearProyecto contUserCrearProyecto;
    private ControladorUserDisplayProject contUserDisplayProject;
    /* crear proyecto */
    private ControladorNewProject contNuevoProyecto;
    private ControladorLoadImage contCargarImagen;
    /* display proyecto */
    /*private ControladorVotar contVotar;
    private ControladorSeguir contSeguir;
    private ControladorDejarSeguir contDejarSeguir;*/
    /* inicio admin */
    private ControladorAdminUsuarios contAdminUsuarios;
    private ControladorAdminConfig contAdminConfig;
    private ControladorAdminProyectos contAdminProyectos;
    private ControladorAdminConfigVotes contAdminConfigVotes;
    private ControladorAdminConfigCaducidad contAdminConfigCaducidad;
    private ControladorAdminProyectosGuardar contAdminProyectosGuardar;


    private JPanel contentPane;

    /**
     * Constructor de la ventana principal de la aplicacion
     */
    public MainFrame(){
        super("Across Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,700,500);

        contentPane = new JPanel(new CardLayout());
        setContentPane(contentPane);
        
        fillContentPane();
    }

    /**
     * Muestra el panel cuyo nombre es el pasado como argumento
     * 
     * @param carta nomber del panel a mostrar
     */
    public void showPanel(String carta) {
        Application.saveData(Application.getApplication());
		CardLayout layout = (CardLayout)contentPane.getLayout();
		layout.show(contentPane, carta);
    }
    
    /**
     * Rellena el contenedor del panel de la ventana principal con todos los paneles 
     * de la aplicacion
     */
    private void fillContentPane(){
        contentPane.add(inicio, "inicio");
        contentPane.add(registro, "registro");
        contentPane.add(login, "login");

        /* PANELES DEL USUARIO */
        contentPane.add(inicioUser, "inicioUser");
        contentPane.add(nuevoColectivo, "nuevoColectivo");
        contentPane.add(nuevoProyecto, "nuevoProyecto");
        contentPane.add(displayProject, "displayProject");

        /* PANELES DEL ADMINISTRADOR */
        contentPane.add(inicioAdmin, "inicioAdmin");
        contentPane.add(adminUsuarios, "adminUsuarios");
        contentPane.add(adminConfig, "adminConfig");
    }

    /**
     * Devuelve el panel inicial de la aplicacion
     * 
     * @return panel inicial
     */
    public PanelInicio getInicio(){
        return inicio;
    }

    /**
     * Devuelve el panel de registro de la aplicacion
     * 
     * @return panel de registro
     */
    public PanelRegistro getRegistro(){
        return registro;
    }

    /**
     * Devuelve el panel de login de la aplicacion
     * 
     * @return panel de login
     */
    public PanelLogin getLogin(){
        return login;
    }

    /**
     * Devuelve el panel inicial del usuario
     * 
     * @return panel inicial del usuario
     */
    public PanelInicioUser getInicioUser(){
        return inicioUser;
    }

    /**
     * Devuelve el panel inicial del administrador
     * 
     * @return panel inicial del administrador
     */
    public PanelInicioAdmin getInicioAdmin(){
        return inicioAdmin;
    }
    
    /**
     * Devuelve el panel de usuarios del administrador
     * 
     * @return panel de usuarios del admin
     */
    public PanelAdminUsuarios getAdminUsuarios(){
        return adminUsuarios;
    }
    
    /**
     * Devuelve el panel de configuracion del administrador
     * 
     * @return panel de configuracion del admin
     */
    public PanelAdminConfig getAdminConfig(){
        return adminConfig;
    }

    /**
     * Devuelve el panel de crear proyecto
     * 
     * @return panel de crear proyecto
     */
    public PanelNewProject getNewProject(){
        return nuevoProyecto;
    }
    
    /**
     * Devuelve el panel de visualizar proyecto
     * 
     * @return panel de visualizar proyecto
     */
    public PanelDisplayProject getDisplayProject(){
        return displayProject;
    }
    
    public void setPanelAdminConfig(PanelAdminConfig panel) {
    	adminConfig = panel;
    }

    /**
     * Establece los controlador de los diferentes paneles mediante el uso de metodos
     * auxiliares para los diferentes tipos de controladores
     * 
     * @param controlador objeto controlador general
     */
    public void setControlador(Controlador controlador){

        controladorInicio(controlador);
        controladorRegistro(controlador);
        controladorLogin(controlador);

        //controladorMenuUser(controlador);
        controladorUser(controlador);
        controladorNuevoProyecto(controlador);
        
        controladorAdmin(controlador);
        controladorAdminConfig(controlador);
        controladorAdminUsuarios(controlador);

    }
    
    /**
     * Establece los controladors del panel de inicio
     * 
     * @param controlador objeto controlador general
     */
    private void controladorInicio(Controlador controlador){
        this.inicioRegistro = controlador.getInicioRegistro();
        inicio.setControlRegistro(inicioRegistro);
        this.inicioLogin = controlador.getInicioLogin();
        inicio.setControlLogin(inicioLogin);
    }

    /**
     * Establece los controladors del panel de login
     * 
     * @param controlador objeto controlador general
     */
    private void controladorLogin(Controlador controlador){
        this.contLogin = controlador.getLogin();
        login.setControlContinuar(contLogin);
    }
    
    /**
     * Establece los controladors del panel de registro
     * 
     * @param controlador objeto controlador general
     */
    private void controladorRegistro(Controlador controlador){
        this.contRegistro = controlador.getRegistro();
        registro.setControlContinuar(contRegistro);
    }

    /**
     * Establece los controladors del panel inicial de usuario
     * 
     * @param controlador objeto controlador general
     */
    private void controladorUser(Controlador controlador){
        this.contUserCrearProyecto = controlador.getUserCrearProyecto();
        inicioUser.setControlCrearProyecto(contUserCrearProyecto);
        
        this.contUserDisplayProject = controlador.getUserDisplayProject();
        inicioUser.setControlVerProyecto(contUserDisplayProject);
    }

    /**
     * Establece los controladors del menu del usuario
     * 
     * @param controlador objeto controlador general
     */
    /*private void controladorMenuUser(Controlador controlador){
        this.contToInicio = controlador.getToInicio();
        userMenu.setControlToInicio(contToInicio);
        
        this.contToPerfil = controlador.getToPerfil();
        userMenu.setControlToPerfil(contToPerfil);
    }*/
    
    /**
     * Establece los controladores del panel inicial de usuario
     * 
     * @param controlador objeto controlador general
     */
    private void controladorAdmin(Controlador controlador){
        this.contAdminUsuarios = controlador.getAdminUsuarios();
        inicioAdmin.setControlAdminUsuarios(contAdminUsuarios);
        
        this.contAdminConfig = controlador.getAdminConfig();
        inicioAdmin.setControlAdminConfig(contAdminConfig);
        
        this.contAdminProyectosGuardar = controlador.getAdminProyectosGuardar();
        inicioAdmin.setControlAdminGuardar(contAdminProyectosGuardar);
    }
    
    private void controladorAdminConfig(Controlador controlador) {
    	this.contAdminUsuarios = controlador.getAdminUsuarios();
    	adminConfig.setControlAdminUsuarios(contAdminUsuarios);
    	
    	this.contAdminProyectos = controlador.getAdminProyectos();
    	adminConfig.setControlAdminProyectos(contAdminProyectos);
    	
    	this.contAdminConfigVotes = controlador.getAdminConfigVotes();
    	adminConfig.setControlAdminConfigVotes(contAdminConfigVotes);
    	
    	this.contAdminConfigCaducidad = controlador.getAdminConfigCaducidad();
    	adminConfig.setControlAdminConfigCaducidad(contAdminConfigCaducidad);
    }
    
    private void controladorAdminUsuarios(Controlador controlador) {
    	this.contAdminConfig = controlador.getAdminConfig();
    	adminUsuarios.setControlAdminConfig(contAdminConfig);
    	
    	this.contAdminProyectos = controlador.getAdminProyectos();
    	adminUsuarios.setControlAdminProyectos(contAdminProyectos);
    	
    }

    /**
     * Establece los controladors del panel de crear proyecto
     * 
     * @param controlador objeto controlador general
     */
    private void controladorNuevoProyecto(Controlador controlador){
        this.contCargarImagen = controlador.getCargarImagen();
        nuevoProyecto.setControlFoto(contCargarImagen);

        this.contNuevoProyecto = controlador.getNuevoProyecto();
        nuevoProyecto.setControlProponerProyecto(contNuevoProyecto);
    }

    /**
     * Establece los controladors del panel DisplayProject
     * 
     * @param controlador objeto controlador general
     */
    /*private void controladorDisplayProject(Controlador controlador) {
        this.contVotar = controlador.getVotar();
        displayProject.setControlVotar(contVotar);
        this.contSeguir = controlador.getSeguir();
        displayProject.setControlSeguir(contSeguir);
        this.contDejarSeguir = controlador.getDejarSeguir();
        displayProject.setControlDejarSeguir(contDejarSeguir);
    }*/

}
