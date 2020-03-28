package across.project;

import across.enumerations.*;

import es.uam.eps.sadp.grants.*;

public class Request implements GrantRequest{
	Project project;
	
	public Request(Project p) {
		project = p;
	}
	
	public double getRequestedAmount(){		
		return project.getCost();
	}
	
	public GrantRequest.ProjectKind getProjectKind(){
		if(project.getClass() == SocialProject.class) return GrantRequest.ProjectKind.Social;
		else return GrantRequest.ProjectKind.Infrastructure;
	}
	
	public String getProjectTitle() {
		return project.getName();
	}
	
	public String getProjectDescription() {
		return project.getDescription();
	}
	
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