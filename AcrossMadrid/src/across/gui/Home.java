package across.gui;

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
        
        EditFont.setSize(nombre, 20);
        EditFont.bold(nombre);

        /* colocacion */
        spring.putConstraint(SpringLayout.WEST, toInicio, 10, SpringLayout.WEST, this);
        spring.putConstraint(SpringLayout.NORTH, toInicio, 10, SpringLayout.NORTH, this);

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.NORTH, nombre, 20, SpringLayout.NORTH, this);

        this.add(toInicio);
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
    
}