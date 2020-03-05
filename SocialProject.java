import java.util.*;

enum typeSocial{
    Nacional, International 
}

public class SocialProject extends Project {
    private String group;
    private typeSocial type;

    public SocialProject(String name, String dcp, double cost, int votes, Date lastVote, projectState state, String group, typeSocial type){
        super(name, dcp, cost, votes, lastVote, state);
        this.group = group;
        this.type = type;
    }

    public void setGroup(String group) { this.group = group; }
    public void settypeSocial(typeSocial type) { this.type = type; }

    public String getGroup() { return group; }
    public typeSocial gettypeSocial() { return type; }

}