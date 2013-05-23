package at.fhhgb;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhhgb.command.Command;
import at.fhhgb.command.CommandNotFoundException;
import at.fhhgb.command.MuteCommand;
import at.fhhgb.command.VolumeUpCommand;
import at.fhhgb.xbmc.XbmcCommunicator;


public class CommandTranslatorTest {
    private CommandRepository repository;
    private CommandTranslator    locator;
    private XbmcCommunicator  communicator;
    
    @Before
    public void createCommandLocator() {
        repository = new CommandRepository();
        locator = new CommandTranslator(repository);
        communicator = null;
    }
    
    @Test
    public void locatesCommandThatMatchesGivenStringExactly() {
        repository.addCommand("louder", new VolumeUpCommand(communicator));
        
        Command command = locator.locate("louder");
        
        assertTrue(VolumeUpCommand.class.isAssignableFrom(command.getClass()));
    }
    
    @Test
    public void locatesCommandThatHasExtraWords() {
        repository.addCommand("mute( please)?", new MuteCommand(communicator));
        
        Command command = locator.locate("mute");
        Command commandWithExtraWords = locator.locate("mute please");
        
        assertTrue(MuteCommand.class.isAssignableFrom(command.getClass()));
        assertTrue(MuteCommand.class.isAssignableFrom(commandWithExtraWords.getClass()));
    }
    
    @Test(expected = CommandNotFoundException.class)
    public void throwsIfCommandWasNotFound() {
        locator.locate("NOT A REAL COMMAND");
    }
    
    @Test
    public void locatesCommandThatIsInDifferentCase() {
        repository.addCommand("louder", new VolumeUpCommand(communicator));
        
        Command command = locator.locate("Louder");
        
        assertTrue(VolumeUpCommand.class.isAssignableFrom(command.getClass()));
    }
    
    @Test
    public void locatesFirstCommandInList() {
        repository.addCommand("louder", new VolumeUpCommand(communicator));
        repository.addCommand("mute", new MuteCommand(communicator));
        
        Command command = locator.locateFirstOf(commandsAsList("mute", "louder"));
        
        assertTrue(MuteCommand.class.isAssignableFrom(command.getClass()));
    }
    
    @Test(expected = CommandNotFoundException.class)
    public void throwsIfNoneOfTheCommandsIsRecognized() {
        locator.locateFirstOf(commandsAsList("NOT A REAL COMMAND", "NEITHER IS THIS"));
    }
    
    @Test
    public void recognizesCommandEvenIfUnrecognizableCommandsAreFirstInList() {
        repository.addCommand("mute", new MuteCommand(communicator));
        
        Command command = locator.locateFirstOf(commandsAsList("NOT A REAL COMMAND", "NEITHER IS THIS", "mute"));
        
        assertTrue(MuteCommand.class.isAssignableFrom(command.getClass()));
    }
    
    private List<String> commandsAsList(String... commands) {
        List<String> commandList = new ArrayList<>();
        
        for (String command : commands) {
            commandList.add(command);
        }
        
        return commandList;
    }
}