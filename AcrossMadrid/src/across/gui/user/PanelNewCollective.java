package across.gui.user;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import across.gui.EditFont;

public class PanelNewCollective extends JPanel {
	
    private JLabel title = new JLabel("Nuevo colectivo");
    private JLabel nameLabel = new JLabel("NIF:", SwingConstants.RIGHT);
    private JTextField name= new JTextField(15);
    private JLabel descriptionLabel = new JLabel("Descripción:", SwingConstants.RIGHT);
    private JTextField description = new JTextField(15);
    private JButton button = new JButton("Continuar");

    
    public PanelNewCollective(){
    	
        SpringLayout spring = new SpringLayout();
        setLayout(spring);

        JPanel centro = new JPanel(new GridLayout(4,2,10,5));
        
        /* anadir componentes a la ventana */
        centro.add(nameLabel);
        centro.add(name);
        centro.add(descriptionLabel);
        centro.add(description);
        
        /* anadir restricciones */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, centro, -30, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);

        spring.putConstraint(SpringLayout.NORTH, centro, -20, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, title, -80, SpringLayout.NORTH, centro);
        spring.putConstraint(SpringLayout.NORTH, button, 35, SpringLayout.SOUTH, centro);

	    title = EditFont.setSize(title, 20);
        title = EditFont.bold(title);
        this.add(title);
        this.add(centro);
        this.add(button);
    }
    
    public String getName(){
        return name.getText();
    }

    public String getDescription(){
        return description.getText();
    }

    public void setControlContinuar(ActionListener c){
        button.addActionListener(c);
    }
}
