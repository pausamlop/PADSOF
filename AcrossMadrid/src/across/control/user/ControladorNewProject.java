package across.control.user;

import across.model.application.Application;
import across.model.enumerations.typeSocial;
import across.model.project.InfraestructureProject;
import across.model.project.SocialProject;
import across.gui.*;
import across.gui.user.PanelNewProject;

import java.awt.event.*;

import javax.swing.*;

public class ControladorNewProject implements ActionListener{

    private PanelNewProject panel;
    private MainFrame frame;
    private Application model;

    public ControladorNewProject (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNewProject();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String name = panel.getName().trim();
        double cost = panel.getCost();
        String desc = panel.getDescription().trim();

        if (name.equals(""))
            JOptionPane.showMessageDialog(frame, "Debe introducir un nombre para el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
        else if (cost <= 0)
            JOptionPane.showMessageDialog(frame, "Debe introducir un coste de proyecto valido", "Aviso", JOptionPane.WARNING_MESSAGE);
        else if (desc.equals(""))
            JOptionPane.showMessageDialog(frame, "Debe introducir una descripcion para el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);

        else if (panel.isInfraestructura()){
            String distrito = panel.getDistrito();
            // foto
            model.addNewProject(new InfraestructureProject(name, desc, cost, "/src/across/gui/images/prueba.png", distrito, model.getCurrentUser()));
            JOptionPane.showMessageDialog(frame, "Su propuesta de proyecto se ha enviado al adminstrador.\nUna vez validado estara disponible para ser votado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            frame.showPanel("inicioUser");
        }
        else if (panel.isSocial()){
            String grupo = panel.getGroup().trim();
            String ambitoStr = panel.getAmbito();
            if (grupo.equals(""))
                JOptionPane.showMessageDialog(frame, "Debe introducir el grupo social al que va dirigido el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (ambitoStr.equals("Ambito"))
                JOptionPane.showMessageDialog(frame, "Debe elegir un ambito de proyecto valido", "Aviso", JOptionPane.WARNING_MESSAGE);
            else{
                typeSocial tipo = (ambitoStr.equals("Nacional"))?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                model.addNewProject(new SocialProject(name, desc, cost, grupo, tipo, model.getCurrentUser()));
                JOptionPane.showMessageDialog(frame, "Su propuesta de proyecto se ha enviado al adminstrador.\nUna vez validado estara disponible para ser votado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                frame.showPanel("inicioUser");
            }
        }
        else
            JOptionPane.showMessageDialog(frame, "Debe elegir tipo de proyecto: de infraestructura o social", "Aviso", JOptionPane.WARNING_MESSAGE);
    


    }
}