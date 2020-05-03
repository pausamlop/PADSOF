package across.control.user.collective;

import across.model.application.Application;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.tablas.*;

import javax.swing.JTable;
import javax.swing.event.*;

/**
 * Clase ControladorUserDisplayProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUserDisplayCollective implements ListSelectionListener {
	
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorUserDisplayCollective
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUserDisplayCollective(MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
    }

    /**
     * Accion que se realiza cuando se hace click sobre un colectivo en una tabla
     * 
     * @param e accion recibida
     */
	@Override
	public void valueChanged(ListSelectionEvent e) {

        if (e.getValueIsAdjusting()) return;

        JTable t;
        if (frame.getPerfil().isVisible())
            t = frame.getPerfil().getTableC();
        else if (frame.getInicioUser().isVisible())
            t = frame.getInicioUser().getTableC();
        else return;

        int rows = t.getSelectedRow();
        if (rows == -1) return;

        Collective c = ((TablaColectivos)t.getModel()).getCollectives().get(rows);
        this.frame.getDisplayCollective().setCollective(c);
        this.frame.showPanel("displayCollective");
        
    }
    
}
