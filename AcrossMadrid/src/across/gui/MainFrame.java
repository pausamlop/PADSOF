package across.gui;

import across.control.*;

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

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin contLogin;

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
		CardLayout layout = (CardLayout)contentPane.getLayout();
		layout.show(contentPane, carta);
    }
    
    private void fillContentPane(){
        contentPane.add(inicio, "inicio");
        contentPane.add(registro, "registro");
        contentPane.add(login, "login");

        /* PANELES DEL USUARIO */
        contentPane.add(inicioUser, "inicioUser");

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

    public void setControlador(Controlador controlador){

        controladorInicio(controlador);
        //controladorRegistro(controlador);
        controladorLogin(controlador);

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
}