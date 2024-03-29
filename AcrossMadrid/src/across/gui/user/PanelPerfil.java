package across.gui.user;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionListener;

import across.gui.EditFont;
import across.gui.user.tablas.*;

import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;

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
	
	TablaColectivos t4;
	TablaColectivos t5;
	TablaProyectos t3;
	TablaProyectos t1;
	TablaProyectos t2;
	
	JScrollPane table1;
	JScrollPane table2;
	JScrollPane table3;
	JScrollPane table4;
	JScrollPane table5;
	
    /**
     * Constructor de la clase PanelPerfil
     */
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
    
    
    /**
     * crea las distintas tablas
     */
    public void setTables() {
    	
        /* TABLA DE PROYECTOS APOYADOS */
        pApoyados = new JTable(new TablaProyectos());
        pApoyados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pApoyados.setFillsViewportHeight(true);
        pApoyados.setOpaque(false);
	    table1 = new JScrollPane(pApoyados);
	    table1.setOpaque(false);
        table1.setVisible(false);
        
        /* TABLA DE COLECTIVOS CREADOS */
        cCreados = new JTable(new TablaColectivos());
        cCreados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        cCreados.setFillsViewportHeight(true);
        cCreados.setOpaque(false);
	    table2 = new JScrollPane(cCreados);
	    table2.setOpaque(false);
        table2.setVisible(false);
        
        /* TABLA DE COLECTIVOS MIEMBROS */
        cMiembro = new JTable(new TablaColectivos());
        cMiembro.setPreferredScrollableViewportSize(new Dimension(400, 280));
        cMiembro.setFillsViewportHeight(true);
        cMiembro.setOpaque(false);
	    table3 = new JScrollPane(cMiembro);
	    table3.setOpaque(false);
        table3.setVisible(false);
        
        /* TABLA DE PROYECTOS SEGUIDOS */
        pSeguidos = new JTable(new TablaProyectos());
        pSeguidos.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pSeguidos.setFillsViewportHeight(true);
        pSeguidos.setOpaque(false);
	    table4 = new JScrollPane(pSeguidos);
	    table4.setOpaque(false);
        table4.setVisible(false);
        
        /* TABLA DE PROYECTOS CREADOS */
        pCreados = new JTable(new TablaProyectos());
        pCreados.setPreferredScrollableViewportSize(new Dimension(400, 280));
        pCreados.setFillsViewportHeight(true);
        pCreados.setOpaque(false);
	    table5 = new JScrollPane(pCreados);
	    table5.setOpaque(false);
        table5.setVisible(false);
    	
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

    /**
     * Hace update de los datos
     * @param pc proyectos creados
     * @param pa proyectos apoyados
     * @param ps proyectos seguidos
     * @param cm colectivos miembro
     * @param cc colectivos creados
     */
	public void updateData(ArrayList<Project> pc, ArrayList<Project> pa, ArrayList<Project> ps,
			ArrayList<Collective> cm, ArrayList<Collective> cc) {
    	nombre.setText(Application.getApplication().getCurrentUser().getUsername()); 
      
        ((TablaProyectos)pCreados.getModel()).setPP(pc);
        ((TablaProyectos)pApoyados.getModel()).setPP(pa);
        ((TablaProyectos)pSeguidos.getModel()).setPP(ps);
        ((TablaColectivos)cMiembro.getModel()).setCC(cm);
        ((TablaColectivos)cCreados.getModel()).setCC(cc);
	}
    
    /**
     * Devuelve la tabla de proyectos visible
     * @return tabla, null si no hay ninguna 
     */
	public JTable getTableP() {
        if (table1.isVisible()) return pApoyados;
        else if (table4.isVisible()) return pSeguidos;
        else if (table5.isVisible()) return pCreados;
        else return null;
	}

	/**
     * Devuelve la tabla de colectivos visible
     * @return tabla, null si no hay ninguna 
     */
	public JTable getTableC() {
        if (table2.isVisible()) return cCreados;
        if (table3.isVisible()) return cMiembro;
        else return null;
	}
	
	/**
     * Gestionar el display de colectivos
     * @param contUserDisplayCollective controlador
     */
	public void setControlCollective(ListSelectionListener contUserDisplayCollective){
        ListSelectionModel cellSelectionModelCC = cCreados.getSelectionModel();
        ListSelectionModel cellSelectionModelCM = cMiembro.getSelectionModel();
        cellSelectionModelCC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModelCM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModelCC.addListSelectionListener(contUserDisplayCollective);
        cellSelectionModelCM.addListSelectionListener(contUserDisplayCollective);

    }

    /**
     * Gestionar el display de proyectos
     * @param contUserDisplayProjects controlador
     */
    public void setControlProject(ListSelectionListener contUserDisplayProjects){
        ListSelectionModel cellSelectionModelPC = pCreados.getSelectionModel();
        ListSelectionModel cellSelectionModelPA = pApoyados.getSelectionModel();
        ListSelectionModel cellSelectionModelPS = pSeguidos.getSelectionModel();
        cellSelectionModelPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModelPA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModelPS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModelPC.addListSelectionListener(contUserDisplayProjects);
        cellSelectionModelPA.addListSelectionListener(contUserDisplayProjects);
        cellSelectionModelPS.addListSelectionListener(contUserDisplayProjects);
    }

}