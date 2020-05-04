package across.gui.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.*;

import across.model.user.User;

/**
 * Tabla de usuarios personalizada, con los datos relacionados con los usuarios en la app
 * 
 * @author Usuario
 *
 */
@SuppressWarnings("serial")
public class TablaUsuarios extends AbstractTableModel{
	
	private PanelAdminUsuarios panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<String> estado = new ArrayList<>();
	private ArrayList<Boolean> validar = new ArrayList<>();
	private ArrayList<Boolean> bloquear = new ArrayList<>();
	private String[] titulos = {"Usuarios", "Estado", "Validar","Bloquear"};
	
	private Map<String, Boolean> changes;
	
	/**
	 * Contructor de la clase tabla usuarios, simplemente crea una tabla vacia que poblaremos luego
	 * 
	 * @param panel
	 */
	public TablaUsuarios(PanelAdminUsuarios panel) {
		
		this.panel = panel;

	}

	/**
	 * Devuelve el numero de columnas de la tabla
	 * 
	 */
	public int getColumnCount() { return titulos.length; }
	
	/**
	 * Devuelve el numero de elementos en la tabla 
	 */
	public int getRowCount() { return nombres.size(); }
	
	/**
	 * Devuelve el valor de una celda de la tabla
	 * 
	 * @param row fila de la cual obtener el valor
	 * @param col columna de la que botener el valor
	 */
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 1) {
			return estado.get(row);
		}else if(col == 2) {
			return validar.get(row);
		}else {
			return bloquear.get(row);
		}
	}
	
	/**
	 * Devuelve el titulo de la columna c
	 * 
	 * @param c posicion del titulo en el array de titulos
	 * 
	 * @return titulo de la columna c 
	 */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
	 * Dice si la celda es o no editable
	 * 
	 * @param row fila de la cual obtener el valor
	 * @param col columna de la que botener el valor
	 * 
	 * @boolean diciendo si la celda es o no editable
	 */
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 3 || col == 2) {
            return true;
        }else {
            return false;
        }
    }
	
	/**
	 * Actualiza el valor de una determinada celda editable asi como todas aquellas otra que se pudiesen ver afectadas debido a este cambio
	 * 
	 * @param row fila de la cual obtener el valor
	 * @param col columna de la que botener el valor
	 * @param object valor a establecer
	 */
	public void setValueAt(Object value, int row, int col) {	
		
		if(col == 3) {
			bloquear.set(row,(Boolean)value);
			fireTableCellUpdated(row, col);
		}else if(col == 2 && !validar.get(row)){
			changes.put(nombres.get(row), true);
			
			validar.set(row, (Boolean)value);
			fireTableCellUpdated(row,col);
			estado.set(row, "Funcional");
			fireTableCellUpdated(row,1);
		}
    }
	
	/**
	 * Metodo que permite al rendere de la tabla interpretar que tipo de celda usar, en caso de una columna de booleans, una chekcBox
	 * 
	 * @param c nº de la columna
	 * @return Class<?> clase de los elementos de la columna c
	 */
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	/**
	 * Funcion para poblar la tabla con los datos de la app
	 * 
	 * @param nonValidated array de usuarios no validados
	 * @param validatedUsers array de usuarios validados
	 */
	public void setUsuarios(ArrayList<User> nonValidated, ArrayList<User> validatedUsers) {
		
		 changes = new HashMap<>();
		
		 for(User aux : nonValidated) {
				if(!nombres.contains(aux.getUsername())) {
					nombres.add(aux.getUsername());
					
					estado.add("No validado");
					
					validar.add(false);
					bloquear.add(false);
				}
			}
			
			for(User aux : validatedUsers) {
				if(!nombres.contains(aux.getUsername())) {
					nombres.add(aux.getUsername());
					
					if(aux.getBlocked()) {
						estado.add("Bloqueado");
					}else {
						estado.add("Funcional");
					}
					
					if(aux.getBlocked()) {
						bloquear.add(true);
					}else {
						bloquear.add(false);
					}
					
					validar.add(true);
					
				} 
			}
		
		if (nombres.size() == 0) return;
		fireTableRowsInserted(0, nombres.size() - 1);
	}
	
	/**
	 * Devuelve un mapa con los cambios que se han producido al modificar la tabla
	 * 
	 * @return Map<String, Boolean>
	 */
	public Map<String, Boolean> getChanges(){
		return changes;
	}
	
	
}
