package across.gui.user;

import javax.swing.*;

import across.model.application.Application;
import across.model.project.Project;

import java.awt.event.*;

/**
 * Clase PanelInicioUser de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInicioUser extends JPanel{

    private JButton crearProyecto = new JButton("Crear proyecto");
    
    //para probar
    private JComboBox<Project> proyectos; 
    private JButton verProyecto = new JButton("Ver proyecto");
    
    public PanelInicioUser(){
    	proyectos = new JComboBox<>(Application.getApplication().searchProject("").toArray(new Project[0]));
    	this.add(proyectos);
    	this.add(verProyecto);
    	
        this.add(crearProyecto);
        
    }
    
    public void setControlCrearProyecto(ActionListener c){
        crearProyecto.addActionListener(c);
    }
    
    public void setControlVerProyecto(ActionListener c){
        verProyecto.addActionListener(c);
    }

	public Project getProject() {
		return (Project)proyectos.getSelectedItem();
	}
}