package across.gui.notif;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import across.gui.EditFont;
import across.gui.menu.HomeUser;
import across.model.application.Application;
import across.model.notification.Notification;

/**
 * Clase PanelNotifications
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelNotifications extends HomeUser {

    JLabel titulo = new JLabel("Notificaciones");
    JTable table;

    /**
     * Constructor de la clase PanelNotifications
     * 
     */
    public PanelNotifications(){
        AbstractTableModel model = new TablaNotif();
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        /* configuraciones de la tabla */
        table.setPreferredScrollableViewportSize(new Dimension(500, 260));
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.setFillsViewportHeight(true);
        table.setOpaque(false);


        EditFont.setSize(titulo, 25);

        /* colocacion */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, scroll, 20, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, titulo, -25, SpringLayout.NORTH, scroll);

        this.add(scroll);
        this.add(titulo);
    }

    /**
     * Devuelve  la tabla
     * @return tabla
     */
    public JTable getTable(){
        return table;
    }

    /**
     * Establece las nostificaciones a mostrar en la tabla
     * 
     * @param not lista de notificaciones
     */
    public void setNotifications(ArrayList<Notification> not){
        ((TablaNotif)table.getModel()).setNotifications(not);
    }
    
    /**
     * Actualiza la informacion del panel en funcion del usuario conectado
     */
    public void update() {
    	if (Application.getApplication().getCurrentUser() == null){
            toPerfil.setVisible(false);
        }else{
            toPerfil.setVisible(true);
        }
    }

    /**
     * Establece el control de una notificacion vista
     * @param e controlador de la accion
     */
    public void setControlVisto(TableModelListener e){
        table.getModel().addTableModelListener(e);
    }
}