package across.gui.menu;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import across.gui.EditFont;

/**
 * Clase abstracta Home
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public abstract class Home extends JPanel {

    protected JButton toInicio = new JButton();
    protected JButton notif = new JButton();
    private JLabel nombre = new JLabel("ACROSS MADRID");
    protected SpringLayout spring = new SpringLayout();

    /**
     * Constructor de la clase abstracta Home
     */
    public Home(){
        setLayout(spring);

        ImageIcon inicioIcon = new ImageIcon("icons/home.png");
        Image inicioImg = inicioIcon.getImage();
        Image inicioScale = inicioImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        toInicio.setIcon(new ImageIcon(inicioScale));

        ImageIcon notifIcon = new ImageIcon("icons/notif.png");
        Image notifImg = notifIcon.getImage();
        Image notifScale = notifImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        notif.setIcon(new ImageIcon(notifScale));
        
        EditFont.setSize(nombre, 20);
        EditFont.bold(nombre);

        /* colocacion */
        spring.putConstraint(SpringLayout.WEST, toInicio, 10, SpringLayout.WEST, this);
        spring.putConstraint(SpringLayout.NORTH, toInicio, 10, SpringLayout.NORTH, this);
        spring.putConstraint(SpringLayout.EAST, notif,-60, SpringLayout.EAST, this);
        spring.putConstraint(SpringLayout.NORTH, notif, 10, SpringLayout.NORTH, this);

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.NORTH, nombre, 20, SpringLayout.NORTH, this);

        this.add(toInicio);
        this.add(notif);
        this.add(nombre);
    }

    /**
     * Establece el control del boton de inicio
     * 
     * @param c accion que activa el boton
     */
    public void setControlToInicio(ActionListener c){
        toInicio.addActionListener(c);
    }

    /**
     * Establece el control del boton de notificaciones
     * 
     * @param c accion que activa el boton
     */
    public void setControlToNotif(ActionListener c){
        notif.addActionListener(c);
    }
    
}