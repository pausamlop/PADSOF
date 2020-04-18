package across.control.user;

import across.model.application.Application;
import across.gui.*;
import across.gui.start.PanelInicio;

import java.awt.event.*;

public class ControladorUserCrearProyecto implements ActionListener{

    private PanelInicio inicio;
    private MainFrame frame;
    private Application model;

    public ControladorUserCrearProyecto (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.inicio = frame.getInicio();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.showPanel("nuevoProyecto");
    }
}