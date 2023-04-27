package Client;

import Server.commands.Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandCaller implements Serializable {
    String commandName;
    String commandParam;
    ArrayList<String> commandList = new ArrayList<>(Arrays.asList("exit", "remove_all_by_chapter" ,"help","show", "info", "remove_greater", "add", "add_if_max", "execute_script", "clear", "filter_contains_name", "print_unique_chapter", "remove_lower", "update_id", "remove_by_id"));

    public CommandCaller(String commandName){
        this.commandName = commandName;
    }
    public String getCommandName() {
        return commandName;
    }
    public String getCommandParam(){
        return commandParam;
    }

    public ArrayList<String> getCommandList() {
        return commandList;
    }

    public void setCommandParam(String commandParam) {
        this.commandParam = commandParam;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void collectInfo(){

    }
}
