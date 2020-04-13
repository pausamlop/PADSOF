package across.control;

import across.model.application.Application;
import across.gui.MainFrame;

public class Controlador {

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin login;

    private MainFrame frame;
    private Application model;

    public Controlador(MainFrame frame, Application model){
        this.frame = frame;
        this.model = model;
        setupControladores();
    }

    private void setupControladores(){
        inicioRegistro = new ControladorInicioRegistro(frame, model);
        inicioLogin = new ControladorInicioLogin(frame, model);
        login = new ControladorLogin(frame, model);
    }

    public ControladorInicioRegistro getInicioRegistro(){
        return this.inicioRegistro;
    }

    public ControladorInicioLogin getInicioLogin(){
        return this.inicioLogin;
    }

    public ControladorLogin getLogin(){
        return this.login;
    }

}