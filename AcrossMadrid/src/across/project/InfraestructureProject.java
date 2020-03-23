package across.project;

import across.enumerations.*;
import java.util.*;

public class InfraestructureProject extends Project {
    private String image;
    private nameDistrict district;
    private scopeSocial scope;

    public InfraestructureProject(String name, String dcp, double cost, int votes, Date lastVote, projectState state, String image, nameDistrict district){
        super(name, dcp, cost, votes, lastVote, state);
        this.image = image;
        this.district = district;
    }

    public void setImage(String image) { this.image = image; }
    public void setDistrict(nameDistrict district) { this.district = district; }

    public String getImage() { return image; }
    public nameDistrict getDistrict() { return district; }


    @Override
    public String toString(){
        String resumen = super.toString();
        resumen += "\nPROYECTO TIPO INFRAESTRUCTURA";
        resumen += "\nDistrito: " + district;
        resumen += "\nFoto: " + image;
        return resumen;
    }
    
}