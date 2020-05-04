package across.gui.user.tablas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.model.enumerations.projectState;
import across.model.project.Project;

/**
 * Clase TablaProyectos de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class TablaProyectos extends AbstractTableModel{
	
	private ArrayList<Project> nombres = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private String[] titulos = {"Proyecto", "Estado", "Votos"};

	/**
     * Devuelve los proyectos de la tabla
	 * @return proyectos
     */
	public ArrayList<Project> getProjects() { return nombres; }

	/**
     * Devuelve el numero de columnas
	 * @return numero de columnas
     */
	public int getColumnCount() { return titulos.length; }
	
	/**
     * Devuelve el numero de filas
	 * @return numero de filas
     */
	public int getRowCount() { return nombres.size(); }
	
	/**
     * Devuelve el numero de filas
	 * @param row fila
	 * @param col columna
	 * @return numero de filas
     */
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 1) {
			return estado.get(row);
		}else {
			return votos.get(row);
		}
	}
	
	/**
     * Devuelve el nombre de la columna
	 * 
	 * @param c columna
	 * @return nombre
     */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
     * Devuelve la clase de la columna
	 * 
	 * @param c columna
	 * @return clase de la columna
     */
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	/**
     * Setter de la tabla
	 * 
	 * @param lista lista de proyectos
     */
	public void setPP(ArrayList<Project> lista){
		deleteData();
		for(Project p : lista) {
			if(!nombres.contains(p)) {
				nombres.add(p);
				estado.add(p.getProjectState());
				votos.add(p.getVotes());

			}
		}
		
		if (nombres.size() == 0) return;
		fireTableRowsInserted(0, nombres.size() - 1);
	}
	
	/**
     * Borra los datos de la tabla
     */
	private void deleteData() {
		int rows = getRowCount();
        if (rows == 0) {
            return;
        }
        nombres.clear();
        estado.clear();
		votos.clear();
        fireTableRowsDeleted(0, rows - 1);
	}
	
	
	

}
