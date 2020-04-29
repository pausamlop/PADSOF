package across.gui.general;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import across.gui.EditFont;
import across.gui.user.HomeUser;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.enumerations.typeSocial;
import across.model.project.*;
import across.model.user.Collective;
import across.model.user.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase PanelDisplayProject de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelDisplayCollective extends HomeUser{
	
	Collective collective;
	
    protected JLabel nombre = new JLabel();
    protected JLabel descLabel = new JLabel("Descripcion:");
    protected JTextArea desc = new JTextArea(5,25);
    
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("JERARQUÍA DEL COLECTIVO");
    protected JTree tree = new JTree(root);
    
    protected JButton join = new JButton("Unirse");
    

   
    /**
     * Constructor de la clase PanelDisplayCollective
     */
    public PanelDisplayCollective(){
    	
    	
    	
    	setLayout(spring);
    	
        descProperties();
        
        tree.setVisible(true);
    	
        EditFont.bold(nombre);
        EditFont.setSize(nombre, 28);
        EditFont.bold(descLabel);
        
        JScrollPane scroll = new JScrollPane(tree);
        
        scroll.setPreferredSize(new java.awt.Dimension(220, 220));
    	
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, -140, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, descLabel, 40, SpringLayout.HORIZONTAL_CENTER, nombre);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, descLabel, 90, SpringLayout.VERTICAL_CENTER, nombre);
        
        spring.putConstraint(SpringLayout.WEST, desc, 0, SpringLayout.WEST, descLabel);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, desc, 70, SpringLayout.VERTICAL_CENTER, descLabel);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, -160, SpringLayout.HORIZONTAL_CENTER, nombre);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, scroll, 180, SpringLayout.VERTICAL_CENTER, nombre);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, join, 30, SpringLayout.HORIZONTAL_CENTER, nombre);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, join, 270, SpringLayout.VERTICAL_CENTER, nombre);
        
        
  
        this.add(scroll);
        this.add(nombre);
        this.add(descLabel);
        this.add(desc);
        this.add(join);
    }
    
    
   
    
    
	private void setTree(Collective c, DefaultMutableTreeNode node) {
	
        for (Collective aux: c.getChildren()) {
        	
        	DefaultMutableTreeNode n = new DefaultMutableTreeNode(aux.getName());
        	node.add(n);
        	setTree(aux, n);
        	
        }
        
        return;
		   
	}
    
    
    
    /**
     * Establece las propiedades del campo descripcion para que se vea como un JLabel en varias lineas
     */
    private void descProperties() {
        desc.setEditable(false);  
        desc.setCursor(null);  
        desc.setOpaque(false);  
        desc.setFocusable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
    }
    
    
    /**
     * Establece el colectivo a visualizar en este panel
     * 
     * @param project colectivo a visualizar
     */
    public void setCollective(Collective col) {

    	this.collective = col;
    	updateCollectiveInfo();
    	//updateButtons();

    }
    
    
    /**
     * Actualiza la informacion que muestra el panel en funcion del colectivo
     */
	private void updateCollectiveInfo() {
		nombre.setText(collective.getName());
		desc.setText(collective.getDescription());
		

        Collective c = collective;
        while (c.getParent() != null) c = c.getParent();

    	DefaultMutableTreeNode n = new DefaultMutableTreeNode(c.getName());
    	root.add(n);
        setTree(c, n);

	    }
	

}
