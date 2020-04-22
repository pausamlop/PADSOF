package across.model.project;

import across.model.notification.*;
import across.model.user.*;


/**
 * Clase InfraestructureProject que hereda de Project
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
@SuppressWarnings("serial")
public class InfraestructureProject extends Project {
    private String image;
    private String district;

    /**
     * Constructor de un proyecto de infraestructura
     * 
     * @param name nombre del proyecto
     * @param dcp descripcion del proyecto
     * @param cost coste apoximado del proyecto
     * @param image imagen asociada al proyecto
     * @param district distrito al cual el pryecto esta enfocado
     * @param creator creador del proyecto
     */
    public InfraestructureProject(String name, String dcp, double cost, String image, String district, UserCollective creator){
        super(name, dcp, cost, creator);
        this.image = image;
        this.district = district;
        new NotificationAdminProject(this);
    }

    /**
     * Establece la imagen asociada al proyecto
     * 
     * @param image cadena de caracteres
     */
    public void setImage(String image) { this.image = image; }

    /**
     * Establece el distrito asociado al proyecto
     * 
     * @param district cadena de caracteres
     */
    public void setDistrict(String district) { this.district = district; }

    /**
     * Devuelve la cadena de la imagen asociada al proyecto
     * 
     * @return imagen asociada al proyecto
     */
    public String getImage() { return image; }
    
    /**
     * Devuelve el distrito asociado al proyecto
     * 
     * @return distrito asociado al proyecto
     */
    public String getDistrict() { return district; }

    
    @Override
    public String toString(){
        String resumen = super.toString();
        resumen += "\n      PROYECTO TIPO INFRAESTRUCTURA";
        resumen += "\n      + Distrito: " + district;
        resumen += "\n      + Foto: " + image;
        return resumen;
    }
    
}