package across.gui;

import across.control.*;
import across.control.admin.*;
import across.control.menu.*;
import across.control.start.*;
import across.control.user.*;
import across.control.user.project.*;
import across.gui.admin.*;
import across.gui.general.*;
import across.gui.notif.PanelNotifications;
import across.gui.start.*;
import across.gui.user.*;
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
public class MainFrame extends JFrame {

    /****************** PANELES ******************/
    private PanelInicio inicio = new PanelInicio();
    private PanelRegistro registro = new PanelRegistro();
    private PanelLogin login = new PanelLogin();
    /* user */
    private PanelInicioUser inicioUser = new PanelInicioUser();
    private PanelPerfil perfil = new PanelPerfil();
    private PanelNotifications notif = new PanelNotifications();
    private PanelNewCollective nuevoColectivo = new PanelNewCollective();
    private PanelNewProject nuevoProyecto = new PanelNewProject();
    /* admin */
    private PanelInicioAdmin inicioAdmin = new PanelInicioAdmin();
    private PanelAdminUsuarios adminUsuarios = new PanelAdminUsuarios();
    private PanelAdminConfig adminConfig = new PanelAdminConfig();
    /* generales */
    private PanelDisplayProject displayProject = new PanelDisplayProject();
    private PanelDisplayCollective displayCollective = new PanelDisplayCollective();
    
    /*************** CONTROLADORES ***************/
    private ControladorLogout contLogout;
    private ControladorToInicio contToInicio;
    private ControladorToPerfil contToPerfil;
    /* inicio */
    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin contLogin;
    private ControladorRegistro contRegistro;
    private ControladorAtras contAtras;

    private ControladorToNotif contNotif;
    /* inicio user */
    private ControladorUserCrearProyecto contUserCrearProyecto;
    private ControladorUserCrearColectivo contUserCrearColectivo;
    private ControladorUserDisplayProject contUserDisplayProject;
    private ControladorUserDisplayCollective contUserDisplayCollective;
    /* crear colectivo */
    private ControladorNewCollective contNuevoColectivo;
    /* display colectivo */
    private ControladorJoin contJoin;
    /* crear proyecto */
    private ControladorNewProject contNuevoProyecto;
    private ControladorLoadImage contCargarImagen;
    /* display proyecto */
    private ControladorVotar contVotar;
    private ControladorSeguir contSeguir;
    private ControladorDejarSeguir contDejarSeguir;
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
     * Devuelve el panel que se muestra actualmente en la aplicacion
     * 
     * @return panel actual
     */
    public JPanel getCurrentPanel(){
        JPanel card = null;
        for (Component comp : contentPane.getComponents()) {
            if (comp.isVisible() == true) {
                return card;
            }
        }
        return null;
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
        //contentPane.add(perfil, "perfil");
        contentPane.add(notif, "notif");
        contentPane.add(nuevoColectivo, "nuevoColectivo");
        contentPane.add(nuevoProyecto, "nuevoProyecto");
        contentPane.add(displayProject, "displayProject");
        contentPane.add(displayCollective, "displayCollective");

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
     * Devuelve el panel de perfil del usuario
     * 
     * @return panel de perfil del usuario
     */
    public PanelPerfil getPerfil(){
        return perfil;
    }

    /**
     * Devuelve el panel de notificaciones 
     * 
     * @return panel de notificaciones
     */
    public PanelNotifications getNotif(){
        return notif;
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
     * Devuelve el panel de crear colectivo
     * 
     * @return panel de crear colectivo
     */
    public PanelNewCollective getNewCollective(){
        return nuevoColectivo;
    }
    
    /**
     * Devuelve el panel de visualizar proyecto
     * 
     * @return panel de visualizar proyecto
     */
    public PanelDisplayProject getDisplayProject(){
        return displayProject;
    }
    
    /**
     * Devuelve el panel de visualizar proyecto
     * 
     * @return panel de visualizar proyecto
     */
    public PanelDisplayCollective getDisplayCollective(){
        return displayCollective;
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

        controladorLogout(controlador);
        controladorInicio(controlador);
        controladorRegistro(controlador);
        controladorLogin(controlador);
        controladorAtras(controlador);

        controladorHome(controlador);
        controladorUser(controlador);
        controladorNuevoProyecto(controlador);
        controladorNuevoColectivo(controlador);
        controladorDisplayProject(controlador);
        controladorDisplayCollective(controlador);
        
        controladorAdmin(controlador);
        controladorAdminConfig(controlador);
        controladorAdminUsuarios(controlador);

    }
    
    /**
     * Establece el controlador de cierre de sesion
     * 
     * @param controlador objeto controlador general
     */
    private void controladorLogout(Controlador controlador){
        this.contLogout = controlador.getLogout();
        inicioUser.setControlLogout(contLogout);
        inicioAdmin.setControlLogout(contLogout);
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
     * Establece el control de volver a inicio desde registro o login
     * 
     * @param controlador objeto controlador general
     */
    private void controladorAtras(Controlador controlador){
        this.contAtras = controlador.getAtras();
        registro.setControlAtras(contAtras);
        login.setControlAtras(contAtras);
    }

    /**
     * Establece los controladores de inicio y perfil de usuario
     * 
     * @param controlador
     */
    private void controladorHome(Controlador controlador){
        this.contToInicio = controlador.getToInicio();
        displayCollective.setControlToInicio(contToInicio);
        displayProject.setControlToInicio(contToInicio);
        notif.setControlToInicio(contToInicio);
        nuevoColectivo.setControlToInicio(contToInicio);
        nuevoProyecto.setControlToInicio(contToInicio);
        perfil.setControlToInicio(contToInicio);

        this.contToPerfil = controlador.getToPerfil();
        inicioUser.setControlToPefil(contToPerfil);
        displayCollective.setControlToPefil(contToPerfil);
        displayProject.setControlToPefil(contToPerfil);
        notif.setControlToPefil(contToPerfil);
        nuevoColectivo.setControlToPefil(contToPerfil);
        nuevoProyecto.setControlToPefil(contToPerfil);

        this.contNotif = controlador.getToNotif();
        inicioUser.setControlToNotif(contNotif);
        displayCollective.setControlToNotif(contNotif);
        displayProject.setControlToNotif(contNotif);
        notif.setControlToPefil(contToPerfil);
        nuevoColectivo.setControlToNotif(contNotif);
        nuevoProyecto.setControlToNotif(contNotif);
        perfil.setControlToNotif(contNotif);
        inicioAdmin.setControlToNotif(contNotif);
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
        
        this.contUserCrearColectivo = controlador.getUserCrearColectivo();
        inicioUser.setControlCrearColectivo(contUserCrearColectivo);
        
        this.contUserDisplayCollective = controlador.getUserDisplayCollective();
        inicioUser.setControlVerColectivo(contUserDisplayCollective);
    }

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
    private void controladorDisplayProject(Controlador controlador) {
        this.contVotar = controlador.getVotar();
        displayProject.setControlVotar(contVotar);
        this.contSeguir = controlador.getSeguir();
        displayProject.setControlSeguir(contSeguir);
        this.contDejarSeguir = controlador.getDejarSeguir();
        displayProject.setControlDejarSeguir(contDejarSeguir);
    }
    
    /**
     * Establece los controladors del panel de crear proyecto
     * 
     * @param controlador objeto controlador general
     */
    private void controladorNuevoColectivo(Controlador controlador){
        this.contNuevoColectivo = controlador.getNuevoColectivo();
        nuevoColectivo.setControlCreateCollective(contNuevoColectivo);
    }
    
    /**
     * Establece los controladors del panel DisplayCollective
     * 
     * @param controlador objeto controlador general
     */
    private void controladorDisplayCollective(Controlador controlador) {
        this.contJoin = controlador.getJoin();
        displayCollective.setControlJoin(contJoin);

    }

}
