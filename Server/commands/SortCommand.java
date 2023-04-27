package Server.commands;

import Server.CollectionManager;
import Server.core.Chapter;

public class SortCommand implements Command{
    CollectionManager cm;

    public SortCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        cm.sortMarines();
    }

    @Override
    public String descr() {
        return null;
    }
}
