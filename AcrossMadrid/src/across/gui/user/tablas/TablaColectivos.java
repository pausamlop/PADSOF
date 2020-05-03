package across.gui.user.tablas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.model.user.Collective;

/**
 * Clase TablaColectivos de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class TablaColectivos extends AbstractTableModel{
	
	private ArrayList<Collective> nombres = new ArrayList<>();
	private ArrayList<Integer> miembros = new ArrayList<>();

	private String[] titulos = {"Colectivo", "Numero de Miembros"};

	/**
     * Devuelve los colectivos de la tabla
	 * @return colectivos
     */
	public ArrayList<Collective> getCollectives() { return nombres; }

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
	 * @param row
	 * @param col
	 * @return numero de filas
     */
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else {
			return miembros.get(row);
		}
	}
	
	/**
     * Devuelve el nombre de la columna
	 * @param c
	 * @return nombre
     */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
     * Devuelve la clase de la columna
	 * @param c
	 * @return clase de la columna
     */
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }	
	
	/**
     * Setter de la tabla
	 * @param lista
     */
	public void setCC(ArrayList<Collective> lista){
		deleteData();
		
		for(Collective c : lista) {
			if(!nombres.contains(c)) {
				nombres.add(c);
				miembros.add(c.getMembers().size());
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
        miembros.clear();
        fireTableRowsDeleted(0, rows - 1);
	}


}
