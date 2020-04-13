package across.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase PanelInicio de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class PanelInicio extends JPanel{

    private JLabel title = new JLabel("ACROSS MADRID");
    private JButton registro = new JButton("REGISTRO");
    private JButton login = new JButton("INICIAR SESION");

    public PanelInicio(){
        SpringLayout spring = new SpringLayout();
        setLayout(spring);

        JPanel buttons = new JPanel(new GridLayout(3,1));
        buttons.add(registro);
        buttons.add(Box.createRigidArea(new Dimension(160,15)));
        buttons.add(login);

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttons, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.NORTH, buttons, 0, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, title, -50, SpringLayout.NORTH, buttons);
        
        title = EditFont.setSize(title, 32);
        title = EditFont.bold(title);
        this.add(title);
        this.add(buttons);
    }

    public void setControlRegistro(ActionListener c){
        registro.addActionListener(c);
    }

    public void setControlLogin(ActionListener c){
        login.addActionListener(c);
    }
    
}