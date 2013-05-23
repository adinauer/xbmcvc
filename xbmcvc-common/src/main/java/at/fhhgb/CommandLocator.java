package at.fhhgb;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.fhhgb.command.Command;
import at.fhhgb.command.CommandNotFoundException;


public class CommandLocator {
    
    private CommandRepository commands;
    
    public CommandLocator(CommandRepository commands) {
        this.commands = commands;
    }
    
    public Command locate(String commandAsRawString) {
        Command command = locateSilently(commandAsRawString);
        
        if (command == null) {
            throw new CommandNotFoundException("Could not find command: " + commandAsRawString);
        }
        
        return command;
    }
    
    // TODO: add simon says mode to avoid unintentional commands
    
    public Command locateFirstOf(List<String> commandListAsStrings) {
        Command command = null;
        
        for (String commandAsString : commandListAsStrings) {
            command = locateSilently(commandAsString);
            
            if (command != null) {
                return command;
            }
        }
        
        throw new CommandNotFoundException("Commands not recognized");
    }
    
    private Command locateSilently(String commandAsRawString) {
        for (String commandPattern : commands.getCommandPatterns()) {
            if (matchesPattern(commandPattern, commandAsRawString)) {
                return commands.getCommandForPattern(commandPattern);
            }
        }
        
        return null;
    }
    
    private boolean matchesPattern(String commandPattern, String commandAsRawString) {
        Pattern p = Pattern.compile(commandPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(commandAsRawString);
        
        boolean matchesPattern = matcher.matches();
        return matchesPattern;
    }
}
