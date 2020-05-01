package across.gui.admin;

import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.*;

import across.gui.MainFrame;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.project.Project;
import across.model.user.User;

@SuppressWarnings("serial")
public class TablaUsuarios extends AbstractTableModel{
	
	private PanelAdminUsuarios panel;
	private ArrayList<String> nombres = new ArrayList<>();
	private ArrayList<String> estado = new ArrayList<>();
	private ArrayList<Boolean> validar = new ArrayList<>();
	private ArrayList<Boolean> bloquear = new ArrayList<>();
	private String[] titulos = {"Usuarios", "Estado", "Validar","Bloquear"};
	
	
	public TablaUsuarios(PanelAdminUsuarios panel) {
		
		this.panel = panel;
		
		for(User aux : Application.getApplication().getNonValidatedUsers()) {
			if(!nombres.contains(aux.getUsername())) {
				nombres.add(aux.getUsername());
				
				estado.add("No validado");
				
				validar.add(false);
				bloquear.add(false);
			}
		}
		
		for(User aux : Application.getApplication().getUsers()) {
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

	}

	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return nombres.size(); }
	
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
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 3 || col == 2) {
            return true;
        }else {
            return false;
        }
    }
	
	public void setValueAt(Object value, int row, int col) {	
		User user = Application.getApplication().getUserByName(nombres.get(row));
		
		if(col == 3) {
			bloquear.set(row,(Boolean)value);
			fireTableCellUpdated(row, col);
		}else if(col == 2 && !validar.get(row)){
			user.validate();
			validar.set(row, (Boolean)value);
			fireTableCellUpdated(row,col);
		}
    }
	
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
}
