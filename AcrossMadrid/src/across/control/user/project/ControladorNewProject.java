package across.control.user.project;

import across.model.application.Application;
import across.model.enumerations.typeSocial;
import across.model.project.InfraestructureProject;
import across.model.project.Project;
import across.model.project.SocialProject;
import across.model.user.Collective;
import across.model.user.UserCollective;
import across.gui.*;
import across.gui.user.PanelNewProject;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Clase ControladorNewProject
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class ControladorNewProject implements ActionListener{

    private PanelNewProject panel;
    private MainFrame frame;
    private Application model;

    /**
     * Constructor de la clase ControladorNewProject
     * 
     * @param frame pantalla principal de la aplicacion
     * @param model aplicacion(funcionamiento)
     */
    public ControladorNewProject (MainFrame frame, Application model){
        this.model = model;
        this.frame = frame;
        this.panel = frame.getNewProject();
    }

    /**
     * Accion que se realiza cuando se pulsa el boton de 'proponer proyecto' en el panel de crear nuevo proyecto
     * 
     * @param e accion recibida
     */
    @Override
    public void actionPerformed(ActionEvent e){
        String name = panel.getName().trim();
        double cost = panel.getCost();
        String desc = panel.getDescription().trim();
        UserCollective creator = null;
        
        /* TIPO DE CREADOR */
        if (panel.isUser())
            creator = model.getCurrentUser();
        else if (panel.isColectivo()){
            creator = panel.getColectivo();
            if (creator == null){
                JOptionPane.showMessageDialog(frame, "No puede crear el proyecto como colectivo porque todavia no ha creado ningun colectivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        else
            JOptionPane.showMessageDialog(frame, "Debe seleccionar como desea crear el proyecto: como usuario o como colectivo", "Aviso", JOptionPane.WARNING_MESSAGE);

        /* INFORMACION GENERAL DEL PROYECTO */
        if (name.equals("")){
            JOptionPane.showMessageDialog(frame, "Debe introducir un nombre para el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }else if(!model.validNameProject(name)){
            JOptionPane.showMessageDialog(frame, "Ya existe un proyecto con ese nombre", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }else if (cost <= 0){
            JOptionPane.showMessageDialog(frame, "Debe introducir un coste de proyecto valido", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }else if (desc.equals("")){
            JOptionPane.showMessageDialog(frame, "Debe introducir una descripcion para el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /* TIPO DE PROYECTO */
        else if (panel.isInfraestructura()){
            String distrito = panel.getDistrito();
            String imgPath = "src/across/gui/images/" + name + ".png";

            if (!saveImage(imgPath)) return;
            
            model.addNewProject(new InfraestructureProject(name, desc, cost, imgPath, distrito, creator));
            JOptionPane.showMessageDialog(frame, "Su propuesta de proyecto se ha enviado al adminstrador.\nUna vez validado estara disponible para ser votado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            panel.emptyFields();
        }
        else if (panel.isSocial()){
            String grupo = panel.getGroup().trim();
            String ambitoStr = panel.getAmbito();
            if (grupo.equals("")){
                JOptionPane.showMessageDialog(frame, "Debe introducir el grupo social al que va dirigido el proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }else if (ambitoStr.equals("Ambito")){
                JOptionPane.showMessageDialog(frame, "Debe elegir un ambito de proyecto valido", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
                typeSocial tipo = (ambitoStr.equals("Nacional"))?typeSocial.NACIONAL:typeSocial.INTERNACIONAL;
                model.addNewProject(new SocialProject(name, desc, cost, grupo, tipo, creator));
                JOptionPane.showMessageDialog(frame, "Su propuesta de proyecto se ha enviado al adminstrador.\nUna vez validado estara disponible para ser votado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                panel.emptyFields();
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Debe elegir tipo de proyecto: de infraestructura o social", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        ArrayList<Project> proyectos = model.getProjects();
        ArrayList<Collective> colectivos = model.getCollectives();
        frame.getInicioUser().updateData(proyectos, colectivos);
        frame.showPanel("inicioUser");
    }

    /**
     * Guarda la imagen cargada en el panel alteriormente en el directorio parado
     * como argumento
     * 
     * @param path directorio donde almacenar la imagen
     * @return true si la imagen se ha guardado correctamente, false en caso
     *         contrario
     */
    private boolean saveImage(String path){
        BufferedImage img = panel.getImage();
        File dest = new File(path);

        if (img != null){
            try{
                ImageIO.write(img, "png", dest);
                return true;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        else 
            JOptionPane.showMessageDialog(frame, "Debe seleccionar una imagen o maqueta del proyecto", "Aviso", JOptionPane.WARNING_MESSAGE);

        return false;
    }
}

