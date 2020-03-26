package across.project;

import across.enumerations.*;
import java.util.*;

public class InfraestructureProject extends Project implements Serializable{
    private String image;
    private District district;

    public InfraestructureProject(String name, String dcp, double cost, String image, District district){
        super(name, dcp, cost);
        this.image = image;
        this.district = new District(district);
    }

    public void setImage(String image) { this.image = image; }
    public void setDistrict(District district) { this.district = district; }

    public String getImage() { return image; }
    public District getDistrict() { return district; }

    
    @Override
    public String toString(){
        String resumen = super.toString();
        resumen += "\nPROYECTO TIPO INFRAESTRUCTURA";
        resumen += "\nDistrito: " + district;
        resumen += "\nFoto: " + image;
        return resumen;
    }
    
}