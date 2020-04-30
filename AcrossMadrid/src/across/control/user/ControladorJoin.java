package across.control.user;

import across.model.application.Application;
import across.model.user.Collective;
import across.model.user.User;
import across.gui.*;
import across.gui.general.PanelDisplayCollective;

import java.awt.event.*;

/**
 * Clase ControladorJoin
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorJoin implements ActionListener{
	
    private PanelDisplayCollective panel;
    private MainFrame frame;
    private Application model;
	

    
    /**
     * Constructor de la clase ControladorJoin
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorJoin (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getDisplayCollective();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'unirse' en el panel de visalizar colectivo
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        Collective c = panel.getCollective();
        User u = model.getCurrentUser();
        if (u.getMemberCollectives().contains(c))
            c.disjoin(u);
        else
            c.join(u);
        panel.update();
    }
    
    
    
}









