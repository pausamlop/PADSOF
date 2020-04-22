package across.gui.user;

import javax.swing.*;

import across.gui.EditFont;
import across.model.application.Application;
import across.model.user.Collective;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Clase PanelNewProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class PanelNewProject extends JPanel {

    private int anchoTextField = 20;

    private JLabel title = new JLabel("Nuevo proyecto");
    private JLabel crearComo = new JLabel("Crear proyecto como:");
    private JRadioButton comoUser = new JRadioButton("Usuario");
    private JRadioButton comoColectivo = new JRadioButton("Colectivo");
    private JLabel nombreLabel = new JLabel("Nombre:", SwingConstants.RIGHT);
    private JTextField nombre = new JTextField(anchoTextField);
    private JLabel tipoLabel = new JLabel("Tipo de proyecto:", SwingConstants.RIGHT);
    private JRadioButton infr = new JRadioButton("Infraestructura");
    private JRadioButton social = new JRadioButton("Social");
    private JLabel costeLabel = new JLabel("Coste aproximado:", SwingConstants.RIGHT);
    private JTextField coste = new JTextField(7);
    private JLabel descLabel = new JLabel("Descripcion:", SwingConstants.RIGHT);
    private JTextArea desc = new JTextArea(4,anchoTextField);
    private JButton button = new JButton("Proponer proyecto");
    private JScrollPane descPane;

    private JComboBox<Collective> colectivos = new JComboBox<>();
    
    private JLabel grupoDistritoLabel = new JLabel(" ");
    private JPanel grupoDistrito = new JPanel(new CardLayout());
    private JPanel ambitoFoto = new JPanel(new CardLayout());

    private JComboBox<String> distritos;
    private JButton foto = new JButton("Cargar foto");
    private JTextField grupoSocial = new JTextField(anchoTextField);
    private JComboBox<String> ambito;

    private SpringLayout spring = new SpringLayout();

    private BufferedImage infrImage = null;

    /**
     * Constructor de la clase PanelNewProject
     */
    public PanelNewProject(){
        setLayout(spring);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(comoUser);
        buttonGroup1.add(comoColectivo);
        setControlUser();
        setControlColectivo();
        colectivos.setVisible(false);
        
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(infr);
        buttonGroup2.add(social);
        setControlInfr();
        setControlSocial();

        desc.setLineWrap(true);
        descPane = new JScrollPane(desc);

        crearPanelesTipo();
        anadirRestricciones();

        /* modificacion de fuentes */
        title = EditFont.setSize(title, 24);
        title = EditFont.bold(title);

        this.add(title);
        this.add(crearComo);
        this.add(comoUser);
        this.add(comoColectivo);
        this.add(colectivos);
        this.add(nombre);
        this.add(nombreLabel);
        this.add(infr);
        this.add(tipoLabel);
        this.add(social);
        this.add(grupoDistritoLabel);
        this.add(grupoDistrito);
        this.add(coste);
        this.add(costeLabel);
        this.add(ambitoFoto);
        this.add(descPane);
        this.add(descLabel);
        this.add(button);

    }

    /**
     * Crea los diferentes paneles correspondientes a los 2 tipos de proyecto
     */
    private void crearPanelesTipo(){

        JPanel panelDistritos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        distritos = new JComboBox<>(Application.getApplication().getDistricts().toArray(new String[0]));
        panelDistritos.add(distritos);
        JPanel panelGrupo = new JPanel();
        panelGrupo.add(grupoSocial);

        grupoDistrito.add(new JPanel(), "nada");
        grupoDistrito.add(panelDistritos, "infraestructura");
        grupoDistrito.add(panelGrupo, "social");

        JPanel panelFoto = new JPanel();
        panelFoto.add(foto);
        JPanel panelAmbito = new JPanel();
        String[] arrayAmbito = {"Ambito", "Nacional", "Internacional"};
        ambito = new JComboBox<>(arrayAmbito);
        panelAmbito.add(ambito);

        ambitoFoto.add(new JPanel(), "nada");
        ambitoFoto.add(panelFoto, "infraestructura");
        ambitoFoto.add(panelAmbito, "social");
    }

    /**
     * Estrablece las restricciones de colocacion de los elementos con una distribucion SpringLayout
     */
    private void anadirRestricciones(){
        /* alineacion horizontal */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, comoUser, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, comoColectivo, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, nombre, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, infr, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.EAST, grupoDistritoLabel, -70, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, coste, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, descPane, -56, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        /* uniones verticales */
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, grupoDistritoLabel, 0, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, infr, -10, SpringLayout.NORTH, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.SOUTH, nombre, -10, SpringLayout.NORTH, infr);
        spring.putConstraint(SpringLayout.SOUTH, comoColectivo, -10, SpringLayout.NORTH, nombre);
        spring.putConstraint(SpringLayout.SOUTH, comoUser, -2, SpringLayout.NORTH, comoColectivo);
        spring.putConstraint(SpringLayout.SOUTH, title, -30, SpringLayout.NORTH, comoUser);
        spring.putConstraint(SpringLayout.NORTH, coste, 10, SpringLayout.SOUTH, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.NORTH, descPane, 10, SpringLayout.SOUTH, coste);
        spring.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.SOUTH, descPane);
        
        /* uniones horizontales */
        spring.putConstraint(SpringLayout.EAST, crearComo, -10, SpringLayout.WEST, comoUser);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, crearComo, 0, SpringLayout.VERTICAL_CENTER, comoUser);
        spring.putConstraint(SpringLayout.WEST, colectivos, 8, SpringLayout.EAST, comoColectivo);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, colectivos, 0, SpringLayout.VERTICAL_CENTER, comoColectivo);
        spring.putConstraint(SpringLayout.EAST, nombreLabel, -10, SpringLayout.WEST, nombre);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel, 0, SpringLayout.VERTICAL_CENTER, nombre);
        spring.putConstraint(SpringLayout.EAST, tipoLabel, -10, SpringLayout.WEST, infr);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, tipoLabel, 0, SpringLayout.VERTICAL_CENTER, infr);
        spring.putConstraint(SpringLayout.WEST, social, 8, SpringLayout.EAST, infr);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, social, 0, SpringLayout.VERTICAL_CENTER, infr);
        spring.putConstraint(SpringLayout.WEST, grupoDistrito, 6, SpringLayout.EAST, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, grupoDistrito, 0, SpringLayout.VERTICAL_CENTER, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.EAST, costeLabel, -10, SpringLayout.WEST, coste);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, costeLabel, 0, SpringLayout.VERTICAL_CENTER, coste);
        spring.putConstraint(SpringLayout.WEST, ambitoFoto, 12, SpringLayout.EAST, coste);
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, ambitoFoto, 0, SpringLayout.VERTICAL_CENTER, coste);
        spring.putConstraint(SpringLayout.EAST, descLabel, -10, SpringLayout.WEST, descPane);
        spring.putConstraint(SpringLayout.NORTH, descLabel, 0, SpringLayout.NORTH, descPane);
    }
    
    /**
     * Devuelve la informacion introducida por el usuario en el campo de texto de nombre de proyecto
     * 
     * @return nombre del proyecto
     */
    public String getName() {
        return nombre.getText();
    }

    /**
     * Devuelve la informacion introducida por el usuario en el campo de texto de nombre de grupo
     * 
     * @return grupo social al que va dirigido
     */
    public String getGroup() {
        return grupoSocial.getText();
    }

    /**
     * Devuelve la informacion introducida por el usuario en el campo de texto de coste
     * 
     * @return coste del proyecto
     */
    public double getCost(){
        double cost;
        try{
            cost = Double.parseDouble(this.coste.getText());
        }catch(NumberFormatException e){
            cost = -1f;
        }
        return cost;
    }

    /**
     * Devuelve la informacion introducida por el usuario en el campo de texto de descripcion de proyecto
     * 
     * @return descripcion del proyecto
     */
    public String getDescription(){
        return desc.getText();
    }

    /**
     * Devuelve si el proyecto ha sido creado por un usuario individual
     * 
     * @return true si es creado por un usuario, false en otro caso
     */
    public boolean isUser(){
        return comoUser.isSelected();
    }

    /**
     * Devuelve si el proyecto ha sido creado por un colectivo
     * 
     * @return true si es creado por un colectivo, false en otro caso
     */
    public boolean isColectivo(){
        return comoColectivo.isSelected();
    }

    /**
     * Devuelve el colectivo creador del proyecto 
     * 
     * @return colectivo creador del proyecto, null si el creador es un usuario individual
     */
    public Collective getColectivo(){
        return (Collective)colectivos.getSelectedItem();
    }

    /**
     * Establece el contenido del JComboBox de los colectivos creador por el usuario actual
     * 
     * @param col colectivos creados por el usuario actual
     */
    public void setColectivos(Collection<Collective> col){
        for (Collective c: col)
            colectivos.addItem(c);
    }

    /**
     * Devuelve si el proyecto a crear es de tipo infraestructura
     * 
     * @return true si es infraestructura, false en otro caso
     */
    public boolean isInfraestructura(){
        return infr.isSelected();
    }

    /**
     * Devuelve el distrito en el que se desea llevar a cabo el proyecto de infraestructura
     * 
     * @return distrito
     */
    public String getDistrito(){
        return (String)distritos.getSelectedItem();
    }

    /**
     * Devuelve si el proyecto a crear es de tipo social
     * 
     * @return true si es social, false en otro caso
     */
    public boolean isSocial(){
        return social.isSelected();
    }

    /**
     * Devuelve el ambito del proyecto social
     * 
     * @return ambito
     */
    public String getAmbito(){
        return (String)ambito.getSelectedItem(); 
    }

    /**
     * Devuelve la imagen asociada al proyecto de infraestructura cargada anteriormente
     * 
     * @return imagen cargada, null si no se ha cargado ninguna imagen
     */
    public BufferedImage getImage(){
        return infrImage;
    }

    /**
     * Establece la imagen del proyecto como la imagen cargada y pasada como argumento
     * 
     * @param img imagen cargada
     */
    public void setImage(BufferedImage img){
        this.infrImage = img;
    }
    
    /**
     * Establece el control de la seleccion del RadioButton usuario
     */
    public void setControlUser(){
        comoUser.addActionListener(e -> colectivos.setVisible(false));
    }

    /**
     * Establece el control de la seleccion del RadioButton colectivo
     */
    public void setControlColectivo(){
        comoColectivo.addActionListener(e -> colectivos.setVisible(true));
    }

    /**
     * Establece el control de la seleccion del RadioButton infraestructura
     */
    public void setControlInfr(){
        infr.addActionListener(e -> {
            grupoDistritoLabel.setText("Distrito:");
            ((CardLayout)grupoDistrito.getLayout()).show(grupoDistrito,"infraestructura");
            ((CardLayout)ambitoFoto.getLayout()).show(ambitoFoto,"infraestructura");
        });
    }

    /**
     * Establece el control de la seleccion del RadioButton social
     */
    public void setControlSocial(){
        social.addActionListener(e -> {
            grupoDistritoLabel.setText("Grupo social al que va dirigido:");
            ((CardLayout)grupoDistrito.getLayout()).show(grupoDistrito,"social");
            ((CardLayout)ambitoFoto.getLayout()).show(ambitoFoto,"social");
        });
    }

    /**
     * Establece el control del boton de cargar imagen
     * 
     * @param c accion que activa el boton
     */
    public void setControlFoto(ActionListener c){
        foto.addActionListener(c);
    }

    /**
     * Establece el control del boton de proponer proyecto
     * 
     * @param c accion que activa el boton
     */
    public void setControlProponerProyecto(ActionListener c){
        button.addActionListener(c);
    }

}