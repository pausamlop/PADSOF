package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.gui.admin.PanelAdminConfig;
import across.gui.admin.PanelInicioAdmin;
import across.model.application.Application;

public class ControladorAdminProyectosGuardar implements ActionListener{
	private PanelInicioAdmin inicioAdmin;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminProyectosGuardar(MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicioAdmin = frame.getInicioAdmin();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'usuarios' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	
    	frame.getInicioAdmin().setProyectosButton();
        this.frame.showPanel("inicioAdmin");
    }
}
