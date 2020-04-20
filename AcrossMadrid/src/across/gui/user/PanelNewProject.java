package across.gui.user;

import javax.swing.*;

import across.gui.EditFont;
import across.model.application.Application;
import java.awt.*;
import java.awt.event.*;

public class PanelNewProject extends JPanel {

    private int anchoTextField = 20;

    private JLabel title = new JLabel("Nuevo proyecto");
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
    
    private JLabel grupoDistritoLabel = new JLabel(" ");
    private JPanel grupoDistrito = new JPanel(new CardLayout());
    private JPanel ambitoFoto = new JPanel(new CardLayout());

    JComboBox<String> distritos;
    JButton foto = new JButton("Cargar foto");
    JTextField grupoSocial = new JTextField(anchoTextField);
    JComboBox<String> ambito;

    private SpringLayout spring = new SpringLayout();

    public PanelNewProject(){
        setLayout(spring);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(infr);
        buttonGroup.add(social);
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

    private void anadirRestricciones(){
        /* anadir restricciones de colocacion de los elementos */
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 0, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, nombre, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, infr, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.EAST, grupoDistritoLabel, -70, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, coste, -60, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.WEST, descPane, -56, SpringLayout.HORIZONTAL_CENTER, this);
        spring.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        /* uniones verticales */
        spring.putConstraint(SpringLayout.VERTICAL_CENTER, grupoDistritoLabel, -10, SpringLayout.VERTICAL_CENTER, this);
        spring.putConstraint(SpringLayout.SOUTH, infr, -10, SpringLayout.NORTH, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.SOUTH, nombre, -10, SpringLayout.NORTH, infr);
        spring.putConstraint(SpringLayout.SOUTH, title, -30, SpringLayout.NORTH, nombre);
        spring.putConstraint(SpringLayout.NORTH, coste, 10, SpringLayout.SOUTH, grupoDistritoLabel);
        spring.putConstraint(SpringLayout.NORTH, descPane, 10, SpringLayout.SOUTH, coste);
        spring.putConstraint(SpringLayout.NORTH, button, 30, SpringLayout.SOUTH, descPane);
        
        /* uniones horizontales */
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
    
    public String getName() {
        return nombre.getText();
    }

    public String getGroup() {
        return grupoSocial.getText();
    }

    public double getCost(){
        double cost;
        try{
            cost = Double.parseDouble(this.coste.getText());
        }catch(NumberFormatException e){
            cost = -1f;
        }
        return cost;
    }

    public String getDescription(){
        return desc.getText();
    }

    public boolean isInfraestructura(){
        return infr.isSelected();
    }

    public String getDistrito(){
        return (String)distritos.getSelectedItem();
    }

    public boolean isSocial(){
        return social.isSelected();
    }

    public String getAmbito(){
        return (String)ambito.getSelectedItem(); 
    }
    
    public void setControlInfr(){
        infr.addActionListener(e -> {
            grupoDistritoLabel.setText("Distrito:");
            ((CardLayout)grupoDistrito.getLayout()).show(grupoDistrito,"infraestructura");
            ((CardLayout)ambitoFoto.getLayout()).show(ambitoFoto,"infraestructura");
        });
    }

    public void setControlSocial(){
        social.addActionListener(e -> {
            grupoDistritoLabel.setText("Grupo social al que va dirigido:");
            ((CardLayout)grupoDistrito.getLayout()).show(grupoDistrito,"social");
            ((CardLayout)ambitoFoto.getLayout()).show(ambitoFoto,"social");
        });
    }

    public void setControlFoto(ActionListener c){
        foto.addActionListener(c);
    }

    public void setControlProponerProyecto(ActionListener c){
        button.addActionListener(c);
    }

}