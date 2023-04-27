package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class InfoCommand implements Command, Serializable {
    private CollectionManager cm;
    public InfoCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute() {
        cm.showCollectionInfo();
    }

    @Override
    public String descr() {
        return null;
    }
}
