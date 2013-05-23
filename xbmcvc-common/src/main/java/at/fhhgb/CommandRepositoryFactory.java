package at.fhhgb;


import at.fhhgb.command.BackCommand;
import at.fhhgb.command.ContextMenuCommand;
import at.fhhgb.command.InfoCommand;
import at.fhhgb.command.MuteCommand;
import at.fhhgb.command.NavigateDownCommand;
import at.fhhgb.command.NavigateHomeCommand;
import at.fhhgb.command.NavigateLeftCommand;
import at.fhhgb.command.NavigateRightCommand;
import at.fhhgb.command.NavigateUpCommand;
import at.fhhgb.command.PausePlaybackCommand;
import at.fhhgb.command.ResumePlaybackCommand;
import at.fhhgb.command.SelectCommand;
import at.fhhgb.command.StopPlaybackCommand;
import at.fhhgb.command.UnmuteCommand;
import at.fhhgb.command.VolumeDownCommand;
import at.fhhgb.command.VolumeUpCommand;
import at.fhhgb.xbmc.XbmcCommunicator;


public class CommandRepositoryFactory {
    
    public CommandRepository create() {
        CommandRepository repository = new CommandRepository();
        
        XbmcCommunicator communicator = new XbmcCommunicator("localhost", "8082");
        
        repository.addCommandPattern("(louder|volume up)", new VolumeUpCommand(communicator));
        repository.addCommandPattern("(softer|quieter|volume down)", new VolumeDownCommand(communicator));
        repository.addCommandPattern("(mute|quiet|silence|shut up)", new MuteCommand(communicator));
        repository.addCommandPattern("unmute", new UnmuteCommand(communicator));
        repository.addCommandPattern("resume( playback)?", new ResumePlaybackCommand(communicator));
        repository.addCommandPattern("pause( playback)?", new PausePlaybackCommand(communicator));
        repository.addCommandPattern("stop( playback)?", new StopPlaybackCommand(communicator));
        repository.addCommandPattern("home( screen)?", new NavigateHomeCommand(communicator));
        repository.addCommandPattern("(navigate )?left", new NavigateLeftCommand(communicator));
        repository.addCommandPattern("(navigate )?right", new NavigateRightCommand(communicator));
        repository.addCommandPattern("(navigate )?up", new NavigateUpCommand(communicator));
        repository.addCommandPattern("(navigate )?down", new NavigateDownCommand(communicator));
        repository.addCommandPattern("select", new SelectCommand(communicator));
        repository.addCommandPattern("(back|escape)", new BackCommand(communicator));
        repository.addCommandPattern("info", new InfoCommand(communicator));
        repository.addCommandPattern("(right click|context( menu)?|options)", new ContextMenuCommand(communicator));
        
        
        // commands todo: pause / play / stop / show(list of)? movies
        // show(list of)? tv shows / next / previous / info / trailer
        
        return repository;
    }
    
}
