package across.gui.admin;

import java.util.ArrayList;

import javax.swing.table.*;

import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;

@SuppressWarnings("serial")
public class TablaProyectos extends AbstractTableModel{
	
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<String> validar = new ArrayList<>();
	private String[] titulos = {"Proyectos", "Nº de votos", "Estado", "Validar"};
	
	private Application app = Application.getApplication();
	
	
	public TablaProyectos() {
		
		for(Project aux : Application.getApplication().getNonValidatedProjects()) {
			if(!nombres.contains(aux.getName())) {
				nombres.add(aux.getName());
				votos.add(aux.getVotes());
				
				projectState status = aux.getProjectState();
				estado.add(status);
				
				if(status == projectState.ENVALIDACION) {
					validar.add("Validar/Rechazar");
				}else if(status == projectState.RECHAZADO) {
					validar.add("Rechazado");
				}else {
					validar.add("Validado");
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
        if (col == 3 && validar.get(row).equals("Validar/Rechazar")) {
            return true;
        } else {
            return false;
        }
    }
	
	public void setValueAt(Object value, int row, int col) {	
		if(col == 3) {
			Project aux = app.getProjectByName(nombres.get(row));
			
			if(((String)value).equals("Validar")) {
				aux.validate();
				
				estado.set(row, aux.getProjectState());
				validar.set(row,"Validado");
				fireTableCellUpdated(row, col);
			}else {
				aux.reject();
				
				estado.set(row, aux.getProjectState());
				validar.set(row,"Rechazado");
				fireTableCellUpdated(row, col);
			}
		}
    }
	
}
