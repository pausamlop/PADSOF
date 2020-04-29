package across.gui.user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.TableRowSorter;

import across.gui.EditFont;
import across.gui.admin.TablaUsuarios;
import across.gui.user.tablasPerfil.TablaProyectosApoyados;
import across.model.application.Application;
import across.model.user.User;

/**
 * Clase PanelPerfil
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelPerfil extends HomeUser{
	
    ButtonGroup buttonGroup;
	JRadioButton buttonPCreados = new JRadioButton("Proyectos creados");
	JRadioButton buttonPApoyados = new JRadioButton("Proyectos apoyados");
	JRadioButton buttonPSeguidos = new JRadioButton("Proyectos seguidos");
	JRadioButton buttonCCreados = new JRadioButton("Colectivos creados");
	JRadioButton buttonCMiembro = new JRadioButton("<html>Colectivos a los que <br />pertenezco</html>");
	
    private JLabel title = new JLabel("Mi Perfil");
    private JLabel nombre = new JLabel();

	JTable pCreados;
	JTable pApoyados;
	JTable pSeguidos;
	JTable cCreados;
	JTable cMiembro;
	
	JScrollPane table1;
	
	TableRowSorter trsfiltro;

    public PanelPerfil(){
    	super();
		this.setLayout(spring);
        
        toPerfil.setVisible(false);
        

        
        JPanel options = new JPanel(new GridLayout(20,10, 0, 10));
        
        
        
        
        setTables();

        
        setControlPCreados();
        setControlPApoyados();
        setControlPSeguidos();
        setControlCCreados();
        setControlCMiembro();
	    
	    
	    
	    
        buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonPCreados);
        buttonGroup.add(buttonPApoyados);
        buttonGroup.add(buttonPSeguidos);
        buttonGroup.add(buttonCCreados);
        buttonGroup.add(buttonCMiembro);
        
        options.add(buttonPCreados);
        options.add(buttonPApoyados);
        options.add(buttonPSeguidos);
        options.add(buttonCCreados);
        options.add(buttonCMiembro);
        
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, -220, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, title, -130, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, options, -220, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, options, 400, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, -220, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, -95, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, table1, 90, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, table1, 10, SpringLayout.VERTICAL_CENTER, this);
        
        
	    EditFont.setSize(title, 24);
        EditFont.bold(title);
        
	    EditFont.setSize(nombre, 16);
        EditFont.bold(title);
        
        this.add(options);
        this.add(title);
        this.add(nombre);
        
        this.add(table1);
        
    }
    
    
    public void setTables() {
    	
        /* TABLA DE PROYECTOS APOYADOS */
        pApoyados = new JTable(new TablaProyectosApoyados(this));
        pApoyados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pApoyados.getColumnModel().getColumn(0).setPreferredWidth(180);
        pApoyados.getColumnModel().getColumn(1).setPreferredWidth(150);
        pApoyados.setFillsViewportHeight(true);
        pApoyados.setOpaque(false);
        trsfiltro = new TableRowSorter(pApoyados.getModel());
 		pApoyados.setRowSorter(trsfiltro);
	    table1 = new JScrollPane(pApoyados);
	    table1.setOpaque(false);
        table1.setVisible(false);
    	
    }
    
    /**
     * Actualiza el panel
     */
    public void updateData(){
    	nombre.setText(Application.getApplication().getCurrentUser().getUsername()); 
    }
    
    
    
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlPCreados(){
    	buttonPCreados.addActionListener(e -> table1.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlPApoyados(){
    	buttonPApoyados.addActionListener(e -> table1.setVisible(true));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlPSeguidos(){
    	buttonPSeguidos.addActionListener(e -> table1.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlCCreados(){
    	buttonCCreados.addActionListener(e -> table1.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlCMiembro(){
    	buttonCMiembro.addActionListener(e -> table1.setVisible(false));
    }

}