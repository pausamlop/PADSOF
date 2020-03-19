package across.project;

import across.enumerations.*;
import java.util.*;

enum nameDistrict{
    Centro, Arganzuela, Retiro, Salamanca, Chamartin, Tetuan, Chamberi, FuencarralElPardo,
    MoncloaAravaca, Latina, Carabanchel, Usera, PuentedeVallecas, Moratalaz, CiudadLineal,
    Hortaleza, Villaverde, VillaDeVallecas, Vicalvaro, SanBlasCanillejas, Barajas
}

public class InfraestructureProject extends Project {
    private String image;
    private nameDistrict district;

    public InfraestructureProject(String name, String dcp, double cost, int votes, Date lastVote, projectState state, String image, nameDistrict district){
        super(name, dcp, cost, votes, lastVote, state);
        this.image = image;
        this.district = district;
    }

    public void setImage(String image) { this.image = image; }
    public void setDistrict(nameDistrict district) { this.district = district; }

    public String getImage() { return image; }
    public nameDistrict getDistrict() { return district; }

    
}