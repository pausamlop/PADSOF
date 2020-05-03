package across.gui.admin;

import across.gui.EditFont;
import across.gui.menu.HomeAdmin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;


/**
 * Clase PanelInicioAdmin de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInicioAdmin extends HomeAdmin{
		
		private JButton logout = new JButton();
    	JRadioButton proyectos = new JRadioButton("Proyectos");
    	JRadioButton usuarios = new JRadioButton("Usuarios");
    	JRadioButton config = new JRadioButton("Configuracion");
    	
    	JTable aux;
    	
    	ButtonGroup grupo = new ButtonGroup();
    	
    	JButton guardarCambios = new JButton("Guardar"); 
    	
    	JLabel adminIni = new JLabel("Administrador");
    	JLabel proyectsPanel = new JLabel("Proyectos");
    	
    	
    	

        
    	/**
         * Consructor de la clase PanelInicioAdmin
        */
    	public PanelInicioAdmin(){
    		SpringLayout layout = (SpringLayout)getLayout();
			
			/* boton logout */
			// ImageIcon icon = new ImageIcon("icons/logout.png");
			// Image img = icon.getImage();
			// Image scaled = img.getScaledInstance(20,30,Image.SCALE_SMOOTH);
			// logout.setIcon(new ImageIcon(scaled));
			
    		/*Botones*/
    		grupo.add(proyectos);
    		grupo.add(usuarios);
    		grupo.add(config);
        	proyectos.setSelected(true);
        
    		/*Tablas*/
        	
            aux = new JTable(new TablaProyectos());
            aux.setPreferredScrollableViewportSize(new Dimension(450, 250));
            aux.setFillsViewportHeight(true);
            
            aux.setOpaque(false);
            //aux.setShowGrid(false);
            //((DefaultTableCellRenderer)aux.getDefaultRenderer(Object.class)).setOpaque(false);

            JScrollPane table = new JScrollPane(aux);
            
            table.setOpaque(false);
            //table.getViewport().setOpaque(false);
            
            aux.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
    		
    		/*Titulos*/
    		EditFont.setSize(adminIni,25);
    		EditFont.setSize(proyectsPanel,15);
			
			// layout.putConstraint(SpringLayout.WEST, logout, 10, SpringLayout.WEST, this);
			// layout.putConstraint(SpringLayout.NORTH, logout, 10, SpringLayout.NORTH, this);

    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, adminIni, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.NORTH, adminIni, 50, SpringLayout.NORTH, this);
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, proyectsPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.NORTH, proyectsPanel, 30, SpringLayout.NORTH, adminIni);
    		
    		/*Botones*/
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, -75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, proyectos, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarios, 0, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, usuarios, 5, SpringLayout.WEST, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, config, 75, SpringLayout.VERTICAL_CENTER, this);
    		layout.putConstraint(SpringLayout.WEST, config, 5, SpringLayout.WEST, this);
    		
    		layout.putConstraint(SpringLayout.EAST, guardarCambios, 0, SpringLayout.EAST, table);
    		layout.putConstraint(SpringLayout.NORTH, guardarCambios, 20, SpringLayout.SOUTH, table);
    		
    		/*Tabla*/
    		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, table, 10, SpringLayout.HORIZONTAL_CENTER, this);
    		layout.putConstraint(SpringLayout.VERTICAL_CENTER, table, 20, SpringLayout.VERTICAL_CENTER, this);

			//this.add(logout);

    		this.add(adminIni);
    		this.add(proyectsPanel);
    		
    		this.add(proyectos);
    		this.add(usuarios);
    		this.add(config);
    		//this.add(guardarCambios);
    		
    		this.add(table);
    		
    	}
    
    /**
     * Implementacion para permitir definir el comportamiento de algunas de las celdas en concreto dentro de una misma columna de una tabla	
     * 
     */
   /* public static class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        private TableCellEditor editor;

        @Override
        public Object getCellEditorValue() {
            if (editor != null) {
                return editor.getCellEditorValue();
            }

            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (((String)value).equals("Validar/Rechazar")) {
            	JComboBox<String> comboBox = new JComboBox<>();
            	
        		comboBox.addItem("Validar");
        		comboBox.addItem("Rechazar");
        		comboBox.addItem("Validar/Rechazar");
        		
                editor = new DefaultCellEditor(comboBox);
            }

            return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
	}*/
    
    public void setControlAdminUsuarios(ActionListener c){
        usuarios.addActionListener(c);
    }
    
    public void setControlAdminConfig(ActionListener c){
        config.addActionListener(c);
    }
    
    public void setControlAdminGuardar(ActionListener c){
        guardarCambios.addActionListener(c);
    }
    
    public void setProyectosButton() {
    	proyectos.setSelected(true);
    }
    
    public JTable getTabla() {
    	return aux;
    }
    	
    
}