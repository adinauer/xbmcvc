package at.fhhgb;


import at.fhhgb.command.MuteCommand;
import at.fhhgb.command.VolumeUpCommand;


public class CommandRepositoryFactory {
    
    public CommandRepository create() {
        CommandRepository repository = new CommandRepository();
        
        repository.addCommandPattern("louder", new VolumeUpCommand());
        repository.addCommandPattern("mute( please)?", new MuteCommand());
        // commands todo: pause / play / stop / show(list of)? movies
        // show(list of)? tv shows / next / previous / info / trailer
        
        return repository;
    }
    
}
