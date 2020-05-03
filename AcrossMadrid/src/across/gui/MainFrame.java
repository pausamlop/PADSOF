package across.gui;

import across.control.*;
import across.control.admin.*;
import across.control.menu.*;
import across.control.notif.ControladorVistoNotif;
import across.control.start.*;
import across.control.user.collective.*;
import across.control.user.filtro.*;
import across.control.user.informes.*;
import across.control.user.project.*;
import across.gui.admin.*;
import across.gui.general.*;
import across.gui.notif.*;
import across.gui.start.*;
import across.gui.user.*;
import across.model.application.Application;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

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
    private PanelInformes informes = new PanelInformes();
    /* admin */
    private PanelInicioAdmin inicioAdmin = new PanelInicioAdmin();
    private PanelAdminUsuarios adminUsuarios = new PanelAdminUsuarios();
    private PanelAdminConfig adminConfig = new PanelAdminConfig();
    private FrameAdminUsuariosBloq adminBloq;
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
    /* notificacion */
    private ControladorVistoNotif contVistoNotif;
    /* inicio user */
    private ControladorUserCrearProyecto contUserCrearProyecto;
    private ControladorUserCrearColectivo contUserCrearColectivo;
    private ControladorUserDisplayProject contUserDisplayProject;
    private ControladorUserDisplayCollective contUserDisplayCollective;
    private ControladorToInformes contInformes;
    private ControladorUpdateColectivos contUpdateCol;
    private ControladorFiltrar contFiltrar;
    private ControladorLimpiarFiltro contLimpiar;
    /* crear colectivo */
    private ControladorNewCollective contNuevoColectivo;
    /* displaycolectivo */
    private ControladorJoin contJoin;
    /* crear proyecto */
    private ControladorNewProject contNuevoProyecto;
    private ControladorLoadImage contCargarImagen;
    /* display proyecto */
    private ControladorVotar contVotar;
    private ControladorSeguir contSeguir;
    private ControladorDejarSeguir contDejarSeguir;
    private ControladorFinanciar contFinanciar;
    /* inicio admin */
    private ControladorAdminUsuarios contAdminUsuarios;
    private ControladorAdminConfig contAdminConfig;
    private ControladorAdminProyectos contAdminProyectos;
    private ControladorAdminConfigVotes contAdminConfigVotes;
    private ControladorAdminConfigCaducidad contAdminConfigCaducidad;
    private ControladorAdminProyectosGuardar contAdminProyectosGuardar;
    private ControladorAdminUsuariosBloq contAdminUsuariosBloq;

    private JPanel contentPane;
    
    private Controlador contMain;

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
        contentPane.add(notif, "notif");
        contentPane.add(perfil, "perfil");
        contentPane.add(nuevoColectivo, "nuevoColectivo");
        contentPane.add(nuevoProyecto, "nuevoProyecto");
        contentPane.add(informes, "informes");

        /* DISPLAYS */
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
     * Devuelve el panel de solicitar informes
     * 
     * @return panel de informes 
     */
    public PanelInformes getInformes(){
        return informes;
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
    
    public FrameAdminUsuariosBloq getFrameAdminUsuariosBloq() {
    	return adminBloq;
    }
    
    public void setFrameAdminUsuariosBloq(FrameAdminUsuariosBloq panel) {
    	adminBloq = panel;
    }
    
    public void updateAdminUsuarios(PanelAdminUsuarios panel) {
    	contentPane.remove(adminUsuarios);
    	adminUsuarios = panel;
    	controladorAdminUsuarios(contMain);
    	controladorHome(contMain);
    	adminUsuarios.setUsuariosButton();
    	adminUsuarios.getTabla().clearSelection();
    	contentPane.add(adminUsuarios, "adminUsuarios");
    }
    
    public void updateInicioAdmin(PanelInicioAdmin panel) {
    	contentPane.remove(inicioAdmin);
    	inicioAdmin = panel;
    	controladorAdmin(contMain);
    	controladorHome(contMain);
    	inicioAdmin.setProyectosButton();
    	inicioAdmin.getTabla().clearSelection();
    	contentPane.add(inicioAdmin, "inicioAdmin");
    }

    /**
     * Establece los controlador de los diferentes paneles mediante el uso de metodos
     * auxiliares para los diferentes tipos de controladores
     * 
     * @param controlador objeto controlador general
     */
    public void setControlador(Controlador controlador){
    	
    	contMain = controlador;

        controladorInicio(controlador);
        controladorRegistro(controlador);
        controladorLogin(controlador);
        controladorAtras(controlador);

        controladorVistoNotif(controlador);

        controladorHome(controlador);
        controladorPerfil(controlador);
        controladorUser(controlador);
        controladorNuevoProyecto(controlador);
        controladorNuevoColectivo(controlador);
        controladorDisplayProject(controlador);
        controladorDisplayCollective(controlador);
        controladorInformes(controlador);
        
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
     * Establece el control de marcar una notificacion como vista
     * 
     * @param controlador objeto controlador general
     */
    public void controladorVistoNotif(Controlador controlador){
        this.contVistoNotif = controlador.getVistoNotif();
        notif.setControlVisto(contVistoNotif);
    }

    /**
     * Establece los controladores de inicio y perfil de usuario
     * 
     * @param controlador
     */
    private void controladorHome(Controlador controlador){
    	this.contLogout = controlador.getLogout();
        inicioUser.setControlLogout(contLogout);
        inicioAdmin.setControlLogout(contLogout);
        adminUsuarios.setControlLogout(contLogout);
        adminConfig.setControlLogout(contLogout);
    	
        this.contToInicio = controlador.getToInicio();
        displayCollective.setControlToInicio(contToInicio);
        displayProject.setControlToInicio(contToInicio);
        informes.setControlToInicio(contToInicio);
        notif.setControlToInicio(contToInicio);
        nuevoColectivo.setControlToInicio(contToInicio);
        nuevoProyecto.setControlToInicio(contToInicio);
        perfil.setControlToInicio(contToInicio);

        this.contToPerfil = controlador.getToPerfil();
        inicioUser.setControlToPefil(contToPerfil);
        displayCollective.setControlToPefil(contToPerfil);
        displayProject.setControlToPefil(contToPerfil);
        informes.setControlToPefil(contToPerfil);
        notif.setControlToPefil(contToPerfil);
        nuevoColectivo.setControlToPefil(contToPerfil);
        nuevoProyecto.setControlToPefil(contToPerfil);

        this.contNotif = controlador.getNotif();
        inicioUser.setControlToNotif(contNotif);
        displayCollective.setControlToNotif(contNotif);
        displayProject.setControlToNotif(contNotif);
        informes.setControlToNotif(contNotif);
        notif.setControlToPefil(contToPerfil);
        nuevoColectivo.setControlToNotif(contNotif);
        nuevoProyecto.setControlToNotif(contNotif);
        perfil.setControlToNotif(contNotif);
        inicioAdmin.setControlToNotif(contNotif);
        adminUsuarios.setControlToNotif(contNotif);
        adminConfig.setControlToNotif(contNotif);
    }

    /**
     * Establece los controladors del panel inicial de usuario
     * 
     * @param controlador objeto controlador general
     */
    private void controladorUser(Controlador controlador){
        this.contUserCrearProyecto = controlador.getUserCrearProyecto();
        inicioUser.setControlCrearProyecto(contUserCrearProyecto);
         
        this.contUserCrearColectivo = controlador.getUserCrearColectivo();
        inicioUser.setControlCrearColectivo(contUserCrearColectivo);

        this.contInformes = controlador.getInformes();
        inicioUser.setControlSolicitarInformes(contInformes);     
        
        this.contFiltrar = controlador.getFiltrar();
        inicioUser.setControlFiltrar(contFiltrar);

        this.contLimpiar = controlador.getLimpiarFiltro();
        inicioUser.setControlLimpiarFiltro(contLimpiar);
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
    
    /**
     * Establece los controladores del panel de configuracion de administrador
     * 
     * @param controlador objeto controlador general
     */
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
    
    /**
     * Establece los controladores del panel de usuarios de adminstradoe
     * 
     * @param controlador objeto controlador general
     */
    private void controladorAdminUsuarios(Controlador controlador) {
    	this.contAdminConfig = controlador.getAdminConfig();
    	adminUsuarios.setControlAdminConfig(contAdminConfig);
    	
    	this.contAdminProyectos = controlador.getAdminProyectos();
    	adminUsuarios.setControlAdminProyectos(contAdminProyectos);
    	
    	this.contAdminUsuariosBloq = controlador.getAdminUsuariosBloq();
    	adminUsuarios.setControlAdminBloq(contAdminUsuariosBloq);
    	
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
     * Establece los controladors del perfil
     * 
     * @param controlador objeto controlador general
     */
    private void controladorPerfil(Controlador controlador){
        this.contUserDisplayCollective = controlador.getUserDisplayCollective();
        perfil.setControlCollective((ListSelectionListener)contUserDisplayCollective);
        inicioUser.setControlCollective((ListSelectionListener)contUserDisplayCollective);

        this.contUserDisplayProject = controlador.getUserDisplayProject();
        perfil.setControlProject((ListSelectionListener)contUserDisplayProject);
        inicioUser.setControlProject((ListSelectionListener)contUserDisplayProject);
 
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

        this.contFinanciar = controlador.getFinanciar();
        displayProject.setControlFinanciar(contFinanciar);
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

    /**
     * Establece los controladores del panel informes
     * 
     * @param controlador objeto controlador general
     */
    private void controladorInformes(Controlador controlador) {
        this.contUpdateCol = controlador.getUpdateColectivos();
        informes.setControlSelectColectivo(contUpdateCol);

    }


}
