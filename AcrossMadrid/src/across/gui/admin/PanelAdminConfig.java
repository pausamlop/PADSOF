package across.gui.admin;

import java.awt.event.ActionListener;
import javax.swing.*;
import across.gui.EditFont;
import across.gui.menu.HomeAdmin;
import across.model.application.Application;



/**
 * Clase PanelInicioAdmin de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelAdminConfig extends HomeAdmin{
    	
    	JRadioButton proyectos = new JRadioButton("Proyectos");
    	JRadioButton usuarios = new JRadioButton("Usuarios");
    	JRadioButton config = new JRadioButton("Configuracion");
    	
    	JLabel caducidad = new JLabel("N� de dias para que un proyecto caduque: ");
    	JTextField tiempo = new JTextField(((Integer)(Application.getApplication().getDaysExpiration())).toString(), 5);
    	JButton buttonTiempo = new JButton("Aplicar");
    	
    	JLabel vmin = new JLabel("Editar numero minimo de votos por proyecto: ");
    	JTextField votos = new JTextField(((Integer)(Application.getApplication().getMinVotes())).toString(), 5);
    	JButton buttonVotos = new JButton("Aplicar");
    	
    	ButtonGroup grupo = new ButtonGroup();
    	
    	JLabel adminIni = new JLabel("Administrador");
    	JLabel configPanel = new JLabel("Configuracion de la aplicacion");

    	/**
         * Consructor de la clase PanelAdminUsuarios
        */
    	public PanelAdminConfig(){
    		
    		SpringLayout layout = (SpringLayout)getLayout();
    		
    		/*Botones*/
    		grupo.add(proyectos);
    		grupo.add(usuarios);
    		grupo.add(config);
        	
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, proyectos, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarios, 0, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, usuarios, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, config, 75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, config, 5, SpringLayout.WEST, this);
    		
    		/*Configuracion de caducidad*/
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, caducidad, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, caducidad, -70, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, tiempo, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, tiempo, 0, SpringLayout.EAST, caducidad);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, buttonTiempo, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, buttonTiempo, 5, SpringLayout.EAST, tiempo);
    		
    		/*Configuracion N� de votos minimo por proyecto*/
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, vmin, -25, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, vmin, -80, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, votos, -25, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, votos, 0, SpringLayout.EAST, caducidad);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, buttonVotos, -25, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, buttonVotos, 5, SpringLayout.EAST, tiempo);
    		
    		
    		/*Titulos*/
    		EditFont.setSize(adminIni,25);
    		EditFont.setSize(configPanel,15);
    		
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, adminIni, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.NORTH, adminIni, 50, SpringLayout.NORTH, this);
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, configPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.NORTH, configPanel, 30, SpringLayout.NORTH, adminIni);
    		
    		/*Inclusiones*/
    		this.add(adminIni);
    		this.add(configPanel);
    		
    		this.add(caducidad);
    		this.add(tiempo);
    		this.add(buttonTiempo);
    		
    		this.add(vmin);
    		this.add(votos);
    		this.add(buttonVotos);
    		
    		this.add(proyectos);
    		this.add(usuarios);
    		this.add(config);
    		
    	}
        
    	public void setControlAdminUsuarios(ActionListener c){
            usuarios.addActionListener(c);
        }
    	
    	public void setControlAdminProyectos(ActionListener c){
            proyectos.addActionListener(c);
        }
    	
    	public void setControlAdminConfigVotes(ActionListener c){
            buttonVotos.addActionListener(c);
        }
    	
    	public void setControlAdminConfigCaducidad(ActionListener c){
            buttonTiempo.addActionListener(c);
        }
    	
    	public void setConfigButton() {
    		config.setSelected(true);
    	}
    	
    	public String getMinVotos() {
    		return votos.getText();
    	}
    	
    	public String getCaducidad() {
    		return tiempo.getText();
    	}
    
}