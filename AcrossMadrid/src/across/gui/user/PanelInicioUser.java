package across.gui.user;

import javax.swing.*;

import across.model.application.Application;
import across.model.project.Project;

import java.awt.event.*;
import java.awt.Image;

/**
 * Clase PanelInicioUser de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInicioUser extends HomeUser{

	private JButton logout = new JButton();
    private JButton crearProyecto = new JButton("Crear proyecto");
    private JButton crearColectivo = new JButton("Crear colectivo");
    
    //para probar
    private JComboBox<Project> proyectos; 
    private JButton verProyecto = new JButton("Ver proyecto");
    
    public PanelInicioUser(){
		super();
		toInicio.setVisible(false);
		
    	//SpringLayout layout = new SpringLayout();
		//this.setLayout(layout);

		/* boton logout */
		ImageIcon icon = new ImageIcon("icons/logout.png");
        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(20,30,Image.SCALE_SMOOTH);
        logout.setIcon(new ImageIcon(scaled));
		
    	proyectos = new JComboBox<>(Application.getApplication().searchProject("").toArray(new Project[0]));
    	
		/*Botones*/
		spring.putConstraint(SpringLayout.WEST, logout, 10, SpringLayout.WEST, this);
        spring.putConstraint(SpringLayout.NORTH, logout, 10, SpringLayout.NORTH, this);
    	spring.putConstraint(SpringLayout.VERTICAL_CENTER, crearColectivo, 75, SpringLayout.VERTICAL_CENTER, this);
		spring.putConstraint(SpringLayout.WEST, crearColectivo, 5, SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, crearProyecto, 0, SpringLayout.VERTICAL_CENTER, this);
		spring.putConstraint(SpringLayout.WEST, crearProyecto, 5, SpringLayout.WEST, this);
		
		
		/*Provisional*/
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, 0, SpringLayout.VERTICAL_CENTER, this);
		spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, proyectos, 0, SpringLayout.HORIZONTAL_CENTER, this);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, verProyecto, 0, SpringLayout.VERTICAL_CENTER, this);
		spring.putConstraint(SpringLayout.WEST, verProyecto, 10, SpringLayout.EAST, proyectos);
		
		this.add(logout);		
    	this.add(crearColectivo);
    	this.add(proyectos);
    	this.add(verProyecto);
        this.add(crearProyecto);
    }
	
	public void setControlLogout(ActionListener c){
        logout.addActionListener(c);
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