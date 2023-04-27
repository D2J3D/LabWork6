package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class RemoveById implements Command, Serializable {
    CollectionManager cm;
    Integer id;

    public RemoveById(CollectionManager cm){
        this.cm = cm;
    }
    public RemoveById(CollectionManager cm, Integer id){
        this.id = id;
    }
    @Override
    public void execute() {
        cm.removeAllById(id);
        System.out.println("Soldier with ID  = " + id + " was removed\nRest soldiers:");
        cm.showMarines();
    }

    @Override
    public String descr() {
        return null;
    }

    @Override
    public void setParamName(String param) {
        this.id = Integer.parseInt(param);
    }
}
