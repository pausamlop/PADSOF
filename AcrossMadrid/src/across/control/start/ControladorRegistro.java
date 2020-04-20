package across.control.start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import across.gui.MainFrame;
import across.gui.start.PanelRegistro;
import across.model.application.Application;

/**
 * Clase ControladorRegistro
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorRegistro implements ActionListener{
	
    private PanelRegistro reg;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorRegistro
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorRegistro (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.reg = frame.getRegistro();
    }
    
    /**
     * Accion que se realiza cuando se pulsa el boton de 'continuar' en el panel de registro
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){

        String username = reg.getUsername().trim();
        String nif = reg.getNif().trim();
        String password = reg.getPassword();


        if (password.equals(""))
            JOptionPane.showMessageDialog(frame, "Introduzca contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            
        else if (username.equals(""))
            JOptionPane.showMessageDialog(frame, "Introduzca nombre de usuario válido", "Aviso", JOptionPane.WARNING_MESSAGE);
            
        else if (nif.equals(""))
            JOptionPane.showMessageDialog(frame, "Introduzca un NIF válido", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        else if (model.register(username, nif, password)) {
        	JOptionPane.showMessageDialog(frame, "Su cuenta está siendo validada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.frame.showPanel("inicio");
        }
        else
            JOptionPane.showMessageDialog(frame, "Usuario o NIF ya registrados", "Error", JOptionPane.ERROR_MESSAGE);
            


    }

}
