package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class ShowCommand implements Command, Serializable {
    CollectionManager cm;
    public ShowCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        cm.showMarines();
    }

    @Override
    public String descr() {
        return "Команда show";
    }
}
