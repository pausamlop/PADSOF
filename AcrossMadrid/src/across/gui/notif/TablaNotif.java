package across.gui.notif;

import java.util.ArrayList;

import javax.swing.table.*;

import across.model.application.Application;
import across.model.notification.Notification;

@SuppressWarnings("serial")
public class TablaNotif extends AbstractTableModel{
	
	private ArrayList<Notification> notif = new ArrayList<>();
	private ArrayList<Boolean> visto = new ArrayList<>();

	private String[] titulos = {"Mensaje", "Visto"};
	
	public int getColumnCount() { return titulos.length; }
	
	public int getRowCount() { return notif.size(); }
	
	public Object getValueAt(int row, int col) {
		if(col == 0) {
			return notif.get(row);
		}if (col == 1){
			return visto.get(row);
		}
		return null;
	}

	public void setValueAt(Object value, int row, int col){
		if (col == 0){
			notif.set(row, (Notification)value);
		}else if(col == 1 && !visto.get(row)){
			notif.remove(getValueAt(row, 0));
			if (Application.getApplication().getCurrentUser() != null){
				Application.getApplication().getCurrentUser().setNotifications(notif);
			}else{
				Application.getApplication().getAdmin().setNotifications(notif);
			}
			fireTableCellUpdated(row,col);
		}
	}
	
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}
	
	public void setNotifications(ArrayList<Notification> not){
		notif = not;
		fireTableDataChanged();
	}

}
