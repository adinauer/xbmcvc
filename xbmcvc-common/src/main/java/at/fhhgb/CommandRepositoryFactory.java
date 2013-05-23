package at.fhhgb;


import at.fhhgb.command.MuteCommand;
import at.fhhgb.command.TogglePlaybackCommand;
import at.fhhgb.command.VolumeUpCommand;
import at.fhhgb.xbmc.XbmcCommunicator;


public class CommandRepositoryFactory {
    
    public CommandRepository create() {
        CommandRepository repository = new CommandRepository();
        
        XbmcCommunicator communicator = new XbmcCommunicator("localhost", "8082");
        
        repository.addCommandPattern("louder", new VolumeUpCommand(communicator));
        repository.addCommandPattern("mute( please)?", new MuteCommand(communicator));
        repository.addCommandPattern("toggle playback", new TogglePlaybackCommand(communicator));
        
        
        // commands todo: pause / play / stop / show(list of)? movies
        // show(list of)? tv shows / next / previous / info / trailer
        
        return repository;
    }
    
}
