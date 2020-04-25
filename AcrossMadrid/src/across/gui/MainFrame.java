package across.gui;

import across.control.*;
import across.control.start.*;
import across.control.user.*;
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

    private PanelInicio inicio = new PanelInicio();
    private PanelRegistro registro = new PanelRegistro();
    private PanelLogin login = new PanelLogin();
    private PanelInicioUser inicioUser = new PanelInicioUser();
    private PanelInicioAdmin inicioAdmin = new PanelInicioAdmin();
    private PanelNewCollective nuevoColectivo = new PanelNewCollective();
    private PanelNewProject nuevoProyecto = new PanelNewProject();
    
    private PanelDisplayProject displayProject = new PanelDisplayProject();

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin contLogin;
    private ControladorRegistro contRegistro;

    private ControladorUserCrearProyecto contUserCrearProyecto;
    private ControladorUserDisplayProject contUserDisplayProject;

    private ControladorNewProject contNuevoProyecto;
    private ControladorLoadImage contCargarImagen;
    
    private ControladorNewCollective contNuevoColectivo;
    private ControladorUserCrearColectivo contUserCrearColectivo;

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
     * Establece los controlador de los diferentes paneles mediante el uso de metodos
     * auxiliares para los diferentes tipos de controladores
     * 
     * @param controlador objeto controlador general
     */
    public void setControlador(Controlador controlador){

        controladorInicio(controlador);
        controladorRegistro(controlador);
        controladorLogin(controlador);

        controladorUser(controlador);
        controladorNuevoProyecto(controlador);
        controladorCargarImagen(controlador);
        
        controladorNuevoColectivo(controlador);

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
        
        this.contUserCrearColectivo = controlador.getUserCrearColectivo();
        inicioUser.setControlCrearColectivo(contUserCrearColectivo);
        
        this.contUserDisplayProject = controlador.getUserDisplayProject();
        inicioUser.setControlVerProyecto(contUserDisplayProject);
    }

    /**
     * Establece los controladors del panel de crear proyecto
     * 
     * @param controlador objeto controlador general
     */
    private void controladorNuevoProyecto(Controlador controlador){
        this.contNuevoProyecto = controlador.getNuevoProyecto();
        nuevoProyecto.setControlProponerProyecto(contNuevoProyecto);
    }

    /**
     * Establece los controladors de cargar imagen del panel crear proyecto 
     * 
     * @param controlador objeto controlador general
     */
    private void controladorCargarImagen(Controlador controlador){
        this.contCargarImagen = controlador.getCargarImagen();
        nuevoProyecto.setControlFoto(contCargarImagen);
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
}
