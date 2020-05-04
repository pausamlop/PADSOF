package across.gui.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.*;

import across.model.enumerations.projectState;
import across.model.project.Project;

@SuppressWarnings("serial")
public class TablaProyectos extends AbstractTableModel{
	
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Boolean> validar = new ArrayList<>();
	private ArrayList<Boolean> rechazar = new ArrayList<>();
	
	private Map<String, Boolean> changes;
	
	private String[] titulos = {"Proyectos", "NÂº de votos", "Estado", "Validar","Rechazar"};
	
	/**
	 * Constructor de la clase TablaProyectos
	 * 
	 */
	public TablaProyectos() {}

	/**
	 * 
	 * @return numero de columnas de la tabla
	 */
	public int getColumnCount() { return titulos.length; }
	
	/**
	 * 
	 * @return numero de elementos guardados en la tabla
	 */
	public int getRowCount() { return nombres.size(); }
	
	/**
	 * 
	 * @param row fila que contiene a la celda
	 * @param columna que contiene a la celda
	 * @return valor de la tabla en la celda en cuestion
	 */
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 2) {
			return estado.get(row);
		}else if(col == 3) {
			return validar.get(row);
		}else if(col == 4) {
			return rechazar.get(row);
		}else {
			return votos.get(row);
		}
	}
	
	/**
	 * @param columna de la cual extraer el nombre
	 * @return nombre de la columna c
	 */
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	/**
	 * 
	 * @param row fila de la celda
	 * @param col columna de la celda
	 * @return true en caso de que sea editable false en el contrario
	 */
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
		if(!validar.get(row) && !rechazar.get(row)) {
	        if (col == 3) {
	            return true;
	        } else if(col == 4) {
	        	return true;
	        }
		}
        return false;
    }
	
	/**
	 * Actualiza el valor de una determinada celda editable asi como todas aquellas otra que se pudiesen ver afectadas debido a este cambio
	 * 
	 * @param row fila de la cual obtener el valor
	 * @param col columna de la que botener el valor
	 * @param object valor a establecer
	 */
	public void setValueAt(Object value, int row, int col) {	
		//Project aux = app.getProjectByName(nombres.get(row));
		
		if(col == 3) {
			changes.put(nombres.get(row), true);
			
			votos.set(row, 1);
			estado.set(row, projectState.ACEPTADO);
			validar.set(row,true);
			fireTableCellUpdated(row, col);
			fireTableCellUpdated(row, 2);
			fireTableCellUpdated(row, 1);
		}else {
			changes.put(nombres.get(row), false);
			
			estado.set(row, projectState.RECHAZADO);
			rechazar.set(row,true);
			fireTableCellUpdated(row, col);
			fireTableCellUpdated(row, 2);
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
	public void setProyectos(ArrayList<Project> proyectos) {
		
		 changes = new HashMap<>();
		
		for(Project aux : proyectos) {
			if(!nombres.contains(aux.getName())) {
				nombres.add(aux.getName());
				votos.add(aux.getVotes());
				
				projectState status = aux.getProjectState();
				estado.add(status);
				
				if(status == projectState.ENVALIDACION) {
					validar.add(false);
					rechazar.add(false);
				}else if(status == projectState.RECHAZADO){
					validar.add(false);
					rechazar.add(true);
				}else {
					validar.add(true);
					rechazar.add(false);
				}
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

