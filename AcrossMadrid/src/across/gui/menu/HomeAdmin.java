package across.gui.menu;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase abstracta HomeAdmin
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public abstract class HomeAdmin extends Home {

    protected JButton logout = new JButton();

    /**
     * Constructor de la clase abstracta HomeAdmin
     */
    public HomeAdmin(){
        toInicio.setVisible(false);

        ImageIcon icon = new ImageIcon("icons/logout.png");
        Image img = icon.getImage();
        Image scale = img.getScaledInstance(20,30,Image.SCALE_SMOOTH);
        logout.setIcon(new ImageIcon(scale));

        /* colocacion */
        spring.putConstraint(SpringLayout.WEST, logout, 10, SpringLayout.WEST, this);
        spring.putConstraint(SpringLayout.NORTH, logout, 10, SpringLayout.NORTH, this);

        this.add(logout);
    }

    /**
     * Establece el control del boton de perfil
     * 
     * @param c accion que activa el boton
     */
    public void setControlLogout(ActionListener c){
        logout.addActionListener(c);
    }

    
}