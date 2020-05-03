package across.control.user.collective;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.PanelNewCollective;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        if (name.equals("")){
            JOptionPane.showMessageDialog(frame, "Debe introducir un nombre para el colectivo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if(!model.validNameColective(name)){
            JOptionPane.showMessageDialog(frame, "Ya existe un colectivo con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if (desc.equals("")){
            JOptionPane.showMessageDialog(frame, "Debe introducir una descripcion para el colectivo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        else if (panel.isIndependent()) {
            model.addCollectives(new Collective(name, desc));
            JOptionPane.showMessageDialog(frame, "El colectivo ha sido creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            panel.emptyFields();
        }
        else if (panel.isChild()) {
            Collective parent = panel.getColectivo();
            if (parent == null){
                JOptionPane.showMessageDialog(frame, "No existe un colectivo padre al que unirse", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.addCollectives(new Collective(name, desc, parent));
            JOptionPane.showMessageDialog(frame, "El colectivo ha sido creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            panel.emptyFields();
        }
        else{
            JOptionPane.showMessageDialog(frame, "Debe decidir si el colectivo es independiente, o escoger un colectivo padre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ArrayList<Project> proyectos = model.getProjects();
        ArrayList<Collective> colectivos = model.getCollectives();
        frame.getInicioUser().updateData(proyectos, colectivos);
        frame.showPanel("inicioUser");
		
	}
	
	

}