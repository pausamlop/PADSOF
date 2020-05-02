package across.gui.admin;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import across.gui.EditFont;
import across.gui.MainFrame;
import across.model.user.User;

public class FrameAdminUsuariosBloq extends JDialog {
	User userToBloq;
    
    JLabel label1;
    JLabel label2;
    
    JButton enviar;
    JButton cancelar;
    
    JTextArea mensaje;

    public FrameAdminUsuariosBloq(User userToBloq, MainFrame frame, String title, Boolean bool)
    {
    	super(frame, title, bool);
    	
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        
        this.userToBloq = userToBloq;
        
        /*Area para escribir el mensaje de bloqueo*/
        SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
        
        mensaje = new JTextArea(8,40);
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(mensaje);
        
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, scrollPane, 125, SpringLayout.VERTICAL_CENTER, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 230, SpringLayout.HORIZONTAL_CENTER, this);

        /*Etiquetas*/
        label1 = new JLabel("Bloqueo de usuario " + userToBloq.getUsername());
        EditFont.setSize(label1, 15);
        
        label2 = new JLabel("Escriba un mensaje con el motivo del bloqueo:");
        
        layout.putConstraint(SpringLayout.SOUTH, label1, -30, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label1, 230, SpringLayout.HORIZONTAL_CENTER, this);
        
        layout.putConstraint(SpringLayout.SOUTH, label2, -10, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label2, 230, SpringLayout.HORIZONTAL_CENTER, this);
        
        /*Botones*/
        enviar = new JButton("Enviar");
        cancelar = new JButton("Cancelar");
        
        layout.putConstraint(SpringLayout.NORTH, cancelar, 10, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cancelar, 120, SpringLayout.HORIZONTAL_CENTER, this);
        
        layout.putConstraint(SpringLayout.NORTH, enviar, 10, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, enviar, 340, SpringLayout.HORIZONTAL_CENTER, this);
        
        this.add(enviar);
        this.add(cancelar);
        this.add(label1);
        this.add(label2);
        this.add(scrollPane);
    }
    
    public void setControlFrameBloqEnviar(ActionListener c) {
    	enviar.addActionListener(c);
    }
    
    public User getUserToBloq() {
    	return userToBloq;
    }
    
    public String getMensajeBloq() {
    	return mensaje.getText();
    }
}