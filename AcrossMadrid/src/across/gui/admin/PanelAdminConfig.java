package across.gui.admin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

import across.gui.EditFont;
import across.gui.admin.PanelInicioAdmin.CustomTableCellEditor;



/**
 * Clase PanelInicioAdmin de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelAdminConfig extends JPanel{
    	
    	JRadioButton proyectos = new JRadioButton("Proyectos");
    	JRadioButton usuarios = new JRadioButton("Usuarios");
    	JRadioButton config = new JRadioButton("Configuracion");
    	
    	ButtonGroup grupo = new ButtonGroup();
    	
    	JLabel adminIni = new JLabel("Administrador");
    	JLabel configPanel = new JLabel("Configuracion de la aplicacion");

    	/**
         * Consructor de la clase PanelAdminUsuarios
        */
    	public PanelAdminConfig(){
    		
    		SpringLayout layout = new SpringLayout();
    		this.setLayout(layout);
    		
    		/*Botones*/
    		grupo.add(proyectos);
    		grupo.add(usuarios);
    		grupo.add(config);
        	config.setSelected(true);
        	
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, proyectos, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarios, 0, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, usuarios, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, config, 75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, config, 5, SpringLayout.WEST, this);
    		
    		/*Titulos*/
    		EditFont.setSize(adminIni,25);
    		EditFont.setSize(configPanel,15);
    		
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, adminIni, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, configPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.NORTH, configPanel, 30, SpringLayout.NORTH, adminIni);
    		
    		/*Inclusiones*/
    		this.add(adminIni);
    		this.add(configPanel);
    		
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
    
}