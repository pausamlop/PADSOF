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
		if (row < 0 || row >= notif.size())
			return null;

		if(col == 0) {
			return notif.get(row);
		}else if (col == 1){
			return visto.get(row);
		}
		return null;
	}

	public void setValueAt(Object value, int row, int col){
		if(col == 1 && !visto.get(row)){
			visto.set(row, (Boolean)value);
			// notif.remove(getValueAt(row, 0));
			// if (Application.getApplication().getCurrentUser() != null){
			// 	Application.getApplication().getCurrentUser().setNotifications(notif);
			// }else{
			// 	Application.getApplication().getAdmin().setNotifications(notif);
			// }
			fireTableCellUpdated(row,col);
		}
	}
	
	public String getColumnName(int c) {
		return titulos[c];
	}
	
	public void addRow(Notification not, boolean visto) {
		notif.add(not);
		this.visto.add(visto);
		fireTableCellUpdated(notif.size(), 0);
		fireTableCellUpdated(notif.size(), 1);
	}
	
	public boolean isCellEditable(int row, int col) {
		return col == 1;
	}
	
	public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}
	
	public void setNotifications(ArrayList<Notification> not){
		deleteData();
		notif.addAll(not);
		for (int i = 0; i < notif.size(); i++)
			visto.add(false);
		fireTableRowsInserted(0, notif.size() - 1);
	}
	
	private void deleteData() {
		int rows = getRowCount();
        if (rows == 0) {
            return;
        }
        notif.clear();
        visto.clear();
        fireTableRowsDeleted(0, rows - 1);
	}

}
