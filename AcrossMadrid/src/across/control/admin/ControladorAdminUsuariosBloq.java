package across.control.admin;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import across.gui.MainFrame;
import across.gui.admin.FrameAdminUsuariosBloq;
import across.gui.admin.PanelAdminUsuarios;
import across.model.application.Application;
import across.model.user.User;

public class ControladorAdminUsuariosBloq implements ListSelectionListener{
	private PanelAdminUsuarios adminUsuarios;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserCrearProyecto
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorAdminUsuariosBloq (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'usuarios' de la pantalla principal del Admin
     * 
     * @param e accion recibida
     */
    @Override
    public void valueChanged(ListSelectionEvent e){
        this.adminUsuarios = frame.getAdminUsuarios();
        
    	JTable aux = adminUsuarios.getTabla();
    	
    	int selectedRow = aux.getSelectedRow();
        int selectedColumn = aux.getSelectedColumn();
        
        if(selectedColumn == 3  && selectedRow >= 0 && !((Boolean)adminUsuarios.getTablaUsuarios().getValueAt(selectedRow, selectedColumn))) {
        	
        	User userToBloq = model.getUserByName((String)aux.getValueAt(selectedRow, 0));
        	
        	final FrameAdminUsuariosBloq frameBloq = new FrameAdminUsuariosBloq(userToBloq, this.frame, "Across Madrid", true);
        	

        	frameBloq.setBounds(200,200,500,300);
        	frameBloq.setResizable(false);
        	frame.setFrameAdminUsuariosBloq(frameBloq);
        	frameBloq.setControlFrameBloqEnviar(new ControladorFrameBloqEnviar(frame, model, selectedRow, selectedColumn));
        	frameBloq.setControlFrameBloqCancelar(new ControladorFrameBloqCancelar(frame, model));
        	
        	frameBloq.setVisible(true);
        	
        }else {
        	if(selectedRow>=0) {
        		(model.getUserByName((String)aux.getValueAt(selectedRow, 0))).unblock();
            	aux.clearSelection();
        	}
        	frame.showPanel("adminUsuarios");
        }
    	
    }
    
}
