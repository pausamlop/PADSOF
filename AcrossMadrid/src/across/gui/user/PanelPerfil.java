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
import across.gui.user.tablasPerfil.TablaColectivosCreados;
import across.gui.user.tablasPerfil.TablaColectivosMiembros;
import across.gui.user.tablasPerfil.TablaProyectosApoyados;
import across.gui.user.tablasPerfil.TablaProyectosCreados;
import across.gui.user.tablasPerfil.TablaProyectosSeguidos;
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
	JScrollPane table2;
	JScrollPane table3;
	JScrollPane table4;
	JScrollPane table5;
	
	TableRowSorter trsfiltro1;
	TableRowSorter trsfiltro2;
	TableRowSorter trsfiltro3;
	TableRowSorter trsfiltro4;
	TableRowSorter trsfiltro5;

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
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, table2, 90, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, table2, 10, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, table3, 90, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, table3, 10, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, table4, 90, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, table4, 10, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, table5, 90, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, table5, 10, SpringLayout.VERTICAL_CENTER, this);
        
        
	    EditFont.setSize(title, 24);
        EditFont.bold(title);
        
	    EditFont.setSize(nombre, 16);
        EditFont.bold(title);
        
        this.add(options);
        this.add(title);
        this.add(nombre);
        
        this.add(table1);
        this.add(table2);
        this.add(table3);
        this.add(table4);
        this.add(table5);
        
    }
    
    
    public void setTables() {
    	
        /* TABLA DE PROYECTOS APOYADOS */
        pApoyados = new JTable(new TablaProyectosApoyados(this));
        pApoyados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pApoyados.getColumnModel().getColumn(0).setPreferredWidth(180);
        pApoyados.getColumnModel().getColumn(1).setPreferredWidth(150);
        pApoyados.setFillsViewportHeight(true);
        pApoyados.setOpaque(false);
        trsfiltro1 = new TableRowSorter(pApoyados.getModel());
 		pApoyados.setRowSorter(trsfiltro1);
	    table1 = new JScrollPane(pApoyados);
	    table1.setOpaque(false);
        table1.setVisible(false);
        
        /* TABLA DE COLECTIVOS CREADOS */
        cCreados = new JTable(new TablaColectivosCreados(this));
        cCreados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        cCreados.getColumnModel().getColumn(0).setPreferredWidth(200);
        cCreados.setFillsViewportHeight(true);
        cCreados.setOpaque(false);
        trsfiltro2 = new TableRowSorter(cCreados.getModel());
        cCreados.setRowSorter(trsfiltro2);
	    table2 = new JScrollPane(cCreados);
	    table2.setOpaque(false);
        table2.setVisible(false);
        
        /* TABLA DE COLECTIVOS CREADOS */
        cMiembro = new JTable(new TablaColectivosMiembros(this));
        cMiembro.setPreferredScrollableViewportSize(new Dimension(400, 280));
        cMiembro.setFillsViewportHeight(true);
        cMiembro.setOpaque(false);
        trsfiltro3 = new TableRowSorter(cMiembro.getModel());
        cMiembro.setRowSorter(trsfiltro3);
	    table3 = new JScrollPane(cMiembro);
	    table3.setOpaque(false);
        table3.setVisible(false);
        
        /* TABLA DE PROYECTOS SEGUIDOS */
        pSeguidos = new JTable(new TablaProyectosSeguidos(this));
        pSeguidos.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pSeguidos.setFillsViewportHeight(true);
        pSeguidos.setOpaque(false);
        trsfiltro4 = new TableRowSorter(pSeguidos.getModel());
        pSeguidos.setRowSorter(trsfiltro4);
	    table4 = new JScrollPane(pSeguidos);
	    table4.setOpaque(false);
        table4.setVisible(false);
        
        /* TABLA DE PROYECTOS CREADOS */
        pCreados = new JTable(new TablaProyectosCreados(this));
        pCreados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pCreados.getColumnModel().getColumn(3).setPreferredWidth(30);
        pCreados.getColumnModel().getColumn(2).setPreferredWidth(5);
        pCreados.getColumnModel().getColumn(1).setPreferredWidth(30);
        pCreados.setFillsViewportHeight(true);
        pCreados.setOpaque(false);
        trsfiltro5 = new TableRowSorter(pCreados.getModel());
        pCreados.setRowSorter(trsfiltro3);
	    table5 = new JScrollPane(pCreados);
	    table5.setOpaque(false);
        table5.setVisible(false);
    	
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
    	buttonPCreados.addActionListener(e -> table2.setVisible(false));
    	buttonPCreados.addActionListener(e -> table3.setVisible(false));
    	buttonPCreados.addActionListener(e -> table4.setVisible(false));
    	buttonPCreados.addActionListener(e -> table5.setVisible(true));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlPApoyados(){
    	buttonPApoyados.addActionListener(e -> table1.setVisible(true));
    	buttonPApoyados.addActionListener(e -> table2.setVisible(false));
    	buttonPApoyados.addActionListener(e -> table3.setVisible(false));
    	buttonPApoyados.addActionListener(e -> table4.setVisible(false));
    	buttonPApoyados.addActionListener(e -> table5.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlPSeguidos(){
    	buttonPSeguidos.addActionListener(e -> table1.setVisible(false));
    	buttonPSeguidos.addActionListener(e -> table2.setVisible(false));
    	buttonPSeguidos.addActionListener(e -> table3.setVisible(false));
    	buttonPSeguidos.addActionListener(e -> table4.setVisible(true));
    	buttonPSeguidos.addActionListener(e -> table5.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlCCreados(){
    	buttonCCreados.addActionListener(e -> table1.setVisible(false));
    	buttonCCreados.addActionListener(e -> table2.setVisible(true));
    	buttonCCreados.addActionListener(e -> table3.setVisible(false));
    	buttonCCreados.addActionListener(e -> table4.setVisible(false));
    	buttonCCreados.addActionListener(e -> table5.setVisible(false));
    }
    
    /**
     * Establece el control de la seleccion del RadioButton
     */
    public void setControlCMiembro(){
    	buttonCMiembro.addActionListener(e -> table1.setVisible(false));
    	buttonCMiembro.addActionListener(e -> table2.setVisible(false));
    	buttonCMiembro.addActionListener(e -> table3.setVisible(true));
    	buttonCMiembro.addActionListener(e -> table4.setVisible(false));
    	buttonCMiembro.addActionListener(e -> table5.setVisible(false));
    }

}