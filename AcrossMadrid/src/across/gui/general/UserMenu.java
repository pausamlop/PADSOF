package across.gui.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import across.gui.EditFont;


/**
 * Clase UserMenu
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class UserMenu{

    private static JButton inicio = new JButton();
    private static JButton perfil = new JButton();
    private static JLabel nombre = new JLabel("ACROSS MADRID");

    public UserMenu(){
        ImageIcon inicioIcon = new ImageIcon("icons/home.png");
        Image inicioImg = inicioIcon.getImage();
        Image inicioScale = inicioImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        inicio.setIcon(new ImageIcon(inicioScale));
        
        ImageIcon perfilIcon = new ImageIcon("icons/perfil.png");
        Image perfilImg = perfilIcon.getImage();
        Image perfilScale = perfilImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        perfil.setIcon(new ImageIcon(perfilScale));

        EditFont.setSize(nombre, 20);
        EditFont.bold(nombre);
    }

    public static void install(JPanel panel){
        SpringLayout spring = (SpringLayout)panel.getLayout();

        spring.putConstraint(SpringLayout.WEST, inicio, 10, SpringLayout.WEST, panel);
        spring.putConstraint(SpringLayout.NORTH, inicio, 10, SpringLayout.NORTH, panel);

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        spring.putConstraint(SpringLayout.NORTH, nombre, 20, SpringLayout.NORTH, panel);

        spring.putConstraint(SpringLayout.EAST, perfil, -10, SpringLayout.EAST, panel);
        spring.putConstraint(SpringLayout.NORTH, perfil, 10, SpringLayout.NORTH, panel);

        panel.add(inicio);
        panel.add(nombre);
        panel.add(perfil);
    }

    /**
     * Establece el control del boton de inicio
     * 
     * @param c accion que activa el boton
     */
    public void setControlToInicio(ActionListener c){
        inicio.addActionListener(c);
    }

    /**
     * Establece el control del boton de perfil
     * 
     * @param c accion que activa el boton
     */
    public void setControlToPerfil(ActionListener c){
        perfil.addActionListener(c);
    }

}