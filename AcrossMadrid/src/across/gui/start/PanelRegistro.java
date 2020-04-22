package across.gui.start;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import across.gui.EditFont;

@SuppressWarnings("serial")
public class PanelRegistro extends JPanel {
	
    private JLabel title = new JLabel("ACROSS MADRID");
    private JLabel desc = new JLabel("Registro");
    private JLabel usernameLabel = new JLabel("Nombre de usuario:", SwingConstants.RIGHT);
    private JTextField username = new JTextField(15);
    private JLabel nifLabel = new JLabel("NIF:", SwingConstants.RIGHT);
    private JTextField nif = new JTextField(15);
    private JLabel passwordLabel = new JLabel("Contraseña:", SwingConstants.RIGHT);
    private JPasswordField password = new JPasswordField(15);
    private JButton button = new JButton("Continuar");

    public PanelRegistro(){
        SpringLayout spring = new SpringLayout();
        setLayout(spring);

        JPanel centro = new JPanel(new GridLayout(4,2,10,5));
        
        /* anadir componentes a la ventana */
        centro.add(usernameLabel);
        centro.add(username);
        centro.add(nifLabel);
        centro.add(nif);
        centro.add(passwordLabel);
        centro.add(password);
        
        /* anadir restricciones */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, desc, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, centro, -30, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);

        spring.putConstraint(SpringLayout.NORTH, centro, -20, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, title, -80, SpringLayout.NORTH, centro);
        spring.putConstraint(SpringLayout.NORTH, desc, 10, SpringLayout.SOUTH, title);
        spring.putConstraint(SpringLayout.NORTH, button, 35, SpringLayout.SOUTH, centro);
        
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

    public String getNif(){
        return nif.getText();
    }


    public void setControlContinuar(ActionListener c){
        button.addActionListener(c);
    }
    
    
    
}