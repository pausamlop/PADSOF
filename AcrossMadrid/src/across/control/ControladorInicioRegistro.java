package across.control;

import across.model.application.Application;
import across.gui.*;

import java.awt.event.*;
import javax.swing.*;

public class ControladorInicioRegistro implements ActionListener{

    private PanelInicio inicio;
    private MainFrame frame;
    private Application model;

    public ControladorInicioRegistro (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicio = frame.getInicio();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("registro");
    }
}