package across.gui.user.tablas;

import java.util.*;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import across.model.user.Collective;

/**
 * Clase TablaAfinidad
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class TablaAfinidad extends AbstractTableModel{
	
	private ArrayList<Collective> colectivos = new ArrayList<>();
	private ArrayList<Double> porcentaje = new ArrayList<>();

	private String[] titulos = {"Colectivo", "Afinidad (%)"};
	
	/**
	 * Devuelve el numero de columnas que tiene la tabla
	 * 
	 * @return numero de columnas
	 */
	public int getColumnCount() { return titulos.length; }
	
	/**
	 * Devuelve el numero de filas que tiene la tabla
	 * 
	 * @return numero de filas
	 */
	public int getRowCount() { return colectivos.size(); }
	
	/**
	 * Devuelve el objeto situado en la posicion indicada
	 * 
	 * @param row fila
	 * @param col columna
	 * @return objeto situado en (row,col)
	 */
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return (Collective)colectivos.get(row);
		}else {
			return (Double)porcentaje.get(row);
		}
	}
	
	/**
	 * Devuelve la cabecera de columna con indice c
	 * 
	 * @param c columna
	 * @return cabecera
	 */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
	 * Devuelve la clase de la columna especificada
	 * 
	 * @param c columna
	 * @return clase de la columna c
	 */
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	/**
	 * Actualiza la informacion de la tabla
	 * 
	 * @param lista map con colectivo y correpondiente afinidad
	 */
	public void setAfinity(Map<Collective,Double> lista){
		deleteData();
		for(Entry<Collective,Double> entry : lista.entrySet()) {
			colectivos.add(entry.getKey());
			porcentaje.add(entry.getValue());
		}
		if (colectivos.size() == 0) return;
		fireTableRowsInserted(0, colectivos.size() - 1);
	}
	
	/**
	 * Elimina la informacion contenida en la tabla
	 * 
	 */
	private void deleteData() {
		int rows = getRowCount();
        if (rows == 0) return;

        colectivos.clear();
        porcentaje.clear();
        fireTableRowsDeleted(0, rows - 1);
	}


}
