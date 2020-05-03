package across.gui.admin;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import across.gui.EditFont;
import across.gui.menu.HomeAdmin;
import across.gui.user.tablas.TablaProyectos;

/**
 * Clase PanelInicioAdmin de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelAdminUsuarios extends HomeAdmin {

	JRadioButton proyectos = new JRadioButton("Proyectos");
	JRadioButton usuarios = new JRadioButton("Usuarios");
	JRadioButton config = new JRadioButton("Configuracion");

	JTextField buscador = new JTextField(20);
	JLabel etiquetaBuscador = new JLabel("Buscar: ");

	JTextField bloqMensaje = new JTextField(20);
	JLabel bloq = new JLabel("Introduce un mensaje de bloqueo");

	TablaUsuarios tablaUsuarios;

	JTable aux;
	TableRowSorter<?> trsfiltro;
	String filtro;

	ButtonGroup grupo = new ButtonGroup();

	JLabel adminIni = new JLabel("Administrador");
	JLabel usersPanel = new JLabel("Usuarios");

	/**
	 * Consructor de la clase PanelAdminUsuarios
	 */
	public PanelAdminUsuarios() {

		SpringLayout layout = (SpringLayout) getLayout();

		/* Botones */
		grupo.add(proyectos);
		grupo.add(usuarios);
		grupo.add(config);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, proyectos, -75, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, proyectos, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarios, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, usuarios, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, config, 75, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, config, 5, SpringLayout.WEST, this);

		/* Tablas */
		tablaUsuarios = new TablaUsuarios(this);
		aux = new JTable(tablaUsuarios);
		aux.setPreferredScrollableViewportSize(new Dimension(450, 200));
		aux.setFillsViewportHeight(true);
		aux.setOpaque(false);
		aux.setCellSelectionEnabled(true);

		trsfiltro = new TableRowSorter<>(aux.getModel());
		aux.setRowSorter(trsfiltro);

		JScrollPane table = new JScrollPane(aux);
		table.setOpaque(false);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, table, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, table, 20, SpringLayout.VERTICAL_CENTER, this);

		/* Titulos */
		EditFont.setSize(adminIni, 25);
		EditFont.setSize(usersPanel, 15);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, adminIni, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, adminIni, 50, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usersPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, usersPanel, 30, SpringLayout.NORTH, adminIni);

		/* Buscador */

		buscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				String cadena = (buscador.getText());
				buscador.setText(cadena);
				repaint();
				filtro();
			}
		});

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, buscador, -70, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, buscador, -110, SpringLayout.VERTICAL_CENTER, this);

		layout.putConstraint(SpringLayout.EAST, etiquetaBuscador, -5, SpringLayout.WEST, buscador);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, etiquetaBuscador, -110, SpringLayout.VERTICAL_CENTER, this);

		/* Bloqueo de usuarios */
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bloqMensaje, -70, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bloqMensaje, -100, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, bloq, -10, SpringLayout.WEST, bloqMensaje);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bloq, -100, SpringLayout.VERTICAL_CENTER, this);

		/* Inclusiones */
		this.add(adminIni);
		this.add(usersPanel);

		this.add(proyectos);
		this.add(usuarios);
		this.add(config);

		this.add(buscador);
		this.add(etiquetaBuscador);

		this.add(table);
	}

	/**
	 * Metodo para filtrar, en el buscador
	 * 
	 */
	public void filtro() {
		filtro = buscador.getText();
		trsfiltro.setRowFilter(RowFilter.regexFilter(buscador.getText(), 0));
	}

	public void setControlAdminConfig(ActionListener c) {
		config.addActionListener(c);
	}

	public void setControlAdminProyectos(ActionListener c) {
		proyectos.addActionListener(c);
	}

	public void setControlAdminBloq(ListSelectionListener c) {
		ListSelectionModel cellSelectionModel = aux.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(c);
	}

	public void setUsuariosButton() {
		usuarios.setSelected(true);
	}

	public void setFUsuariosButton() {
		usuarios.setSelected(false);
	}

	public TablaUsuarios getTablaUsuarios() {
		return tablaUsuarios;
	}

	public JTable getTabla() {
		return aux;
	}
	
    
    public void updateTableCell(Object value,int row, int col) {
    	((TablaUsuarios)aux.getModel()).setValueAt(value, row, col);
    }

}