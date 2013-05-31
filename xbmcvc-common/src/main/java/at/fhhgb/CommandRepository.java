package at.fhhgb;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import at.fhhgb.command.Command;


public class CommandRepository {
    private Map<String, Command> commandPatterns = new HashMap<String, Command>();
    
    public void addCommand(String commandPattern, Command command) {
        commandPatterns.put(commandPattern, command);
    }
    
    public Set<String> getCommandPatterns() {
        return commandPatterns.keySet();
    }
    
    public Command getCommandForPattern(String commandPattern) {
        return commandPatterns.get(commandPattern);
    }
}
