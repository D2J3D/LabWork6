package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class ClearCommand implements Command, Serializable {
    private CollectionManager cm;

    public ClearCommand(CollectionManager cm){
        this.cm =cm;
    }
    @Override
    public void execute() {
        cm.clearMarines();
        System.out.println("Collection is cleared");
    }

    @Override
    public String descr() {
        return null;
    }
}
