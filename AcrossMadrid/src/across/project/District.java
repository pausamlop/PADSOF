package across.project;

import java.util.*;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 


public class District{
    private ArrayList<String> districts = new ArrayList<>();
    private String name;

    public District(){
        readFromFile();
    }

    public District(String district){
        readFromFile();
        this.name = district;
    }
    
    public ArrayList<String> getDistritos(){
        return this.districts;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean readFromFile(){
        try {
        File file = new File("Distritos.txt");
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            this.districts.add(data);
        }
        myReader.close();
        return true;

        } catch (FileNotFoundException e) {
        return false;
        }
    }



}
