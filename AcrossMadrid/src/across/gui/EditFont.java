package across.gui;

import java.awt.*;
import javax.swing.*;

/**
 * Clase EditFont
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class EditFont {

    /**
     * Permite transformar una etiqueta de texto de normal a negrita y viceversa
     * 
     * @param label etiqueta de texto a transformar
     */
    public static void bold(JLabel label){
        label.setFont(label.getFont().deriveFont(label.getFont().getStyle() ^ Font.BOLD));
    }
    
    /**
     * Permite cambiar el tamano de una etiqueta de texto
     * 
     * @param label etiqueta de texto a transformar
     * @param size tamano al que se desea convertir la etiqueta de texto
     */
    public static void setSize(JLabel label, int size){
        label.setFont(label.getFont().deriveFont((float)size));
    }
}