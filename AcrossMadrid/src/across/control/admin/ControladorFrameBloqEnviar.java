package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import across.gui.MainFrame;
import across.gui.admin.FrameAdminUsuariosBloq;
import across.gui.admin.PanelAdminUsuarios;
import across.model.application.Application;
import across.model.user.User;

/**
 * Clase ControladorFrameBloqEnviar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorFrameBloqEnviar implements ActionListener{
	private FrameAdminUsuariosBloq frameBloq;
    private MainFrame frame;
    private Application model;
    private int row,col;

    /**
     * Constructor de la clase ControladorFrameEnviar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorFrameBloqEnviar (MainFrame frame, Application model, int row, int col){
        this.model = model;
        this.frame = frame;
        this.frameBloq = frame.getFrameAdminUsuariosBloq();
        this.row = row;
        this.col = col;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'enviar' del frame de bloquear muy usuario
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	User userToBloq = frameBloq.getUserToBloq();
    	String mensaje = frameBloq.getMensajeBloq();
    	
    	userToBloq.block(mensaje);
    	
    	Map<String, Boolean> changesUsuarios = frame.getAdminUsuarios().getApplicationUpdate();
		for(User aux: model.getNonValidatedUsers()) {
			String uname = aux.getUsername();
			
			if(changesUsuarios.containsKey(uname)) {
				if(changesUsuarios.get(uname)) {
					aux.validate();
				}
			}
		}
		
		frame.updateAdminUsuarios(new PanelAdminUsuarios());
		this.frame.getAdminUsuarios().updateTable(model.getNonValidatedUsers(), model.getUsers());
    	
    	
    	this.frame.showPanel("adminUsuarios");
    	
    	frameBloq.dispose();
    }
    
}
