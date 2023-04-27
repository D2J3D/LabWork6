package Server.commands;

import Server.CollectionManager;

public class ExitCommand implements Command{
    CollectionManager cm;

    public ExitCommand(CollectionManager cm){
        this.cm = cm;
    }

    @Override
    public void execute() {
        SaveCommand sv = new SaveCommand(cm);
        sv.execute();
    }

    @Override
    public String descr() {
        return null;
    }
}
