package across.project;

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

    private List<UserCollective> creators = new ArrayList<>();
    private List<UserCollective> voters = new ArrayList<>();
    private List<User> followers = new ArrayList<>();
    private List<Notification> notification = new ArrayList<>();

    public Project(  String name,   String dcp,   double cost,   int votes,   Date lastVote,  projectState state) {
        this.name = name;
        this.dcp = dcp;
        this.cost = cost;
        this.votes = votes;
        this.lastVote = lastVote;
        this.state = state;
        nonValidatedProjects.add(this);
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

          Project ejemplo = new Project("Ejemplo", "DCP", 10049, 0, creationDate, projectState.InValidation);

        System.out.println(checkExpire(ejemplo));
    }





    /************** METODOS SIN PROBAR ***************/



    /**
     * Valida un proyecto
     */
    public void validateProject(){
        setProjects(getProjects().add(this));
        setNonValidatedProjects(getNonValidatedProjects().remove(this));
    }


	/**
     * Rechaza un proyecto
     */
    public void rejectProject(){
        setNonValidatedProjects(getNonValidatedProjects().remove(this));
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

        ArrayList<Projects> newArray= u.getFollowedProjects();
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
            ArrayList<Projects> newArray= u.getFollowedProjects();
            newArray.remove(this);
            u.setFollowedProjects(newArray);
        }

    }

    public void vote (UserCollective uc){

    }
    
    public boolean checkVote(UserCollective uc){
        
    }
    public int countNotVoted(Collective c){

    }

    
    Double sendToFinance(){

    }


    //checkName(name: String): Status ??

}