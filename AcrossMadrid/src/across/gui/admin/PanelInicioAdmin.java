package across.gui.admin;

import across.gui.EditFont;
import across.gui.menu.HomeAdmin;
import across.model.project.Project;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 * Clase PanelInicioAdmin de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInicioAdmin extends HomeAdmin {

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
    public PanelInicioAdmin() {
        SpringLayout layout = (SpringLayout) getLayout();

        /* Botones */
        grupo.add(proyectos);
        grupo.add(usuarios);
        grupo.add(config);
        proyectos.setSelected(true);

        /* Tablas */

        aux = new JTable(new TablaProyectos());
        aux.setPreferredScrollableViewportSize(new Dimension(450, 250));
        aux.setFillsViewportHeight(true);

        aux.setOpaque(false);

        JScrollPane table = new JScrollPane(aux);

        table.setOpaque(false);

        aux.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        /* Titulos */
        EditFont.setSize(adminIni, 25);
        EditFont.setSize(proyectsPanel, 15);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, adminIni, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, adminIni, 50, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, proyectsPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, proyectsPanel, 30, SpringLayout.NORTH, adminIni);

        /* Botones */
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, -75, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, proyectos, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarios, 0, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, usuarios, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, config, 75, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, config, 5, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.EAST, guardarCambios, 0, SpringLayout.EAST, table);
        layout.putConstraint(SpringLayout.NORTH, guardarCambios, 20, SpringLayout.SOUTH, table);

        /* Tabla */
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, table, 10, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, table, 20, SpringLayout.VERTICAL_CENTER, this);

        this.add(adminIni);
        this.add(proyectsPanel);

        this.add(proyectos);
        this.add(usuarios);
        this.add(config);

        this.add(table);

    }

    /**
     * Devuelve la tabla de los proyectos de este panel
     * 
     * @return tabla de proyectos
     */
    public JTable getTableP() {
        return aux;
    }

    /**
     * Metodo para establecer el controlador del boton "usuarios"
     * 
     * @param c controlador
     */
    public void setControlAdminUsuarios(ActionListener c) {
        usuarios.addActionListener(c);
    }

    /**
     * Metodo para establecer el controlador del boton "config"
     * 
     * @param c controlador
     */
    public void setControlAdminConfig(ActionListener c) {
        config.addActionListener(c);
    }

    /**
     * Metodo para establecer el controlador del boton "guardarCambios"
     * 
     * @param c controlador
     */
    public void setControlAdminGuardar(ActionListener c) {
        guardarCambios.addActionListener(c);
    }

    /**
     * Pone a true el JRadioButton de proyectos
     */
    public void setProyectosButton() {
        proyectos.setSelected(true);
    }

    /**
     * Devuelve la tabla del panel
     * 
     */
    public JTable getTabla() {
        return aux;
    }

    /**
     * Metodo para actualizar los datos de la tabla
     * 
     * @param proyectos array con los datos para poblar la tabla
     */
    public void updateTablaProyectos(ArrayList<Project> proyectos) {
        ((TablaProyectos) aux.getModel()).setProyectos(proyectos);
    }

    /**
     * 
     * @return mapa con los cambios acontecidos
     */
    public Map<String, Boolean> getApplicationUpdate() {
        return ((TablaProyectos) aux.getModel()).getChanges();
    }

    /**
     * Establece el control cuando se hace click en la tabla
     * 
     * @param c accion que activa el control
     */
    public void setControlProject(ListSelectionListener contUserDisplayProjects) {
        ListSelectionModel cellSelectionModel = this.getTableP().getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(contUserDisplayProjects);

    }

}