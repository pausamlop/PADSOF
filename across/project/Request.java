package across.project;

import across.enumerations.*;

import es.uam.eps.sadp.grants.*;

/**
 * Implementacion de la interfaz GrantRequest facilitada para la realizacion del proyecto
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@estudiante.uam.es
 * @author Paula Samlop paula.samper@estudiante.uam.es
 *
 */
public class Request implements GrantRequest{
	Project project;
	
	/**
	 * Constructor de una peticion
	 * 
	 * @param p proyecto que realizara la peticion de financiacion
	 */
	public Request(Project p) {
		project = p;
	}
	
	/**
	 * Devuelve el coste asociado a aquel proyecto que realiza la peticion de financiacion
	 * 
	 * @return coste del proyecto que realizara la propuesta de financiacion
	 */
	public double getRequestedAmount(){		
		return project.getCost();
	}
	
	/**
	 * Devuelve el tipo de proyecto que realiza la peticion, adaptando nuestra implmentacion a aquella 
	 * utilizada en dicha interfaz con la enumeracion GrantRequest
	 * 
	 * @return tipo de proyecto que realiza la peticion
	 */
	public GrantRequest.ProjectKind getProjectKind(){
		if(project.getClass() == SocialProject.class) return GrantRequest.ProjectKind.Social;
		else return GrantRequest.ProjectKind.Infrastructure;
	}
	
	/**
	 * Devuelve el nombre del proyecto que realiza la peticion
	 * 
	 * @return titulo del proyecto que realiza la peticion
	 */
	public String getProjectTitle() {
		return project.getName();
	}
	
	/**
	 * Devuelve la descripcion del proyecto que realiza la peticion
	 * 
	 * @return descripcion del proyecto que realiza la peticion
	 */
	public String getProjectDescription() {
		return project.getDescription();
	}
	
	/**
	 * Devolvera los datos adicionales asociados a los diferentes tipos de proyectos
	 * 
	 * @return datos del proyecto que dependen de su tipo
	 */
	public String getExtraData(){
		String extra = "";
		
		if(project.getClass() == SocialProject.class) {
			extra = "El grupo al que esta destinado el proyecto es: " + ((SocialProject)project).getGroup();
			if(((SocialProject)project).gettypeSocial() == typeSocial.INTERNACIONAL) extra += "\nEl proyecto sera de interes internacional";
			else extra += "\nEl proyecto sera de interes nacional";
		}
		
		else extra= "El enlace asociado a la imagen del proyecto es: " + ((InfraestructureProject)project).getImage() + "\nEl distrito al que el mismo esta destinado es: " + ((InfraestructureProject)project).getDistrict(); 

		return extra;
	}
	
}