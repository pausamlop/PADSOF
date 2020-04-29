package across.gui.user;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import across.gui.EditFont;
import across.model.application.Application;
import across.model.user.User;

/**
 * Clase PanelPerfil
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelPerfil extends HomeUser{
	
    ButtonGroup buttonGroup;
	JRadioButton buttonPCreados = new JRadioButton("Proyectos creados");
	JRadioButton buttonPApoyados = new JRadioButton("Proyectos apoyados");
	JRadioButton buttonPSeguidos = new JRadioButton("Proyectos seguidos");
	JRadioButton buttonCCreados = new JRadioButton("Colectivos creados");
	JRadioButton buttonCMiembro = new JRadioButton("<html>Colectivos a los que <br />pertenezco</html>");
	
    private JLabel title = new JLabel("Mi Perfil");
    private JLabel nombre = new JLabel();

	JTable pCreados;
	JTable pApoyados;
	JTable pSeguidos;
	JTable cCreados;
	JTable cMiembros;

    public PanelPerfil(){
    	super();
		this.setLayout(spring);
        
        toPerfil.setVisible(false);
        
        JPanel options = new JPanel(new GridLayout(20,10, 0, 10));
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonPCreados);
        buttonGroup.add(buttonPApoyados);
        buttonGroup.add(buttonPSeguidos);
        buttonGroup.add(buttonCCreados);
        buttonGroup.add(buttonCMiembro);
        
        options.add(buttonPCreados);
        options.add(buttonPApoyados);
        options.add(buttonPSeguidos);
        options.add(buttonCCreados);
        options.add(buttonCMiembro);
        
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, -220, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, title, -150, SpringLayout.VERTICAL_CENTER, this);
        
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, options, -220, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, options, 400, SpringLayout.VERTICAL_CENTER, this);
        
	    EditFont.setSize(title, 24);
        EditFont.bold(title);
        
        this.add(options);
        this.add(title);
        this.add(nombre);
        
    }
    
    /**
     * Actualiza el panel
     */
    public void updateData(){
    	nombre.setText(Application.getApplication().getCurrentUser().getUsername()); 
    }

}