package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class PrintDescendingCommand implements Command, Serializable {
    CollectionManager cm;
    public PrintDescendingCommand(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute() {
        cm.sortMarines();
        cm.showMarines();
    }

    @Override
    public String descr() {
        return "print_descending";
    }
}
