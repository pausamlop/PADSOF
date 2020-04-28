package across.gui.general;

import javax.imageio.ImageIO;
import javax.swing.*;

import across.gui.EditFont;
import across.model.application.Application;
import across.model.enumerations.projectState;
import across.model.enumerations.typeSocial;
import across.model.project.*;
import across.model.user.User;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Clase PanelDisplayProject de la interfaz
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelDisplayProject extends JPanel{
	
	Project project;

    protected JLabel nombre = new JLabel();
    protected JLabel estado = new JLabel();
    protected JLabel votos = new JLabel();
    protected JLabel costeLabel = new JLabel("Coste:");
    protected JLabel coste = new JLabel();
    protected JLabel tipoLabel = new JLabel("Tipo:");
    protected JLabel tipo = new JLabel();
    protected JLabel distritoGrupoLabel = new JLabel();
    protected JLabel distritoGrupo = new JLabel();
    protected JLabel descLabel = new JLabel("Descripcion:");
    protected JTextArea desc = new JTextArea(5,25);
    protected JLabel image = new JLabel();

    private JPanel buttons = new JPanel(new CardLayout());
    /* botones usuario */
    protected JButton votar = new JButton("Votar");
    protected JPanel seguirPanel;
    protected JButton seguir = new JButton("Seguir");
    protected JButton dejarSeguir = new JButton("Dejar de seguir");
    /* botones admin */
    protected JButton aceptar = new JButton("Aceptar");
    protected JButton rechazar = new JButton("Rechazar");
    
    protected SpringLayout spring = new SpringLayout();


    /**
     * Constructor de la clase PanelDisplayProject
     */
    public PanelDisplayProject(){
    	setLayout(spring);
    	
    	//UserMenu.install(this);
    	
    	createButtonsPanel();
        descProperties();
        
        image.setPreferredSize(new Dimension(160,160));
    	
        /* estilo de fuentes */
        EditFont.bold(nombre);
        EditFont.setSize(nombre, 28);
        EditFont.bold(estado);
        estado.setForeground(Color.BLUE);
        votos.setForeground(Color.GRAY);
        EditFont.bold(costeLabel);
        EditFont.bold(tipoLabel);
        EditFont.bold(distritoGrupoLabel);
        EditFont.bold(descLabel);
    	
    	this.add(nombre);
    	this.add(estado);
    	this.add(votos);
    	this.add(costeLabel);
    	this.add(coste);
    	this.add(tipoLabel);
    	this.add(tipo);
    	this.add(distritoGrupoLabel);
    	this.add(distritoGrupo);
    	this.add(descLabel);
        this.add(desc);
        this.add(image);
    	this.add(buttons);
    }

    /**
     * Crea dos paneles diferentes de botones, dependiento si el proyecto esta siendo 
     * visualizado por un usuario o por el administrador
     */
    private void createButtonsPanel() {
        /* botones del usuario */
        JPanel userButtons = new JPanel();
        ((FlowLayout)userButtons.getLayout()).setHgap(30);

        /* nuevo cardlayout de seguir/dejar de seguir */
        seguirPanel = new JPanel(new CardLayout());
        JPanel userNoSigue = new JPanel();
        JPanel userSigue = new JPanel();
        userNoSigue.add(seguir);
        userSigue.add(dejarSeguir);
        seguirPanel.add(userNoSigue,"noSigue");
        seguirPanel.add(userSigue,"sigue");
        
    	userButtons.add(seguirPanel);
    	userButtons.add(votar);
        
        /* botones del administrador */
    	JPanel adminButtons = new JPanel();
    	((FlowLayout)userButtons.getLayout()).setHgap(30);
    	adminButtons.add(aceptar);
    	adminButtons.add(rechazar);
    	
    	buttons.add(userButtons,"user");
        buttons.add(adminButtons,"admin");
    }
    
    /**
     * Establece las propiedades del campo descripcion para que se vea como un JLabel en varias lineas
     */
    private void descProperties() {
        desc.setEditable(false);  
        desc.setCursor(null);  
        desc.setOpaque(false);  
        desc.setFocusable(false);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
    }
    
    
    /**
     * Estrablece las restricciones de colocacion de los elementos con una distribucion SpringLayout
     */
    protected void anadirRestricciones(int desplIzq){
        /* alineacion horizontal */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, coste, -desplIzq, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, tipo, -desplIzq, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, distritoGrupo, -desplIzq, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, desc, -desplIzq, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, buttons, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        /* uniones verticales */
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, image, 0, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, distritoGrupo, 0, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, tipo, -10, SpringLayout.NORTH, distritoGrupo);
        spring.putConstraint(SpringLayout.SOUTH, coste, -10, SpringLayout.NORTH, tipo);
        spring.putConstraint(SpringLayout.SOUTH, nombre, -40, SpringLayout.NORTH, coste);
        spring.putConstraint(SpringLayout.NORTH, desc, 10, SpringLayout.SOUTH, distritoGrupo);
        spring.putConstraint(SpringLayout.NORTH, buttons, 30, SpringLayout.SOUTH, desc);
        
        /* uniones horizontales */
        spring.putConstraint(SpringLayout.EAST, distritoGrupoLabel, -10, SpringLayout.WEST, distritoGrupo);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, distritoGrupoLabel, 0, SpringLayout.VERTICAL_CENTER, distritoGrupo);
        spring.putConstraint(SpringLayout.EAST, tipoLabel, -10, SpringLayout.WEST, tipo);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, tipoLabel, 0, SpringLayout.VERTICAL_CENTER, tipo);
        spring.putConstraint(SpringLayout.EAST, costeLabel, -10, SpringLayout.WEST, coste);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, costeLabel, 0, SpringLayout.VERTICAL_CENTER, coste);
        spring.putConstraint(SpringLayout.EAST, descLabel, -10, SpringLayout.WEST, desc);
        spring.putConstraint(SpringLayout.NORTH, descLabel, 0, SpringLayout.NORTH, desc);
 
        spring.putConstraint(SpringLayout.WEST, estado, 30, SpringLayout.EAST, nombre);
        spring.putConstraint(SpringLayout.NORTH, estado, 0, SpringLayout.NORTH, nombre);
        spring.putConstraint(SpringLayout.NORTH, votos, 5, SpringLayout.SOUTH, estado);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, votos, 0, SpringLayout.HORIZONTAL_CENTER, estado);

        spring.putConstraint(SpringLayout.EAST, image, -10, SpringLayout.WEST, descLabel);
    }

    /**
     * Devuelve el proyecto asociado al panel
     * 
     * @return proyecto
     */
    public Project getProject() {
		return project;
	}
    
    /**
     * Establece el proyecto a visualizar en este panel
     * 
     * @param project proyecto a visualizar
     */
    public void setProject(Project project) {
        this.project = project;
        update();
    }

    /**
     * Actualiza el panel
     */
    public void update(){
    	updateButtons();
    	updateProjectInfo();
    }

    /**
     * Actualiza el panel de botones segun el usuario que visualiza el proyecto
     */
	private void updateButtons() {
        User user = Application.getApplication().getCurrentUser(); 
    	if (user != null) {
            ((CardLayout)buttons.getLayout()).show(buttons,"user");
    		
    		/* boton seguir/dejar de seguir */
    		if (user.getFollowedProjects().contains(project))
    			((CardLayout)seguirPanel.getLayout()).show(seguirPanel,"sigue");
    		else{
                ((CardLayout)seguirPanel.getLayout()).show(seguirPanel,"noSigue");
            }
    		
    		/* boton votar */
    		if (user.getVotedProjects().contains(project))
    			votar.setEnabled(false);
    		else
    			votar.setEnabled(true);		
        }
        else{
            ((CardLayout)buttons.getLayout()).show(buttons,"admin");
            if (!project.getProjectState().equals(projectState.ENVALIDACION)){
                aceptar.setEnabled(false);
                rechazar.setEnabled(false);
            }
            else{
                aceptar.setEnabled(true);
                rechazar.setEnabled(true);
            }
        }
    }
    
    /**
     * Actualiza la informacion que muestra el panel en funcion del proyecto
     */
	private void updateProjectInfo() {
		nombre.setText(project.getName());
		estado.setText(project.getProjectState().toString());
		
		String votosInfo = project.getVotes() + "/" + Application.getApplication().getMinVotes() + " votos";
		votos.setText(votosInfo);
		
		coste.setText(String.format("%.2f", project.getCost()) + " €");
		
		String tipoInfo;
		if (project instanceof InfraestructureProject) {
			tipoInfo = "Proyecto de infraestructura";
			distritoGrupoLabel.setText("Distrito:");
            distritoGrupo.setText(((InfraestructureProject) project).getDistrict());
            try{
                BufferedImage bufImage = ImageIO.read(new File(((InfraestructureProject)project).getImage()));
                Image scaledImage = bufImage.getScaledInstance(image.getWidth(), image.getHeight(),Image.SCALE_SMOOTH);
                image.setIcon(new ImageIcon(scaledImage));
            }catch(IOException e){
                e.printStackTrace();
            }
            anadirRestricciones(10);
		}
		else {
			tipoInfo = "Proyecto social de ambito ";
			if (((SocialProject) project).gettypeSocial().equals(typeSocial.NACIONAL))
				tipoInfo +=  "nacional";
			else
				tipoInfo += "internacional";
			distritoGrupoLabel.setText("Grupo social al que va dirigido:");
            distritoGrupo.setText(((SocialProject) project).getGroup());
            image.setIcon(null);
			anadirRestricciones(60);
		}
		tipo.setText(tipoInfo);
		
		desc.setText(project.getDescription());
    }
    
    /**
     * Establece el control del boton de votar
     * 
     * @param c accion que activa el boton
     */
    public void setControlVotar(ActionListener c){
        votar.addActionListener(c);
    }

    /**
     * Establece el control del boton de seguir
     * 
     * @param c accion que activa el boton
     */
    public void setControlSeguir(ActionListener c){
        seguir.addActionListener(c);
    }

    /**
     * Establece el control del boton de dejar de seguir
     * 
     * @param c accion que activa el boton
     */
    public void setControlDejarSeguir(ActionListener c){
        dejarSeguir.addActionListener(c);
    }

    /**
     * Establece el control del boton de aceptar
     * 
     * @param c accion que activa el boton
     */
    public void setControlDejarAceptar(ActionListener c){
        aceptar.addActionListener(c);
    }

    /**
     * Establece el control del boton de rechazar
     * 
     * @param c accion que activa el boton
     */
    public void setControlRechazar(ActionListener c){
        rechazar.addActionListener(c);
    }

}