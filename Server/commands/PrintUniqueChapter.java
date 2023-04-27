package Server.commands;

import Server.CollectionManager;

import java.io.Serializable;

public class PrintUniqueChapter implements Command, Serializable {
    CollectionManager cm;
    public PrintUniqueChapter(CollectionManager cm){
        this.cm = cm;
    }
    @Override
    public void execute() {
        cm.printUniqueChapter();
    }

    @Override
    public String descr() {
        return null;
    }
}
