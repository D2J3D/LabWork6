package Server;


import Server.commands.*;

import java.util.HashMap;

public class ServerInvoker {
    private HashMap<String, Command> commands = new HashMap<>();
    CollectionManager cm;

    public ServerInvoker(){}

    public ServerInvoker(CollectionManager cm){
        this.cm = cm;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public Command formCommand(String commandName, String commandParam){
        commands.put("help", new HelpCommand(cm));
        commands.put("show", new ShowCommand(cm));
        commands.put("info", new InfoCommand(cm));
        commands.put("clear", new ClearCommand(cm));
        commands.put("print_unique_chapter", new PrintUniqueChapter(cm));
        commands.put("add_if_max", new AddIfMaxCommand(cm));
        commands.put("filter_contains_name", new FilterContainsNameCommand(cm));
        commands.put("remove_all_by_chapter", new RemoveAllByChapter(cm));
        commands.put("remove_by_id", new RemoveById(cm));
        commands.put("print_descending", new PrintDescendingCommand(cm));
        commands.put("execute_script", new ExecuteScriptCommand(cm));
        commands.put("add", new AddCommand(cm));
        commands.put("remove_greater", new RemoveGreaterCommand(cm));
        commands.put("remove_lower", new RemoveLowerCommand(cm));
        commands.put("update_id", new UpdateIdCommand(cm));
        commands.put("exit", new ExitCommand(cm));
        Command command = commands.get(commandName);
        if (commandParam!=null){
            command.setParamName(commandParam);
        }
        return command;
    }
}
