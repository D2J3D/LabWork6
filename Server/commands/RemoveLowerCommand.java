package Server.commands;

import Server.CollectionManager;
import Server.core.*;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveLowerCommand implements Command, Serializable {
    CollectionManager cm;

    public RemoveLowerCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute(){
        SpaceMarine candidate = cm.gatherSpaceMarineParams();
        for (SpaceMarine sp : this.cm.getSpaceMarines()){
            if(sp.getName().equals(candidate.getName())){

                System.out.println("Find the guy " + sp.getId());
                try{
                    candidate = sp;
                    this.cm.removeLower(candidate);
                } catch (Exception e){
                    System.out.println(e + "!");
                }
                break;
            }
        }
    }

    @Override
    public String descr() {
        return null;
    }

    @Override
    public void setParamName(String param) {
        Command.super.setParamName(param);
    }
}
