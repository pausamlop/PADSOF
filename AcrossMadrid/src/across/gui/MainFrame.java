package across.gui;

import across.control.*;
import across.control.start.*;
import across.control.user.*;
import across.gui.start.*;
import across.gui.user.*;
import across.gui.admin.*;
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
public class MainFrame extends JFrame{

    private PanelInicio inicio = new PanelInicio();
    private PanelRegistro registro = new PanelRegistro();
    private PanelLogin login = new PanelLogin();
    private PanelInicioUser inicioUser = new PanelInicioUser();
    private PanelInicioAdmin inicioAdmin = new PanelInicioAdmin();
    private PanelNewCollective nuevoColectivo = new PanelNewCollective();
    private PanelNewProject nuevoProyecto = new PanelNewProject();

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin contLogin;
    private ControladorRegistro contRegistro;

    private ControladorUserCrearProyecto contUserCrearProyecto;

    private ControladorNewProject contNuevoProyecto;
    private ControladorLoadImage contCargarImagen;

    private JPanel contentPane;

    public MainFrame(){
        super("Across Madrid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,700,500);

        contentPane = new JPanel(new CardLayout());
        setContentPane(contentPane);
        
        fillContentPane();
    }

    public void showPanel(String carta) {
        Application.saveData(Application.getApplication());
		CardLayout layout = (CardLayout)contentPane.getLayout();
		layout.show(contentPane, carta);
    }
    
    private void fillContentPane(){
        contentPane.add(inicio, "inicio");
        contentPane.add(registro, "registro");
        contentPane.add(login, "login");

        /* PANELES DEL USUARIO */
        contentPane.add(inicioUser, "inicioUser");
        contentPane.add(nuevoColectivo, "nuevoColectivo");
        contentPane.add(nuevoProyecto, "nuevoProyecto");

        /* PANELES DEL ADMINISTRADOR */
        contentPane.add(inicioAdmin, "inicioAdmin");

    }

    public PanelInicio getInicio(){
        return inicio;
    }

    public PanelRegistro getRegistro(){
        return registro;
    }

    public PanelLogin getLogin(){
        return login;
    }

    public PanelInicioUser getInicioUser(){
        return inicioUser;
    }

    public PanelInicioAdmin getInicioAdmin(){
        return inicioAdmin;
    }

    public PanelNewProject getNewProject(){
        return nuevoProyecto;
    }

    public void setControlador(Controlador controlador){

        controladorInicio(controlador);
        controladorRegistro(controlador);
        controladorLogin(controlador);

        controladorUserCrearProyecto(controlador);
        controladorNuevoProyecto(controlador);
        controladorCargarImagen(controlador);

    }
    
    private void controladorInicio(Controlador controlador){
        this.inicioRegistro = controlador.getInicioRegistro();
        inicio.setControlRegistro(inicioRegistro);
        this.inicioLogin = controlador.getInicioLogin();
        inicio.setControlLogin(inicioLogin);
    }

    private void controladorLogin(Controlador controlador){
        this.contLogin = controlador.getLogin();
        login.setControlContinuar(contLogin);
    }
    
    private void controladorRegistro(Controlador controlador){
        this.contRegistro = controlador.getRegistro();
        registro.setControlContinuar(contRegistro);
    }


    private void controladorUserCrearProyecto(Controlador controlador){
        this.contUserCrearProyecto = controlador.getUserCrearProyecto();
        inicioUser.setControlCrearProyecto(contUserCrearProyecto);
    }


    private void controladorNuevoProyecto(Controlador controlador){
        this.contNuevoProyecto = controlador.getNuevoProyecto();
        nuevoProyecto.setControlProponerProyecto(contNuevoProyecto);
    }

    private void controladorCargarImagen(Controlador controlador){
        this.contCargarImagen = controlador.getCargarImagen();
        nuevoProyecto.setControlFoto(contCargarImagen);
    }
}
