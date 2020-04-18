package across.control.user;

import across.model.application.Application;
import across.gui.*;
import across.gui.user.PanelNewProject;

import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorLoadImage implements ActionListener{

    private PanelNewProject panel;
    private MainFrame frame;
    private Application model;

    private static long idImage = 1;

    public ControladorLoadImage (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNewProject();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String sourcePath = null;
        String destPath = "src/across/gui/images/" + String.valueOf(idImage);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "png", "gif"));
        int opc = fileChooser.showOpenDialog(fileChooser);
		if (opc == JFileChooser.APPROVE_OPTION)
            sourcePath = fileChooser.getSelectedFile().getAbsolutePath();
        
        System.out.println("source = " + sourcePath);
        System.out.println("dest = " + destPath);

    }
}