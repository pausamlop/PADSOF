package across.gui.start;

import java.awt.event.*;
import javax.swing.*;

/**
 * Clase abstracta HomeStart
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public abstract class HomeStart extends JPanel {

    private JButton atras = new JButton("Volver a inicio");
    protected SpringLayout spring = new SpringLayout();

    /**
     * Constructor de la clase abstracta HomeStart
     */
    public HomeStart(){
        setLayout(spring);

        /* colocacion */
        spring.putConstraint(SpringLayout.WEST, atras, 10, SpringLayout.WEST, this);
        spring.putConstraint(SpringLayout.NORTH, atras, 10, SpringLayout.NORTH, this);

        this.add(atras);
    }

    /**
     * Establece el control del boton de atras
     * 
     * @param c accion que activa el boton
     */
    public void setControlAtras(ActionListener c){
        atras.addActionListener(c);
    }

    /**
     * Vacia los campos de texto para que la proxima vez que acceda al panel
     * estos aparezan vacios
     */
    public abstract void emptyFields();
    
}