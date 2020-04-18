package across.control.start;

import across.model.application.Application;
import across.gui.*;
import across.gui.start.PanelLogin;

import java.awt.event.*;
import javax.swing.*;

public class ControladorLogin implements ActionListener{

    private PanelLogin login;
    private MainFrame frame;
    private Application model;

    public ControladorLogin (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.login = frame.getLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String username = login.getUsername().trim();
        String password = login.getPassword();

        if (login.isAdmin()){
            if (password.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (model.getAdmin().login(password))
                this.frame.showPanel("inicioAdmin");
            else
                JOptionPane.showMessageDialog(frame, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (username.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca nombre de usuario válido", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (password.equals(""))
                JOptionPane.showMessageDialog(frame, "Introduzca contraseña", "Aviso", JOptionPane.WARNING_MESSAGE);
            else if (model.login(username, password))
                this.frame.showPanel("inicioUser");
            else
                JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}