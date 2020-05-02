package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.gui.admin.FrameAdminUsuariosBloq;
import across.model.application.Application;
import across.model.user.User;

public class ControladorFrameBloqEnviar implements ActionListener{
	private FrameAdminUsuariosBloq frameBloq;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorFrameBloqEnviar (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.frameBloq = frame.getFrameAdminUsuariosBloq();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'usuarios' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	User userToBloq = frameBloq.getUserToBloq();
    	String mensaje = frameBloq.getMensajeBloq();
    	
    	userToBloq.block(mensaje);
    	
    	    	
    	this.frame.showPanel("adminUsuarios");
    	
    	frameBloq.dispose();
    }
    
}
