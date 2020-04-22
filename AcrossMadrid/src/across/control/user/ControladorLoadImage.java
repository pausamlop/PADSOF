package across.control.user;

import across.model.application.Application;
import across.gui.*;
import across.gui.user.PanelNewProject;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase ControladorLoadImage
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorLoadImage implements ActionListener{

    private PanelNewProject panel;
    private MainFrame frame;
    private Application model;
    
    /**
     * Constructor de la clase ControladorLoadImage
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorLoadImage (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNewProject();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'cargar imagen' en el panel de crear proyecto
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        BufferedImage image;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png", "gif"));
        int opc = fileChooser.showOpenDialog(fileChooser);
		if (opc == JFileChooser.APPROVE_OPTION){
            try{
                File f = fileChooser.getSelectedFile();
                image = ImageIO.read(f);
                panel.setImage(image);
                JOptionPane.showMessageDialog(frame, "La imagen " + f.getName() + " se ha guardado correctamente", "Aviso", JOptionPane.WARNING_MESSAGE);
            }catch(IOException exc){
                exc.printStackTrace();
            }
        }

    }
}