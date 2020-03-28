package across.project;

import across.enumerations.*;
import across.admin.*;
import across.notification.*;
import across.project.*;
import across.user.*;

import java.io.*;


/**
 * Clase InfraestructureProject que hereda de Project
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public class InfraestructureProject extends Project implements Serializable{
    private String image;
    private String district;

    public InfraestructureProject(String name, String dcp, double cost, String image, String district, UserCollective creator){
        super(name, dcp, cost, creator);
        this.image = image;
        this.district = district;
        new NotificationAdminProject(this);
    }

    public void setImage(String image) { this.image = image; }
    public void setDistrict(String district) { this.district = district; }

    public String getImage() { return image; }
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