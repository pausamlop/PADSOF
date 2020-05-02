package across.gui.user.tablasPerfil;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import across.gui.admin.PanelAdminUsuarios;
import across.gui.user.PanelPerfil;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.notification.Notification;
import across.model.project.Project;
import across.model.user.Collective;
import across.model.user.User;

@SuppressWarnings("serial")
public class TablaColectivos extends AbstractTableModel{
	
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> miembros = new ArrayList<>();

	private String[] titulos = {"Colectivo", "Numero de Miembros"};
	
	
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
        return false;
    }
	
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	
	public void addRow(String n, Integer i) {
		nombres.add(n);
		this.miembros.add(i);
		fireTableCellUpdated(nombres.size(), 0);
		fireTableCellUpdated(miembros.size(), 1);
	}
	
	
	
	public void setCC(ArrayList<Collective> lista){
		deleteData();
		for(Collective c : lista) {
			if(!nombres.contains(c.getName())) {
				nombres.add(c.getName());
				miembros.add(c.getMembers().size());
			}
		}
		fireTableRowsInserted(0, nombres.size() - 1);
	}
	
	private void deleteData() {
		int rows = getRowCount();
        if (rows == 0) {
            return;
        }
        nombres.clear();
        miembros.clear();
        fireTableRowsDeleted(0, rows - 1);
	}


}
