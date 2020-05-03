package across.gui.admin;

import java.util.ArrayList;

import javax.swing.table.*;

import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;

/**
 * Clase TablaProyectos
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class TablaProyectos extends AbstractTableModel{
	
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Boolean> validar = new ArrayList<>();
	private ArrayList<Boolean> rechazar = new ArrayList<>();
	private String[] titulos = {"Proyectos", "N� de votos", "Estado", "Validar","Rechazar"};
	
	private Application app = Application.getApplication();
	
	public TablaProyectos() {
		
		for(Project aux : Application.getApplication().getNonValidatedProjects()) {
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

	}

	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
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
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
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
	
	public void setValueAt(Object value, int row, int col) {	
		Project aux = app.getProjectByName(nombres.get(row));
		
		if(col == 3) {
			aux.validate();
			
			votos.set(row, aux.getVotes());
			estado.set(row, aux.getProjectState());
			validar.set(row,true);
			fireTableCellUpdated(row, col);
			fireTableCellUpdated(row, 2);
			fireTableCellUpdated(row, 1);
		}else {
			aux.reject();
			
			estado.set(row, aux.getProjectState());
			rechazar.set(row,true);
			fireTableCellUpdated(row, col);
			fireTableCellUpdated(row, 2);
		}
    }
	
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
}
