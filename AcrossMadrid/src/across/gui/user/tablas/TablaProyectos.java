package across.gui.user.tablas;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.model.enumerations.projectState;
import across.model.project.Project;

@SuppressWarnings("serial")
public class TablaProyectos extends AbstractTableModel{
	
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private String[] titulos = {"Proyecto", "Estado", "Votos"};

	
	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 1) {
			return estado.get(row);
		}else {
			return votos.get(row);
		}
	}
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	public boolean isCellEditable(int row, int col) {
        return false;
    }
	
	
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	
	
	
	
	public void addRow(String n, projectState p,  Integer i) {
		nombres.add(n);
		this.estado.add(p);
		this.votos.add(i);
		fireTableCellUpdated(nombres.size(), 0);
		fireTableCellUpdated(estado.size(), 1);
		fireTableCellUpdated(votos.size(), 1);
	}
	
	
	
	public void setPP(ArrayList<Project> lista){
		deleteData();
		for(Project p : lista) {
			if(!nombres.contains(p.getName())) {
				nombres.add(p.getName());
				estado.add(p.getProjectState());
				votos.add(p.getVotes());

			}
		}
		
		if (nombres.size() == 0) return;
		fireTableRowsInserted(0, nombres.size() - 1);
	}
	
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
