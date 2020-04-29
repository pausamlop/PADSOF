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
public class TablaColectivosCreados extends AbstractTableModel{
	
	private PanelPerfil panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> miembros = new ArrayList<>();

	private String[] titulos = {"Colectivo", "Numero de Miembros"};
	
	public TablaColectivosCreados(PanelPerfil panel) {
		
		this.panel = panel;
		
		for(Collective c : Application.getApplication().getCurrentUser().getCreatedCollectives()) {
			if(!nombres.contains(c.getName())) {
				nombres.add(c.getName());
				miembros.add(c.getMembers().size());
			}
		}
		

	}
	
	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else {
			return miembros.get(row);
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
