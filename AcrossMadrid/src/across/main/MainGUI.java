package across.main;

import across.model.application.Application;
import across.gui.MainFrame;
import across.control.Controlador;

/**
 * Clase MainGUI
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class MainGUI {

    public static void main(String[] args){
        Application.setApplication(Application.loadData());
        Application modelo = Application.getApplication();
        MainFrame frame = new MainFrame();
        Controlador controlador = new Controlador(frame, modelo);
        frame.setControlador(controlador);
        frame.setVisible(true);

        // para probar paneles sin conectar
        frame.showPanel("inicio");
    }


}