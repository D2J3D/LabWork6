package Server.commands;

import Server.CollectionManager;
import Server.core.*;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateIdCommand implements Command, Serializable {
    CollectionManager cm;
    Integer newId;
    public UpdateIdCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute() {
        SpaceMarine candidate = cm.gatherSpaceMarineParams();

        for (SpaceMarine sp : this.cm.getSpaceMarines()){
            if(sp.getName().equals(candidate.getName())){
                System.out.println("Found this guy ID №" + sp.getId());
                try{
                    candidate = sp;
                    if (String.valueOf(newId).length() > 0){
                        candidate.setId(newId);
                        System.out.println("ID is changed to " + newId);
                    } else{
                        System.out.println("Failed to change id, try again");
                    }

                } catch (Exception e){
                    System.out.println(e + "!");
                }
                break;
            }
        }

    }

    @Override
    public String descr() {
        return "update id command";
    }

    @Override
    public void setParamName(String newId) {
        this.newId = Integer.parseInt(newId);
    }
}
