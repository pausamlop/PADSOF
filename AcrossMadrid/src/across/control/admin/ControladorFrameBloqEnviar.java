package across.control.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import across.gui.MainFrame;
import across.gui.admin.FrameAdminUsuariosBloq;
import across.gui.admin.PanelAdminUsuarios;
import across.model.application.Application;
import across.model.user.User;

public class ControladorFrameBloqEnviar implements ActionListener{
	private FrameAdminUsuariosBloq frameBloq;
    private MainFrame frame;
    private Application model;
    private int row,col;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
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
     * Accion que se realiza cuando se pulsa el boton de 'usuarios' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
    	User userToBloq = frameBloq.getUserToBloq();
    	String mensaje = frameBloq.getMensajeBloq();
    	
    	userToBloq.block(mensaje);
    	
    	this.frame.updateAdminUsuarios(new PanelAdminUsuarios());
    	
    	this.frame.showPanel("adminUsuarios");
    	
    	/*frame.getAdminUsuarios().getTabla().clearSelection();
    	frame.getAdminUsuarios().updateTableCell(true,row, col);*/
    	
    	frameBloq.dispose();
    }
    
}
