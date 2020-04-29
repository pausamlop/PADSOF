package across.control.user;

import across.model.application.Application;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.PanelNewCollective;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ControladorNewCollective implements ActionListener{
	
    private PanelNewCollective panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorNewCollective
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorNewCollective (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNewCollective();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        String name = panel.getName().trim();
        String desc = panel.getDescription().trim();
        
        /* Comprobacion general */
        if (name.equals(""))
            JOptionPane.showMessageDialog(frame, "Debe introducir un nombre para el colectivo", "Aviso", JOptionPane.WARNING_MESSAGE);
        else if (desc.equals(""))
            JOptionPane.showMessageDialog(frame, "Debe introducir una descripcion para el colectivo", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        else if (panel.isIndependent()) {
        	
            model.addCollectives(new Collective(name, desc));
            JOptionPane.showMessageDialog(frame, "El colectivo ha sido creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            panel.emptyFields();
            frame.showPanel("inicioUser");
        	
        }
        
        else if (panel.isChild()) {
        	
        	Collective parent = panel.getColectivo();
            model.addCollectives(new Collective(name, desc, parent));
            JOptionPane.showMessageDialog(frame, "El colectivo ha sido creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            panel.emptyFields();
            frame.showPanel("inicioUser");
        	
        }
        
        else
            JOptionPane.showMessageDialog(frame, "Debe decidir si el colectivo es independiente, o escoger un colectivo padre", "Aviso", JOptionPane.WARNING_MESSAGE);
        
		
	}
	
	

}