package across.control.notif;

import javax.swing.event.*;
import javax.swing.table.TableModel;

import across.gui.MainFrame;
import across.gui.notif.PanelNotifications;
import across.model.application.Application;
import across.model.notification.Notification;

/**
 * Clase ControladorVistoNotif
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorVistoNotif implements TableModelListener {

    private PanelNotifications panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorVistoNotif
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorVistoNotif (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNotif();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getColumn() == 1 && e.getFirstRow() >= 0){
            TableModel model = panel.getTable().getModel();
            model.setValueAt(true, e.getFirstRow(), 1);
            Notification not = (Notification)model.getValueAt(e.getFirstRow(), 0);
            if (Application.getApplication().getCurrentUser() != null)
                Application.getApplication().getCurrentUser().getNotifications().remove(not);
            else
                Application.getApplication().getAdmin().getNotifications().remove(not);
        }
    }

}