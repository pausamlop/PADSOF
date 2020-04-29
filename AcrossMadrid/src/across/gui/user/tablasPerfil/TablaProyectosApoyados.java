package across.gui.user.tablasPerfil;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.gui.admin.PanelAdminUsuarios;
import across.gui.user.PanelPerfil;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;
import across.model.user.User;

@SuppressWarnings("serial")
public class TablaProyectosApoyados extends AbstractTableModel{
	
	private PanelPerfil panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<projectState> estado = new ArrayList<>();
	private ArrayList<Integer> votos = new ArrayList<>();
	private String[] titulos = {"Proyecto", "Estado", "Votos"};
	
	public TablaProyectosApoyados(PanelPerfil panel) {
		
		this.panel = panel;
		
		for(Project p : Application.getApplication().getCurrentUser().getVotedProjects()) {
			if(!nombres.contains(p.getName())) {
				nombres.add(p.getName());
				estado.add(p.getProjectState());
				votos.add(p.getVotes());

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
        return false;
    }
	
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
