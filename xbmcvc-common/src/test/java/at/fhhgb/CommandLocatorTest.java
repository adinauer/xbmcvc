package at.fhhgb;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.fhhgb.command.Command;
import at.fhhgb.command.CommandNotFoundException;
import at.fhhgb.command.MuteCommand;
import at.fhhgb.command.VolumeUpCommand;
import at.fhhgb.xbmc.XbmcCommunicator;


public class CommandLocatorTest {
    private CommandRepository repository;
    private CommandLocator    locator;
    private XbmcCommunicator  communicator;
    
    @Before
    public void createCommandLocator() {
        repository = new CommandRepository();
        locator = new CommandLocator(repository);
        communicator = null;
    }
    
    @Test
    public void locatesCommandThatMatchesGivenStringExactly() {
        repository.addCommandPattern("louder", new VolumeUpCommand(communicator));
        
        Command command = locator.locate("louder");
        
        assertTrue(VolumeUpCommand.class.isAssignableFrom(command.getClass()));
    }
    
    @Test
    public void locatesCommandThatHasExtraWords() {
        repository.addCommandPattern("mute( please)?", new MuteCommand(communicator));
        
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
        repository.addCommandPattern("louder", new VolumeUpCommand(communicator));
        
        Command command = locator.locate("Louder");
        
        assertTrue(VolumeUpCommand.class.isAssignableFrom(command.getClass()));
    }
}