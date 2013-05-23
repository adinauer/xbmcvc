package at.fhhgb;


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
        for (String commandPattern : commands.getCommandPatterns()) {
            if (matchesPattern(commandPattern, commandAsRawString)) {
                return commands.getCommandForPattern(commandPattern);
            }
        }
        
        throw new CommandNotFoundException("Could not find command: " + commandAsRawString);
    }
    
    private boolean matchesPattern(String commandPattern, String commandAsRawString) {
        Pattern p = Pattern.compile(commandPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(commandAsRawString);
        
        boolean matchesPattern = matcher.matches();
        return matchesPattern;
    }
}
