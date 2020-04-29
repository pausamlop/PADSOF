package across.gui.user.tablasPerfil;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.*;

import across.gui.MainFrame;
import across.gui.user.PanelPerfil;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;
import across.model.user.Collective;
import across.model.user.User;

@SuppressWarnings("serial")
public class TablaColectivosMiembros extends AbstractTableModel{
	
	private PanelPerfil panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<Integer> miembros = new ArrayList<>();
	private ArrayList<Boolean> disjoin = new ArrayList<>();
	private String[] titulos = {"Colectivo", "Numero de miembros", "Abandonar el colectivo"};
	
	
	public TablaColectivosMiembros(PanelPerfil panel) {
		
		this.panel = panel;
		
		for(Collective c : Application.getApplication().getCurrentUser().getMemberCollectives()) {
			if(!nombres.contains(c.getName())) {
				nombres.add(c.getName());
				miembros.add(c.getMembers().size());
				disjoin.add(false);
			}
		}

	}

	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return nombres.get(row);
		}else if(col == 1) {
			return miembros.get(row);
		}else {
			return disjoin.get(row);
		}
	}
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
		
		
		Collective c = Application.getApplication().getCurrentUser().getMemberCollectives().get(row);
		if (Application.getApplication().getCurrentUser().getCreatedCollectives().contains(c)) return false;
		
		
        if (col == 2) {
            return true;
        }else {
            return false;
        }
    }
	
	public void setValueAt(Object value, int row, int col) {	
		
		if(col == 2) {
			disjoin.set(row,(Boolean)value);
			fireTableCellUpdated(row, col);
		}
    }
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
}
