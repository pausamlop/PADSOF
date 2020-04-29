package across.gui.user.tablasPerfil;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.gui.admin.PanelAdminUsuarios;
import across.gui.user.PanelPerfil;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;
import across.model.user.Collective;
import across.model.user.User;

@SuppressWarnings("serial")
public class TablaProyectosCreados extends AbstractTableModel{
	
	private PanelPerfil panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private ArrayList<Boolean> finance = new ArrayList<>();
	private String[] titulos = {"Proyecto", "Estado", "Votos", "Financiar"};
	
	public TablaProyectosCreados(PanelPerfil panel) {
		
		this.panel = panel;
		
		for(Project p : Application.getApplication().getCurrentUser().getCreatedProjects()) {
			if(!nombres.contains(p.getName())) {
				nombres.add(p.getName());
				estado.add(p.getProjectState());
				votos.add(p.getVotes());
				finance.add(false);
			}
		}
		
	}
	
	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 1) {
			return estado.get(row);
		}else if(col == 2) {
			return votos.get(row);
		}else {
			return finance.get(row);
		}
	}
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	public boolean isCellEditable(int row, int col) {
		
		Integer votes = Application.getApplication().getCurrentUser().getCreatedProjects().get(row).getVotes();
		if (votes < Application.getApplication().getMinVotes()) return false;
		if (col == 3) return true;
        return false;
    }
	
	public void setValueAt(Object value, int row, int col) {	
		
		if(col == 3) {
			finance.set(row,(Boolean)value);
			fireTableCellUpdated(row, col);
		}
    }
	
	
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}