package across.model.project;

import across.model.application.*;
import across.model.enumerations.*;
import across.model.notification.*;
import across.model.user.*;

import es.uam.eps.sadp.grants.*;

import java.util.*;
import java.io.*;
import java.time.*;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Clase abstracta Project
 *
 * @author Juan Carlos Villa juanc.villa@estudiante.uam.es
 * @author Laura de Paz laura.pazc@uam.es
 * @author Paula Samper paula.samper@estudiante.uam.es
 *
 */
public abstract class Project implements Serializable, Comparable<Project>{
    private String name;
    private String dcp;
    private double cost;
    private int votes;
    private LocalDate lastVote;
    private projectState state;

    private UserCollective creator;
    private List<UserCollective> voters = new ArrayList<>();
    private List<User> followers = new ArrayList<>();

    /**
     * Constructor de la clase abstracta proyecto, que heredaran sus dos subclases
     * 
     * @param name nombre del proyecto
     * @param dcp descripcion del proyecto
     * @param cost coste aproximado del proyecto
     * @param creator creador del proyecto
     */
    public Project(String name, String dcp, double cost, UserCollective creator) {
        this.name = name;
        this.dcp = dcp;
        this.cost = cost;
        this.votes = 0;
        this.lastVote = LocalDate.now();
        this.state = projectState.ENVALIDACION;
        this.creator = creator;

        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getNonValidatedProjects());
        p1.add(this);
        Application.getApplication().setNonValidatedProjects(p1);

    }

    /**
     * Establece el nombre del proyecto
     * 
     * @param name cadena de caracteres
     */
    public void setName(  String name) {
        this.name = name;
    }
    
    /**
     * Establece la descripcion del proyecto
     * 
     * @param dcp cadena de caracteres
     */
    public void setDescription(  String dcp) {
        this.dcp = dcp;
    }

    /**
     * Establece el coste del proyecto
     * 
     * @param cost coste en euros
     */
    public void setCost(  double cost) {
        this.cost = cost;
    }

    /**
     * Establece los votos del proyecto
     * 
     * @param votes int
     */
    public void setVotes(  int votes) {
        this.votes = votes;
    }

    /**
     * Establece la fecha en la cual se realizo el ultimo voto al proyecto
     * 
     * @param lastVote fecha del ultimo voto
     */
    public void setLastVote(  LocalDate lastVote) {
        this.lastVote = lastVote;
    }

    /**
     * Establece el estado actual del proyecto, dependiendo del cambio de estado,
     * se generará un tipo de notificacion determinado y será enviada a sus seguidores
     * 
     * @param state estado del proyecto
     */
    public void setProjectState( projectState state) {
        this.state = state;
        new NotificationUser(this);
    }

    /**
     * Devuelve el nombre del proyecto
     * 
     * @return nombre del proyecto
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve la descripcion del proyecto
     * 
     * @return descripcion del proyecto
     */
    public String getDescription() {
        return dcp;
    }

    /**
     * Devuelve el coste del proyecto
     * 
     * @return coste del proyecto
     */
    public double getCost() {
        return cost;
    }

    /**
     * Devuelve el numero de votos recibidos 
     * 
     * @return votos recibidos
     */
    public int getVotes() {
        return votes;
    }

    /**
     * Devuelve la fecha del ultimo voto recibido
     * 
     * @return fecha del ultimo voto
     */
    public LocalDate getLastVote() {
        return lastVote;
    }

    /**
     * Devuelve el estado del proyecto
     * 
     * @return estado del proyecto
     */
    public projectState getProjectState() {
        return state;
    }

    /**
     * Devuelve el creador del proyecto, tanto si este ha sido creado como colectivo como si lo ha sido como usuario
     * 
     * @return creador del proyecto
     */
    public UserCollective getCreator() {
        return creator;
    }

    /**
     * Lista con todos los seguidores de un proyecto
	 * 
     * @return boolean 
     */
    public List<User> getFollowers(){
        return this.followers;
    }

    /**
     * Metodo que nos servira para saber si un proyecto ha caducado
     * 
     * @return true en caso de que haya caducado y false en caso contrario
     */
    public boolean isExpired(){ 
        int maxDays = Application.getApplication().getDaysExpiration();
        long diff = DAYS.between(lastVote, LocalDate.now());

        if (state == projectState.ACEPTADO || state == projectState.VOTOSALCANZADOS){
            if(maxDays <= diff){
                setProjectState(projectState.CADUCADO); 
                return true;
            }
        }
        return false;
    }

    /**
     * Actualiza el estado del objeto Project en funcion de sus votos
     */
    public void updateState(){

        // Comprobar si ha llegado al min de votos
        if (getProjectState().equals(projectState.ACEPTADO) && (Application.getApplication().getMinVotes() <= votes) ){
            setProjectState(projectState.VOTOSALCANZADOS);
        }
        if (getProjectState().equals(projectState.VOTOSALCANZADOS) && (Application.getApplication().getMinVotes() > votes) ){
            setProjectState(projectState.ACEPTADO);
        }

    }

    
    /**
     * Sobreescribe el metodo compareTo para objetos de la clase Project
     * 
     * @param p 
     * @return entero dependiendo de si es mayor menor o igual
     */
    @Override
    public int compareTo(Project p){
        if (this.getVotes() < p.getVotes()) return 1;
        else if(this.getVotes() > p.getVotes()) return -1;
        else return 0;
    }

    /**
     * Metodo para validar un proyecto
     */
    public void validate(){
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getProjects());
        p1.add(this);

        ArrayList<Project> p2 = new ArrayList<Project>();
        p2.addAll(Application.getApplication().getNonValidatedProjects());
        p2.remove(this);

        Application.getApplication().setProjects(p1);
        Application.getApplication().setNonValidatedProjects(p2);

        //añadir a proyectos creados
        ArrayList<Project> p3 = new ArrayList<Project>();
        p3.addAll(creator.getCreatedProjects());
        p3.add(this);
        creator.setCreatedProjects(p3);

        // Cambiar el estado del proyecto
        state = projectState.ACEPTADO;

        // Voto del creador
        vote(creator);
    }


	/**
     * Metodo para rechazar un proyecto
     */
    public void reject(){
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getNonValidatedProjects());
        p1.remove(this);

        Application.getApplication().setNonValidatedProjects(p1);

        // Anadirlo al array de rechazados
        ArrayList<Project> p2 = new ArrayList<Project>();
        p2.addAll(Application.getApplication().getRejectedProjects());
        p2.add(this);

        Application.getApplication().setRejectedProjects(p2);

        // Cambiar el estado del proyecto
        state = projectState.RECHAZADO;


    }


    /**
     * Comprueba si un usuario sigue a un proyecto
	 * 
     * @return boolean 
     */
    public boolean checkFollow(User u){
        if (u.getFollowedProjects().contains(this)) return true;
        return false;

    }


    /**
     * Un usuario empieza a seguir a un proyecto
	 * 
     * @return boolean 
     */
    public void follow(User u){
        if (checkFollow(u)) return;

        followers.add(u);
        ArrayList<Project> newArray= u.getFollowedProjects();
        newArray.add(this);
        u.setFollowedProjects(newArray);
    }


    /**
     * Un usuario deja de seguir a un proyecto
	 * 
     * @return boolean 
     */
    public void unfollow (User u){
        if (checkFollow(u)){
            followers.remove(u);
            ArrayList<Project> newArray= u.getFollowedProjects();
            newArray.remove(this);
            u.setFollowedProjects(newArray);
        }

    }

    /**
     * Devuelve un set con todos los usuarios que han votado el proyecto
     * 
     * @return set de objetos UserCollective ...
     */
    private Set<User> makeSet(){
        Set<User> set = new HashSet<>();
        for (UserCollective uc: voters){

            // Usuarios individuales
            if (uc.getClass().equals(User.class)){
                set.add((User)uc);

            // Usuarios que forman parte de colectivos
            } else {
                set.addAll(((Collective)uc).getChildrenMembers());
            }
        }

        Set<User> output = new HashSet <>();
        output.addAll(set);

        for(User u: set){
            if(u.getBlocked())
                output.remove(u);
        }
        return output;
    }


    /**
     * Un usercollective vota a un proyecto
	 * 
     * @param uc usercollective
     */
    public void vote (UserCollective uc){

        //comprobar que no esta caducado o ya ha sido financiado
        if (state == projectState.CADUCADO || state == projectState.FINANCIADO) return;

        // Comprobar si ha votado ya
        if (voters.contains(uc)) return;

        // Si vota como usuario
        if (uc.getClass().equals(User.class)) {
	    if(((User)uc).getBlocked()) return;

            // Añadir el usuario a voters
            voters.add(uc);
        }

        // Si vota como colectivo
        else {
            if (Application.getApplication().getCurrentAdmin() || 
                Application.getApplication().getCurrentUser().equals(((Collective)uc).getManager())){

                // Añadir el colectivo a voters
                voters.add(uc);
            }
        }

        // Make Set con los votes
        Set<User> allVoters = makeSet();

        // Actualizar el numero de votos del proyectos y la fecha del ultimo voto
        votes = allVoters.size();
        lastVote = LocalDate.now();

        // Actualizar los proyectos votados en usercollective
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(uc.getVotedProjects());
        p1.add(this);
        uc.setVotedProjects(p1);

        // Update estado
        updateState();
  

    }


    /**
     * Update de los votos de un proyecto cuando 
     * cambian los miembros de un colectivo
	 * 
     * @param c usercollective
     */
    public void updateVotes(){

        Set<User> allVoters = makeSet();

        // Quitar usuarios bloqueados
        for(User u: allVoters){
            if(u.getBlocked())
            allVoters.remove(u);
        }

        votes = allVoters.size();
        
        // Update estado
        updateState();
        
    }
    

    /**
     * Metodo para mandar a financiar un proyecto en cuestion
     *  
     * @return true en caso de que haya sido enviado y false en caso contrario
     */
    public boolean sendToFinance(){
    	if(this.getVotes() < Application.getApplication().getMinVotes()) return false;
    	
    	String id;
    	
    	CCGG gateway = CCGG.getGateway();
    	Request request = new Request(this);
    	
    	try {
            id = gateway.submitRequest(request);
            Application.getApplication().addPendingFinance(this, id);
            this.setProjectState(projectState.ENVIADO);
	    	return true;
		} catch (IOException | InvalidRequestException e) {
			e.printStackTrace();
			return false;
		}
    	
    }

    /**
     * Metodo para obtener la financiacion dada a un proyecto en cuestion
     * 
     * @return en caso de haber sido financiado, el dinero con el que ha sido financiado. En caso de que aun no haya sido atendida
     * esta request, devolverá null.
     */
    public Double financed(String id){
    	Double finance;
    	
    	try {
            finance = CCGG.getGateway().getAmountGranted(id);
			return finance;
		} catch (InvalidIDException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e2){
            System.out.println("Fallo de comunicacion con el Ayuntamiento");
            return null;
        }
        
    }

    public String toString(){
        String resumen = "";
        resumen += "Nombre: " + name;
        resumen += ", descripcion: " + dcp;
        if (creator instanceof Collective)
            resumen += "\n      Creado por colectivo " + ((Collective)creator).getName();
        else   
            resumen += "\n      Creado por " + ((User)creator).getUsername();
        resumen += "\n      Estado: " + state.name();
        resumen += "\n      Votos: " + votes + "/" + Application.getApplication().getMinVotes(); 
        resumen += "\n      Coste: " + String.format("%.2f €", cost);
        resumen += "\n      Ultimo voto (fecha): " + lastVote;
        return resumen;
    }

}
