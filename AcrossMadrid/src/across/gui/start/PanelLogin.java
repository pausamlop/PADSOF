package across.gui.start;

import javax.swing.*;

import across.gui.EditFont;

import java.awt.*;
import java.awt.event.*;

public class PanelLogin extends JPanel {

    private JLabel title = new JLabel("ACROSS MADRID");
    private JLabel desc = new JLabel("Iniciar sesión");
    private JLabel usernameLabel = new JLabel("Nombre de usuario:", SwingConstants.RIGHT);
    private JTextField username = new JTextField(15);
    private JCheckBox checkAdmin = new JCheckBox("Soy administrador");
    private JLabel passwordLabel = new JLabel("Contraseña:", SwingConstants.RIGHT);
    private JPasswordField password = new JPasswordField(15);
    private JButton button = new JButton("Continuar");

    public PanelLogin(){
        SpringLayout spring = new SpringLayout();
        setLayout(spring);

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
        title = EditFont.setSize(title, 32);
        title = EditFont.bold(title);
        desc = EditFont.setSize(desc, 20);
        
        this.add(title);
        this.add(desc);
        this.add(centro);
        this.add(button);

    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return new String(password.getPassword());
    }

    public boolean isAdmin(){
        return checkAdmin.isSelected();
    }

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

    public void setControlContinuar(ActionListener c){
        button.addActionListener(c);
    }

}