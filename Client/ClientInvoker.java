package Client;

import java.util.ArrayList;

public class ClientInvoker {
    private ArrayList<String> commands;
    private CommandCaller commandCaller;

    public ClientInvoker(CommandCaller commandCaller){
        this.commandCaller = commandCaller;
        this.commands = commandCaller.getCommandList();
    }


    public ArrayList<String> getCommands() {
        return commands;
    }

    public boolean recognizeCommand(String commandCandidate){

        if (commands.contains(commandCandidate)){
           // System.out.println("Команда " + commandCandidate + " распознана");
            return true;
        }
        System.out.println("Комманда " + commandCandidate + " не распознана");
        return false;
    }
}
