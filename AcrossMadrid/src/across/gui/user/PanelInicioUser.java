package across.gui.user;

import javax.swing.*;
import java.awt.event.*;

/**
 * Clase PanelInicioUser de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelInicioUser extends JPanel{

    private JButton crearProyecto = new JButton("Crear Proyecto");
    
    public PanelInicioUser(){
        this.add(crearProyecto);
    }
    
    public void setControlCrearProyecto(ActionListener c){
        crearProyecto.addActionListener(c);
    }
}