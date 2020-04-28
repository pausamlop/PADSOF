package across.gui.user;

import javax.swing.*;

import across.gui.general.UserMenu;
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
    private JButton crearColectivo = new JButton("Crear colectivo");
    
    //para probar
    private JComboBox<Project> proyectos; 
    private JButton verProyecto = new JButton("Ver proyecto");
    
    public PanelInicioUser(){
    	
    	SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
    	UserMenu.install(this);
    	
    	proyectos = new JComboBox<>(Application.getApplication().searchProject("").toArray(new Project[0]));
    	
    	/*Botones*/
    	layout.putConstraint(SpringLayout.VERTICAL_CENTER, crearColectivo, 75, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, crearColectivo, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, crearProyecto, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, crearProyecto, 5, SpringLayout.WEST, this);
		
		/*Provisional*/
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, proyectos, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, verProyecto, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, verProyecto, 10, SpringLayout.EAST, proyectos);
    	
    	this.add(crearColectivo);
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
    
    public void setControlCrearColectivo(ActionListener c) {
    	crearColectivo.addActionListener(c);
    }

	public Project getProject() {
		return (Project)proyectos.getSelectedItem();
	}
}