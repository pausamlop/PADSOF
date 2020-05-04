package across.gui.user;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import across.gui.EditFont;
import across.gui.user.tablas.*;
import across.model.project.Project;
import across.model.user.Collective;

/**
 * Clase PanelInformes
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInformes extends HomeUser{

    private JLabel titulo = new JLabel("Informes");
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton popularidad = new JRadioButton("Informe de popularidad");
    private JRadioButton afinidad = new JRadioButton("Informe de afinidad");

    private JComboBox<Collective> misColectivos = new JComboBox<>();

    private JTable proyectos;
    private JTable colectivos;
    private JScrollPane scrollCol;
    private JScrollPane scrollProy;

    /**
     * Contructor del panel de socilicitar informes del usuario
     * 
     */
    public PanelInformes(){

        group.add(popularidad);
        group.add(afinidad);
        group.setSelected(popularidad.getModel(), true);
        misColectivos.setVisible(false);

        /* crear tablas */
		AbstractTableModel modelProy = new TablaProyectos();
		proyectos = new JTable(modelProy);
        scrollProy = new JScrollPane(proyectos);
        AbstractTableModel modelCol = new TablaAfinidad();
		colectivos = new JTable(modelCol);
        scrollCol = new JScrollPane(colectivos);
        
        /* configuracion de las tablas */
        proyectos.setPreferredScrollableViewportSize(new Dimension(400, 260));
        proyectos.getColumnModel().getColumn(0).setPreferredWidth(210);
		proyectos.setFillsViewportHeight(true);
		proyectos.setOpaque(false);
        scrollProy.setVisible(true);
        
        colectivos.setPreferredScrollableViewportSize(new Dimension(400, 240));
        colectivos.getColumnModel().getColumn(0).setPreferredWidth(300);
		colectivos.setFillsViewportHeight(true);
		colectivos.setOpaque(false);
		scrollCol.setVisible(false);

        /* fuentes */
        EditFont.setSize(titulo, 24);
        EditFont.bold(titulo);

        setControlRadioButtons();
        setColocacion();

        this.add(titulo);
        this.add(popularidad);
        this.add(afinidad);
        this.add(misColectivos);
        this.add(scrollCol);
        this.add(scrollProy);

    }
    
    /**
     * Coloca todos los elementos de este panel
     * 
	 */
    private void setColocacion() {
        /* horizontal */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.EAST, popularidad, -10, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, afinidad, 10, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, misColectivos, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollCol, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollProy, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        /* vertical */
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, scrollProy, 45, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, scrollCol,60, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, misColectivos, -10, SpringLayout.NORTH, scrollCol);
        spring.putConstraint(SpringLayout.SOUTH, popularidad, -10, SpringLayout.NORTH, misColectivos);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, afinidad, 0, SpringLayout.VERTICAL_CENTER, popularidad);
        spring.putConstraint(SpringLayout.SOUTH, titulo, -15, SpringLayout.NORTH, afinidad);
    }
    
    /**
     * Establece que ocurre en el panel si se selecciona afinidad o popularidad
     * 
	 */
    private void setControlRadioButtons(){
        afinidad.addActionListener(e -> {
            misColectivos.setVisible(true);
            scrollProy.setVisible(false);
			scrollCol.setVisible(true);
		});
		popularidad.addActionListener(e -> {
            misColectivos.setVisible(false);
            scrollCol.setVisible(false);
			scrollProy.setVisible(true);
		});
    }
    
    /**
     * Devuelve el colectivo seleccionado del combo box
     * @return colectivo
     */
    public Collective getColectivo(){
        return (Collective)misColectivos.getSelectedItem();
    }

    /**
     * Actualiza la lista desplegable con los colectivos a los que pertenece el usuario actual
     * 
     * @param cols colectivos a los que pertenece el usuario
     */
    public void setMisColectivos(ArrayList<Collective> cols){
        misColectivos.removeAllItems();
        for (Collective c: cols)
            misColectivos.addItem(c);
    }

    /**
     * Actualiza las tablas de este panel
     * 
     * @param p proyectos
     */
    public void updateProjects(ArrayList<Project> p){
        ((TablaProyectos)proyectos.getModel()).setPP(p);
    }

    /**
     * Actualiza las tabla de afinidad de este panel
     * 
     * @param c colectivos con su afinidad
     */
    public void updateCollectives(Map<Collective,Double> c){
        ((TablaAfinidad)colectivos.getModel()).setAfinity(c);
    }

    /**
     * Vacia la seleccion de colectivo del ComboBox
     * 
     */
    public void empty(){
        misColectivos.setSelectedItem(null);
    }

    /**
     * Establece el control cuando se selecciona un colectivo
     * 
     * @param e accion que activa el boton
     */
    public void setControlSelectColectivo(ActionListener e){
        misColectivos.addActionListener(e);
    }
    
}