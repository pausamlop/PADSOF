package across.gui.notif;

import java.util.ArrayList;

import javax.swing.table.*;

import across.model.notification.Notification;

/**
 * Clase TablaNotif
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class TablaNotif extends AbstractTableModel{
	
	private ArrayList<Notification> notif = new ArrayList<>();
	private ArrayList<Boolean> visto = new ArrayList<>();

	private String[] titulos = {"Mensaje", "Visto"};
	
	/**
	 * Devuelve el numero de columnas de la tabla
	 * 
	 * @return numero de columnas
	 */
	public int getColumnCount() { return titulos.length; }
	
	/**
	 * Devuelve el numero de filas de la tabla
	 * 
	 * @return numero de filas
	 */
	public int getRowCount() { return notif.size(); }
	
	/**
	 * Devulve el valor en la celda indicadad
	 * 
	 * @param row fila
	 * @param col columna
	 * @return objeto en (fila, columna)
	 */
	public Object getValueAt(int row, int col) {
		if (row < 0 || row >= notif.size())
			return null;

		if(col == 0) {
			return notif.get(row);
		}else if (col == 1){
			return visto.get(row);
		}
		return null;
	}

	/**
	 * Cambia el valor del boolean visto
	 * 
	 * @param value valor a asignar
	 * @param row fila
	 * @param col columna
	 */
	public void setValueAt(Object value, int row, int col){
		if(col == 1 && !visto.get(row)){
			visto.set(row, (Boolean)value);
			fireTableCellUpdated(row,col);
		}
	}
	
	/**
	 * Devuelve el titulo de una columna
	 * 
	 * @param c columna de la cual devolver el nombre
	 * @return el titulo de la columna c
	 */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
	 * Metodo para añadir una fila a la talba de notificaciones
	 * 
	 * @param not notificaicon a añadir a la tabla
	 * @param visto boolean a añadir a la tabla, para referirse a si ha sido o no vista la notificacion
	 */
	public void addRow(Notification not, boolean visto) {
		notif.add(not);
		this.visto.add(visto);
		fireTableCellUpdated(notif.size(), 0);
		fireTableCellUpdated(notif.size(), 1);
	}
	
	/**
	 * Devuelve si una casilla es o no editable
	 * 
	 * @return true en caso de que sea editable, false en caso contrario
	 */
	public boolean isCellEditable(int row, int col) {
		return col == 1;
	}
	
	/**
	 * Devuelve la clase de la columna especificada
	 * 
	 * @return clase de los elementos de una columna en concreto
	 */
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}
	
	/**
	 * Establece el array de notificaciones, por el que le pasamos como parametro
	 * 
	 * @param not notificaciones
	 */
	public void setNotifications(ArrayList<Notification> not){
		deleteData();
		notif.addAll(not);
		for (int i = 0; i < notif.size(); i++)
			visto.add(false);
		
		if (notif.size() == 0) return;
		fireTableRowsInserted(0, notif.size() - 1);
	}
	
	/**
	 * Borra los datos de la tabla
	 * 
	 */
	private void deleteData() {
		int rows = getRowCount();
        if (rows == 0) {
            return;
        }
        notif.clear();
        visto.clear();
        fireTableRowsDeleted(0, rows - 1);
	}

}
