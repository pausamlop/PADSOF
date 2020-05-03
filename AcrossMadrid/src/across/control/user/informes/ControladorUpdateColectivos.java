package across.control.user.informes;

import across.model.application.Application;
import across.model.user.Collective;
import across.gui.*;
import across.gui.user.PanelInformes;

import java.awt.event.*;

/**
 * Clase ControladorUpdateColectivos
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorUpdateColectivos implements ActionListener{

    private PanelInformes panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorUpdateColectivos
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorUpdateColectivos (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getInformes();
    }

    /**
     * Accion que se realiza cuando se selecciona un colectivo en el comboBox del panel de informes
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        Collective col = panel.getColectivo();
        if (col == null){
            //JOptionPane.showMessageDialog(frame, "Debe seleccionar un colectivo para generar su informe de afinidad", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        panel.updateCollectives(model.affinityReport(col));
        this.frame.showPanel("informes");
    }
}