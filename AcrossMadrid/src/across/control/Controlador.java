package across.control;

import across.model.application.Application;
import across.gui.MainFrame;
import across.control.start.*;
import across.control.user.*;
import across.control.admin.*;

public class Controlador {

    private ControladorInicioRegistro inicioRegistro;
    private ControladorInicioLogin inicioLogin;
    private ControladorLogin login;
    private ControladorRegistro reg;

    private ControladorUserCrearProyecto userCrearProyecto;

    private ControladorNewProject nuevoProyecto;
    private ControladorLoadImage cargarImagen;

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
        reg = new ControladorRegistro(frame, model);

        userCrearProyecto = new ControladorUserCrearProyecto(frame, model);

        nuevoProyecto = new ControladorNewProject(frame, model);
        cargarImagen = new ControladorLoadImage(frame, model);
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
    
    public ControladorRegistro getRegistro(){
        return this.reg;
    }

    public ControladorNewProject getNuevoProyecto(){
        return nuevoProyecto;
    }

    public ControladorLoadImage getCargarImagen(){
        return cargarImagen;
    }

    public ControladorUserCrearProyecto getUserCrearProyecto(){
        return userCrearProyecto;
    }
    
    

}