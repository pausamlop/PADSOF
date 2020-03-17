package across;
import java.util.*;
import java.time.*;

enum projectState{
    InValidation, Accepted, Rejected, VotesReached, Sent, Financed, NotFinanced, Expired 
}

public abstract class Project{
    private String name;
    private String dcp;
    private double cost;
    private int votes;
    private Date lastVote;
    private projectState state;

    public Project (String name, String dcp, double cost, int votes, Date lastVote, projectState state){
        this.name = name;
        this.dcp = dcp;
        this.cost = cost;
        this.votes = votes;
        this.lastVote = lastVote;
        this.state = state;
    }
    
    public void setName(String name) { this.name = name; }
    public void setDescription(String dcp) { this.dcp = dcp; }
    public void setCost(double cost) { this.cost = cost; }
    public void setVotes(int votes) { this.votes = votes; }
    public void setLastVote(Date lastVote) { this.lastVote = lastVote; }
    public void setprojectState(projectState state) { this.state = state; }
    
    public String getName() { return name; }
    public String getDescription() { return dcp; }
    public double getCost() { return cost; }
    public int getVotes() { return votes; }
    public Date getLastVote() { return lastVote; }
    public projectState getProjectState() { return state; }


    /*public boolean checkExpire(Application app){
    int maxDays=app.getDaysExpiration();
    Date date = new Date();
    }*/

    public static void main(String[] args){
        Date date = new Date();
        int test = date.getDayOfMonth;

        System.out.print(test);
    } 
}