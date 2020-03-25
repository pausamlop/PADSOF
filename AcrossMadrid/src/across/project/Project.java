package across.project;
import across.application.*;
import across.enumerations.*;
import across.notification.*;
import across.user.*;
import java.util.*;

public class Project{
    private String name;
    private String dcp;
    private double cost;
    private int votes;
    private Date lastVote;
    private projectState state;

    private UserCollective creator;
    private List<UserCollective> voters = new ArrayList<>();
    private List<User> followers = new ArrayList<>();

    public Project(String name, String dcp, double cost) {
        this.name = name;
        this.dcp = dcp;
        this.cost = cost;
        this.votes = 0;
        this.lastVote = new Date();
        this.state = projectState.ENVALIDACION;

        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getNonValidatedProjects());
        p1.add(this);
        Application.getApplication().setNonValidatedProjects(p1);
    }

    public void setName(  String name) {
        this.name = name;
    }

    public void setDescription(  String dcp) {
        this.dcp = dcp;
    }

    public void setCost(  double cost) {
        this.cost = cost;
    }

    public void setVotes(  int votes) {
        this.votes = votes;
    }

    public void setLastVote(  Date lastVote) {
        this.lastVote = lastVote;
    }

    public void setprojectState(  projectState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return dcp;
    }

    public double getCost() {
        return cost;
    }

    public int getVotes() {
        return votes;
    }

    public Date getLastVote() {
        return lastVote;
    }

    public projectState getProjectState() {
        return state;
    }

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

    public static boolean checkExpire(/*Application app*/Project project){ 
        int maxDays= 0; //app.getDaysExpiration();
        Date actualDate = new Date(); 
        Date lastVote = project.getLastVote();
        int diff = getDifferenceDays(actualDate, lastVote);

        if(maxDays <= diff){ return true; }

        else{ return false; }
    }

    private static int getDifferenceDays(Date date1, Date date2){
        int day1 = date1.getDate();
        int day2 = date2.getDate();

        return Math.abs(day1-day2);
    }

    public static boolean checkVote(UserCollective newVoters[]){

        return true;
    }
     

    public static void main(  String[] args) {
          Date creationDate = new Date();

          Project ejemplo = new Project("Ejemplo", "DCP", 10049, 0, creationDate, projectState.InValidation());

        System.out.println(checkExpire(ejemplo));
    }





    /************** METODOS SIN PROBAR ***************/

    /**
     * Devuelve un set con todos los usuarios que han votado el proyecto
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
                set.add(((Collective) uc).getAllMembers());
            }

        }
        return set;
    }

    /**
     * Valida un proyecto
     */
    public void validateProject(){
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getProjects());
        p1.add(this);

        ArrayList<Project> p2 = new ArrayList<Project>();
        p2.addAll(Application.getApplication().getNonValidatedProjects());
        p2.remove(this);

        Application.getApplication().setProjects(p1);
        Application.getApplication().setNonValidatedProjects(p2);

        // Voto del creador
        vote(creator);

        // Actualizar la lista de proyectos creados por el uc
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(uc.getCreatedProjects());
        p1.add(this);
        uc.setCreatedProjects(p1);

    }


	/**
     * Rechaza un proyecto
     */
    public void rejectProject(){
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(Application.getApplication().getNonValidatedProjects());
        p1.remove(this);

        Application.getApplication().setNonValidatedProjects(p1);
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
     * Un usercollective vota a un proyecto
	 * 
     * @param uc usercollective
     */
    public void vote (UserCollective uc){

        // Comprobar si ha votado ya
        if (voters.contains(uc)) return;

        // Si vota como usuario
        if (uc.getClass().equals(User.class)) {
            voters.add(uc);
        }

        // Si vota como colectivo
        if (uc.getClass().equals(Collective.class)) {
            if ((Application.getApplication().getCurrentUser()).equals( ((Collective)uc).getManager() )){

                // Make Set con los votes
                Set<User> allVoters = makeSet();

                // Añadir todos los votantes del colectivo al numero de votos
                allVoters.addAll((Collective) uc).getAllMembers());

                // Actualizar el numero de votos del proyectos y la fecha del ultimo voto
                votes = allVoters.size();
                lastVote = new Date();

                // Añadir el colectivo a voters
                voters.add(uc);
            }
        }

        // Actualizar los proyectos votados en usercollective
        ArrayList<Project> p1 = new ArrayList<Project>();
        p1.addAll(uc.getVotedProjects());
        p1.add(this);
        uc.setVotedProjects(p1);

    }


    /**
     * Update de los votos de un proyecto cuando 
     * cambian los miembros de un colectivo
	 * 
     * @param c usercollective
     */
    public void updateVotes(Collective c){
        UserCollective collective = (UserCollective) c;

        for(User user : c.getMembers()){
            for(Project project : collective.getVotedProjects()){
                if(!project.checkVote((UserCollective) user)){
                    project.vote(user);
                }
            }
        }

        
    }
    
    


    /**
     * Funcion para revisar si un colectivo o un usuario concreto han votado un proyecto en cuestion
     * 
     * @param uc
     * @return true en caso de que haya votado al proyecto, false en caso contrario
     */
    public boolean checkVote(UserCollective uc){
        
        return false;
    }

    public int countNotVoted(Collective c){
        
        return 0;
    }

    
    // Double sendToFinance(){

    // }


    /*public boolean checkName(name: String){
        
    }*/

    public String toString(){
        String resumen = "";
        resumen += "Nombre: " + name;
        resumen += "\nDescripcion: " + dcp;
        resumen += "\nEstado: " + state.name();
        resumen += "\nVotos: " + votes + "/" + Application.getApplication().getMinVotes(); 
        resumen += "\nCoste: " + coste;
        resumen += "\nUltimo voto (fecha): " + lastVote;
        return resumen;
    }

}