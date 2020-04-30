package across.gui.menu;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import across.gui.*;

/**
 * Clase abstracta HomeUser
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public abstract class HomeUser extends Home {

    protected JButton toPerfil = new JButton();

    /**
     * Constructor de la clase abstracta HomeUser
     */
    public HomeUser(){
               
        ImageIcon perfilIcon = new ImageIcon("icons/perfil.png");
        Image perfilImg = perfilIcon.getImage();
        Image perfilScale = perfilImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        toPerfil.setIcon(new ImageIcon(perfilScale));

        /* colocacion */
        spring.putConstraint(SpringLayout.EAST, toPerfil, -10, SpringLayout.EAST, this);
        spring.putConstraint(SpringLayout.NORTH, toPerfil, 10, SpringLayout.NORTH, this);

        this.add(toPerfil);
    }

    /**
     * Establece el control del boton de perfil
     * 
     * @param c accion que activa el boton
     */
    public void setControlToPefil(ActionListener c){
        toPerfil.addActionListener(c);
    }

    
}