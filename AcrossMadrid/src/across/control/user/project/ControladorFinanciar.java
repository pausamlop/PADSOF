package across.control.user.project;

import across.model.application.Application;
import across.gui.*;
import across.gui.general.PanelDisplayProject;

import java.awt.event.*;

import javax.swing.JOptionPane;

/**
 * Clase ControladorFinanciar
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorFinanciar implements ActionListener{

    private PanelDisplayProject panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorFinanciar
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorFinanciar (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getDisplayProject();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'seguir' en el panel de visalizar proyecto
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if (panel.getProject().sendToFinance()){
            String mssg = "El proyecto " + panel.getProject() + " ha sido enviado a financiar";
            mssg += "\nEn breve recibira una notificacion con la decisión del Ayuntamiento";
            JOptionPane.showMessageDialog(frame, mssg, "Financiacion", JOptionPane.INFORMATION_MESSAGE);
        }
        panel.update();
    }
}