package at.fhhgb;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.fhhgb.command.Command;
import at.fhhgb.command.CommandNotFoundException;


public class CommandTranslator {
    
    private static final String SIMON_SAYS_PREFIX = "simon says ";
    private CommandRepository commands;
    private boolean           simonSaysModeEnabled = false;
    
    public CommandTranslator(CommandRepository commands) {
        this.commands = commands;
    }
    
    public Command locate(String commandAsRawString) {
        Command command = locateSilently(commandAsRawString);
        
        if (command == null) {
            throw new CommandNotFoundException("Could not find command: " + commandAsRawString);
        }
        
        return command;
    }
    
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
        if (simonSaysModeEnabled) {
            commandPattern = SIMON_SAYS_PREFIX + commandPattern;
        }
        Pattern p = Pattern.compile(commandPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(commandAsRawString);
        
        return matcher.matches();
    }
    
    public void enableSimonSaysMode() {
        simonSaysModeEnabled = true;
    }
    
    public void disableSimonSaysMode() {
        simonSaysModeEnabled = false;
    }
}
