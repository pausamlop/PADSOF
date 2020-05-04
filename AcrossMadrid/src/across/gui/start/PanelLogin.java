package across.gui.start;

import javax.swing.*;

import across.gui.EditFont;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase PanelLogin
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelLogin extends HomeStart {

    private JLabel title = new JLabel("ACROSS MADRID");
    private JLabel desc = new JLabel("Iniciar sesión");
    private JLabel usernameLabel = new JLabel("Nombre de usuario:", SwingConstants.RIGHT);
    private JTextField username = new JTextField(15);
    private JCheckBox checkAdmin = new JCheckBox("Soy administrador");
    private JLabel passwordLabel = new JLabel("Contraseña:", SwingConstants.RIGHT);
    private JPasswordField password = new JPasswordField(15);
    private JButton button = new JButton("Continuar");

    /**
     * Constructor de la clase PanelLogin
     */
    public PanelLogin(){
        super();

        JPanel centro = new JPanel(new GridLayout(4,2,10,5));
        centro.add(usernameLabel);
        centro.add(username);
        centro.add(Box.createRigidArea(new Dimension(5,1)));
        centro.add(checkAdmin);
        centro.add(Box.createRigidArea(new Dimension(5,0)));
        centro.add(Box.createRigidArea(new Dimension(5,0)));
        centro.add(passwordLabel);
        centro.add(password);

        /* anadir funcionalidad al checkBox */
        setControlCheckAdmin(); 

        /* anadir restricciones de colocacion de los elementos */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, desc, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, centro, -30, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);

        spring.putConstraint(SpringLayout.NORTH, centro, -20, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, title, -80, SpringLayout.NORTH, centro);
        spring.putConstraint(SpringLayout.NORTH, desc, 10, SpringLayout.SOUTH, title);
        spring.putConstraint(SpringLayout.NORTH, button, 35, SpringLayout.SOUTH, centro);

        /* modificacion de fuentes */
        EditFont.setSize(title, 32);
        EditFont.bold(title);
        EditFont.setSize(desc, 20);
        
        this.add(title);
        this.add(desc);
        this.add(centro);
        this.add(button);

    }

    /**
     * Devuelve el nombre de usuario introducido por usuario
     * 
     * @return nombre de usuario
     */
    public String getUsername(){
        return username.getText();
    }

    /**
     * Devuelve la contrasena introducida por el usuario
     * 
     * @return contrasena
     */
    public String getPassword(){
        return new String(password.getPassword());
    }

    /**
     * Devuelve si la opcion de administrador ha sido seleccionada
     * 
     * @return true si la opcion de administrador ha sido seleccionada, false en otro caso
     */
    public boolean isAdmin(){
        return checkAdmin.isSelected();
    }

    /**
    * Establece el control de seleccionar o deseleccionar el checkbox
    * 
    */
    public void setControlCheckAdmin(){
        checkAdmin.addActionListener(e -> {
            if(isAdmin()){
                username.setText("administrador");
                username.setEnabled(false);
            }
            else{
                username.setText("");
                username.setEnabled(true);
            }
        });
    }

    /**
     * Establece el control del boton de continuar
     * 
     * @param c accion que activa el boton
     */
    public void setControlContinuar(ActionListener c){
        button.addActionListener(c);
    }

    /**
     * Vacia los campos de texto para que la proxima vez que acceda al panel,
     * estos aparezan vacios
     */
	public void emptyFields() {
        username.setText("");
        username.setEnabled(true);
        checkAdmin.setSelected(false);
        password.setText("");
	}

}