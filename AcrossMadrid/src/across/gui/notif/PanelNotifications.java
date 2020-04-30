package across.gui.notif;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import across.gui.EditFont;
import across.gui.menu.HomeUser;
import across.model.application.Application;
import across.model.notification.Notification;

@SuppressWarnings("serial")
public class PanelNotifications extends HomeUser {

    JLabel titulo = new JLabel("Notificaciones");
    JTable table;

    public PanelNotifications(){
        AbstractTableModel model = new TablaNotif();
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        /* configuraciones de la tabla */
        table.setPreferredScrollableViewportSize(new Dimension(400, 280));
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.setFillsViewportHeight(true);
        table.setOpaque(false);


        EditFont.setSize(titulo, 25);

        /* colocacion */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, titulo, 30, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, scroll, 0, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, titulo, -25, SpringLayout.NORTH, scroll);

        this.add(scroll);
        this.add(titulo);
    }

    public void setNotifications(ArrayList<Notification> not){
        ((TablaNotif)table.getModel()).setNotifications(not);
        if (Application.getApplication().getCurrentAdmin()){
            toPerfil.setVisible(false);
        }else{
            toPerfil.setVisible(true);
        }
    }
}