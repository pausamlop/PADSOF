package across.gui.user;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import across.gui.user.tablas.TablaColectivos;
import across.gui.user.tablas.TablaProyectos;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;
import across.model.user.Collective;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Dimension;
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
	private JButton informes = new JButton("Solicitar informes");
    private JButton crearProyecto = new JButton("  Crear proyecto  ");
	private JButton crearColectivo = new JButton(" Crear colectivo  ");
	
	/* filtro */
	private JLabel filtrarLabel = new JLabel("Filtrar:");
	private JComboBox<String> tipoEstado;
	private JComboBox<String> ambito;
	private JComboBox<String> distrito;
	private JComboBox<projectState> estado;
	private JButton filtrar = new JButton("Filtrar proyecto");
	private JButton limpiar = new JButton("<html>Limpiar filtro/<br>busqueda</html>");

	/* buscador */
	private JLabel buscarLabel = new JLabel("Buscar:");
	private JTextField buscador = new JTextField(27);
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton buscarCol = new JRadioButton("Buscar colectivo");
	private JRadioButton buscarProy = new JRadioButton("Buscar proyecto");
	TableRowSorter<?> filtroCol;
	TableRowSorter<?> filtroProy;

	/* tablas */
	private JScrollPane scrollCol;
	private JScrollPane scrollProy;
	private JTable tableColectivos;
	private JTable tableProyectos;
	
	/**
	 * Contructor del Panel de inicio de usuario
	 * 
	 */
    public PanelInicioUser(){
		super();
		toInicio.setVisible(false);

		/* boton logout */
		ImageIcon icon = new ImageIcon("icons/logout.png");
        Image img = icon.getImage();
        Image scaled = img.getScaledInstance(20,30,Image.SCALE_SMOOTH);
        logout.setIcon(new ImageIcon(scaled));
		
		/* buscador */
		group.add(buscarProy);
		group.add(buscarCol);
		buscarProy.setSelected(true);

		/* filtros */
		crearFiltros();

		/* crear tablas */
		AbstractTableModel modelCol = new TablaColectivos();
		AbstractTableModel modelProy = new TablaProyectos();
		tableColectivos = new JTable(modelCol);
		tableProyectos = new JTable(modelProy);
		scrollCol = new JScrollPane(tableColectivos);
		scrollProy = new JScrollPane(tableProyectos);

        /* configuraciones de la tabla */
        tableColectivos.setPreferredScrollableViewportSize(new Dimension(400, 200));
        tableColectivos.getColumnModel().getColumn(0).setPreferredWidth(210);
		tableColectivos.setFillsViewportHeight(true);
		tableColectivos.setAutoCreateRowSorter(true);
		tableColectivos.setOpaque(false);
		scrollCol.setVisible(false);

		tableProyectos.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tableProyectos.getColumnModel().getColumn(0).setPreferredWidth(220);
		tableProyectos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableProyectos.setFillsViewportHeight(true);
		tableProyectos.setAutoCreateRowSorter(true);
		tableProyectos.setOpaque(false);
		scrollProy.setVisible(true);
		
		setBusquedaAutomatica();
		setControlFiltros();
		setControlBusqPC();

		setColocacion();
		/* botones */
		this.add(logout);	
    	this.add(crearColectivo);
		this.add(crearProyecto);
		this.add(informes);
		/* tablas */
		this.add(scrollCol);
		this.add(scrollProy);
		/* buscador */
		this.add(buscarLabel);
		this.add(buscador);
		this.add(buscarCol);
		this.add(buscarProy);
		/* filtros */
		this.add(filtrarLabel);
		this.add(filtrar);
		this.add(limpiar);
		this.add(tipoEstado);
		this.add(ambito);
		this.add(distrito);
		this.add(estado);
	}

	/**
	 * Coloca todos los elementos de este panel
	 * 
	 */
	private void setColocacion() {
		/* tablas */
		spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollCol, 50, SpringLayout.HORIZONTAL_CENTER, this);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, scrollCol, 50, SpringLayout.VERTICAL_CENTER, this);
		spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollProy, 50, SpringLayout.HORIZONTAL_CENTER, this);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, scrollProy, 50, SpringLayout.VERTICAL_CENTER, this);

		/*Botones*/
		spring.putConstraint(SpringLayout.WEST, logout, 10, SpringLayout.WEST, this);
		spring.putConstraint(SpringLayout.NORTH, logout, 10, SpringLayout.NORTH, this);
		
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, crearProyecto, 0, SpringLayout.VERTICAL_CENTER, scrollCol);
		spring.putConstraint(SpringLayout.EAST, crearProyecto, -15, SpringLayout.WEST, scrollCol);
    	spring.putConstraint(SpringLayout.NORTH, crearColectivo, 45, SpringLayout.SOUTH, crearProyecto);
		spring.putConstraint(SpringLayout.EAST, crearColectivo, -15, SpringLayout.WEST, scrollCol);
		spring.putConstraint(SpringLayout.SOUTH, informes, -45, SpringLayout.NORTH, crearProyecto);
		spring.putConstraint(SpringLayout.EAST, informes, -15, SpringLayout.WEST, scrollCol);

		/* buscador */
		spring.putConstraint(SpringLayout.SOUTH, buscarCol, -10, SpringLayout.NORTH, scrollCol);
		spring.putConstraint(SpringLayout.WEST, buscarCol, 0, SpringLayout.WEST, scrollCol);
		spring.putConstraint(SpringLayout.SOUTH, buscarProy, -10, SpringLayout.NORTH, scrollCol);
		spring.putConstraint(SpringLayout.WEST, buscarProy, 0, SpringLayout.EAST, buscarCol);
		spring.putConstraint(SpringLayout.SOUTH, buscador, -10, SpringLayout.NORTH, buscarCol);
		spring.putConstraint(SpringLayout.WEST, buscador, 0, SpringLayout.WEST, buscarCol);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, buscarLabel, 0, SpringLayout.VERTICAL_CENTER, buscador);
		spring.putConstraint(SpringLayout.EAST, buscarLabel, -15, SpringLayout.WEST, buscador);

		/* filtros */
		spring.putConstraint(SpringLayout.SOUTH, tipoEstado, -10, SpringLayout.NORTH, buscador);
		spring.putConstraint(SpringLayout.WEST, tipoEstado, 0, SpringLayout.WEST, buscador);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, ambito, 0, SpringLayout.VERTICAL_CENTER, tipoEstado);
		spring.putConstraint(SpringLayout.WEST, ambito, 7, SpringLayout.EAST, tipoEstado);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER,distrito, 0, SpringLayout.VERTICAL_CENTER, tipoEstado);
		spring.putConstraint(SpringLayout.WEST, distrito, 7, SpringLayout.EAST, tipoEstado);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, estado, 0, SpringLayout.VERTICAL_CENTER, tipoEstado);
		spring.putConstraint(SpringLayout.WEST, estado, 7, SpringLayout.EAST, tipoEstado);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, filtrar, 0, SpringLayout.VERTICAL_CENTER, tipoEstado);
		spring.putConstraint(SpringLayout.WEST, filtrar, 7, SpringLayout.EAST, estado);
		spring.putConstraint(SpringLayout.VERTICAL_CENTER, filtrarLabel, 0, SpringLayout.VERTICAL_CENTER, tipoEstado);
		spring.putConstraint(SpringLayout.EAST, filtrarLabel, -15, SpringLayout.WEST, tipoEstado);
		spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, limpiar, 0, SpringLayout.HORIZONTAL_CENTER, filtrar);
		spring.putConstraint(SpringLayout.NORTH, limpiar, 10, SpringLayout.SOUTH, filtrar);
		
	}
	
    
	/**
	 * Establece el control de la busqueda automatica
	 * 
	 */
	private void setBusquedaAutomatica() {
		filtroCol = new TableRowSorter<>(tableColectivos.getModel());
		filtroProy = new TableRowSorter<>(tableProyectos.getModel());
		tableColectivos.setRowSorter(filtroCol);
		tableProyectos.setRowSorter(filtroProy);

		buscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				String cadena = buscador.getText();
				buscador.setText(cadena);
				repaint();
				filtro();
			}
		});
	}

	/**
	 * Metodo para filtrar, en el buscador
	 * 
	 */
	private void filtro() {
		filtroCol.setRowFilter(RowFilter.regexFilter(buscador.getText()));
		filtroProy.setRowFilter(RowFilter.regexFilter(buscador.getText(), 0));
	}

	/**
	 * Metodo para crear los filtros
	 * 
	 */
	private void crearFiltros() {
		String[] filtroTE = {"Estado", "Infraestructura", "Social"};
		tipoEstado = new JComboBox<>(filtroTE);

		String[] filtroAmb = {"Todos","Nacional","Internacional"};
		ambito = new JComboBox<>(filtroAmb);

		ArrayList<String> filtroDist = new ArrayList<>();
		filtroDist.add("Todos");
		for (String d: Application.getApplication().getDistricts())
			filtroDist.add(d);
		distrito = new JComboBox<>(filtroDist.toArray(new String[0]));
		
		projectState[] filtroPS = {projectState.ACEPTADO, projectState.VOTOSALCANZADOS,
								 projectState.ENVIADO, projectState.FINANCIADO,
								 projectState.CADUCADO};
		estado = new JComboBox<>(filtroPS);
		ambito.setVisible(false);
		distrito.setVisible(false);
		estado.setVisible(true);
	}

	/**
	 * Devuelve la tabla de colectivos del panel
	 * 
	 * @return tabla de colectivos
	 */
	public JTable getTableC() {
		return tableColectivos;
	}

	/**
	 * Devuelve la tabla de proyectos del panel
	 * 
	 * @return tabla de proyectos
	 */
	public JTable getTableP() {
		return tableProyectos;
	}

	/**
     * Devuelve el filtro principial, por estado o tipo de proyecto
     * 
     * @return filtro seleccionado
     */
	public String getTipoEstado(){
		return (String)tipoEstado.getSelectedItem();
	}

	/**
     * Devuelve el ambito del proyecto social como filtro
     * 
     * @return filtro seleccionado
     */
	public String getAmbito(){
		return (String)ambito.getSelectedItem();
	}

	/**
     * Devuelve el distrito seleccionado como filtro
     * 
     * @return filtro seleccionado
     */
	public String getDistrito(){
		return (String)distrito.getSelectedItem();
	}

	/**
     * Devuelve el estado del proyecto como filtro
     * 
     * @return filtro seleccionado
     */
	public projectState getProjectState(){
		return (projectState)estado.getSelectedItem();
	}

	/**
	 * Establece que ocurre en el panel en funcion del filtro seleccionado
	 * 
	 */
	private void setControlFiltros(){
		tipoEstado.addActionListener(e -> {
			ambito.setVisible(false);
			distrito.setVisible(false);
			estado.setVisible(false);
			if (getTipoEstado().equals("Estado")) estado.setVisible(true);
			else if(getTipoEstado().equals("Social")) ambito.setVisible(true);
			else distrito.setVisible(true);
		});
	}

	/**
	 * Establece que ocurre en el panel si se selecciona busqueda de colectivos o de proyectos
	 * 
	 */
	private void setControlBusqPC(){
		buscarCol.addActionListener(e -> {
			scrollProy.setVisible(false);
			scrollCol.setVisible(true);
		});
		buscarProy.addActionListener(e -> {
			scrollCol.setVisible(false);
			scrollProy.setVisible(true);
		});
	}
	
	/**
     * Establece el control del boton de cerrar sesion
     * 
     * @param c accion que activa el boton
     */
	public void setControlLogout(ActionListener c){
		logout.addActionListener(c);
	}

	/**
     * Establece el control del boton de solicitar informes
     * 
     * @param c accion que activa el boton
     */
	public void setControlSolicitarInformes(ActionListener c) {
		informes.addActionListener(c);
	}
	
	/**
     * Establece el control del boton de crear proyecto
     * 
     * @param c accion que activa el boton
     */
	public void setControlCrearProyecto(ActionListener c){
		crearProyecto.addActionListener(c);
	}
	
	/**
     * Establece el control del boton de crear colectivo
     * 
     * @param c accion que activa el boton
     */
	public void setControlCrearColectivo(ActionListener c) {
		crearColectivo.addActionListener(c);
	}

	/**
     * Establece el control del boton de filtrar proyectos
     * 
     * @param c accion que activa el boton
     */
	public void setControlFiltrar(ActionListener c) {
		filtrar.addActionListener(c);
	}

	/**
     * Establece el control del boton de limpiar filtro
     * 
     * @param c accion que activa el boton
     */
	public void setControlLimpiarFiltro(ActionListener c) {
		limpiar.addActionListener(c);
	}

	/**
	 * Establece el control cuando se hace click en la tabla
	 * 
	 * @param c accion que activa el control
	 */
	public void setControlCollective(ListSelectionListener c){
		ListSelectionModel cellSelectionModel = tableColectivos.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(c);
	}
	

	/**
	 * Establece el control cuando se hace click en la tabla
	 * 
	 * @param c accion que activa el control
	 */
    public void setControlProject(ListSelectionListener contUserDisplayProjects){
		ListSelectionModel cellSelectionModel = this.getTableP().getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(contUserDisplayProjects);

    }

	/**
	 * Actualiza la informacion sobre el usuario que tiene el colectiovo
	 * 
	 * @param proyectos proyectos de la aplicacion
	 * @param colectivos colectivos de la aplicacion
	 */
	public void updateData(ArrayList<Project> proyectos, ArrayList<Collective> colectivos) {
        ((TablaProyectos)tableProyectos.getModel()).setPP(proyectos);
        ((TablaColectivos)tableColectivos.getModel()).setCC(colectivos);
	}

	
}