package across.gui.user;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.*;

import across.gui.EditFont;
import across.model.application.Application;
import across.model.project.Project;
import across.model.user.Collective;

/**
 * Clase PanelNewColective
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelNewCollective extends HomeUser{
	
    private int anchoTextField = 20;
	
    private JLabel title = new JLabel("Nuevo Colectivo");
    private JLabel nameLabel = new JLabel("Nombre:", SwingConstants.RIGHT);
    private JTextField name= new JTextField(anchoTextField);
    private JLabel descriptionLabel = new JLabel("Descripción:", SwingConstants.RIGHT);
    private JTextArea description = new JTextArea(4,anchoTextField);
    private JButton button = new JButton("Crear Colectivo");
    
    /* Hijo o independiente */
    ButtonGroup buttonGroup;
    private JRadioButton indep = new JRadioButton("Colectivo independiente");
    private JRadioButton child = new JRadioButton("Elegir colectivo padre");
    private JComboBox<Collective> c = new JComboBox<>();
    private JScrollPane descPane;
    private JPanel colectivos = new JPanel(new CardLayout());
    


    
    public PanelNewCollective(){
        super();
    	
        setLayout(spring);
        
        JPanel centro1 = new JPanel(new FlowLayout(FlowLayout.LEFT));


        /* anadir componentes a la ventana */
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(indep);
        buttonGroup.add(child);
        
        
        colectivos.add(c);
        colectivos.setVisible(false);
        
        setControlHijo();
        setControlIndep();
        
        description.setLineWrap(true);
        descPane = new JScrollPane(description);
        
        centro1.add(nameLabel);
        centro1.add(name);
        

        /* anadir restricciones */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, title, -150, SpringLayout.VERTICAL_CENTER, this);
        

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, centro1, -10, SpringLayout.HORIZONTAL_CENTER, title);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, centro1, 70, SpringLayout.VERTICAL_CENTER, title);
        
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, indep, -5, SpringLayout.HORIZONTAL_CENTER, centro1);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, indep, 40, SpringLayout.VERTICAL_CENTER, centro1);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, child, -13, SpringLayout.HORIZONTAL_CENTER, centro1);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, child, 65, SpringLayout.VERTICAL_CENTER, centro1);
        
        spring.putConstraint(SpringLayout.WEST, colectivos, -235, SpringLayout.EAST, centro1);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, colectivos, 93, SpringLayout.VERTICAL_CENTER, centro1);     
        
        
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, descriptionLabel, -138, SpringLayout.HORIZONTAL_CENTER, centro1);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, descriptionLabel, 130, SpringLayout.VERTICAL_CENTER, centro1);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, descPane, 167, SpringLayout.HORIZONTAL_CENTER, descriptionLabel);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, descPane, 26, SpringLayout.VERTICAL_CENTER, descriptionLabel);

        
        

        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.NORTH, button, 210, SpringLayout.SOUTH, centro1);



	    EditFont.setSize(title, 24);
        EditFont.bold(title);
        this.add(descPane);
        this.add(descriptionLabel);
        this.add(indep);
        this.add(child);
        this.add(colectivos);
        this.add(title);
        this.add(button);
        this.add(centro1);

    }
    


    
    
    /**
     * Establece el control de la seleccion del RadioButton de colectivo independiente
     */
    public void setControlIndep(){
        indep.addActionListener(e -> colectivos.setVisible(false));
    }

    /**
     * Establece el control de la seleccion del RadioButton de colectivo hijo
     */
    public void setControlHijo(){
        child.addActionListener(e -> colectivos.setVisible(true));
    }
    
    public void setControlCreateCollective(ActionListener c){
        button.addActionListener(c);
    }
    
    
    /**
     * Devuelve si el colectivo es independiente 
     * 
     * @return true si es indep, false en otro caso
     */
    public boolean isIndependent(){
        return indep.isSelected();
    }
    
    /**
     * Devuelve si el colectivo tiene padre
     * 
     * @return true si es hijo, false en otro caso
     */
    public boolean isChild(){
        return child.isSelected();
    }
    
    /**
     * Establece el contenido del JComboBox de los colectivos creador por el usuario actual
     * 
     * @param col colectivos creados por el usuario actual
     */
    public void setColectivos(Collection<Collective> col){
        c.removeAllItems();
        for (Collective aux: col)
            c.addItem(aux);
    }
    
    
    
    /**
     * Devuelve el padre
     * 
     * @return colectivo
     */
    public Collective getColectivo(){
        return (Collective)c.getSelectedItem();
    }
    
    /**
     * Devuelve el nombre
     * 
     * @return nombre
     */
    public String getName(){
        return name.getText();
    }

    /**
     * Devuelve la descripcion
     * 
     * @return descripcion
     */
    public String getDescription(){
        return description.getText();
    }
    
    
    /**
     * Vacia los campos de texto para que la proxima vez que se cree un proyecto,
     * estos aparezan vacios
     */
	public void emptyFields() {
        buttonGroup.clearSelection();
        c.removeAllItems();
        colectivos.setVisible(false);
        name.setText("");
        description.setText("");
	}


}
