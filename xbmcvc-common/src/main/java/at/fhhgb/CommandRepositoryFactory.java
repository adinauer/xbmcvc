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
import at.fhhgb.command.PlayerNextCommand;
import at.fhhgb.command.PlayerPreviousCommand;
import at.fhhgb.command.ResumePlaybackCommand;
import at.fhhgb.command.SelectCommand;
import at.fhhgb.command.ShowOsdCommand;
import at.fhhgb.command.StopPlaybackCommand;
import at.fhhgb.command.UnmuteCommand;
import at.fhhgb.command.VolumeDownCommand;
import at.fhhgb.command.VolumeUpCommand;
import at.fhhgb.xbmc.XbmcCommunicator;


public class CommandRepositoryFactory {
    
    public CommandRepository create(String host, String port) {
        CommandRepository repository = new CommandRepository();
        
        XbmcCommunicator communicator = new XbmcCommunicator(host, port);
        
        repository.addCommand("(louder|volume up)", new VolumeUpCommand(communicator));
        repository.addCommand("(softer|quieter|volume down)", new VolumeDownCommand(communicator));
        repository.addCommand("(mute|quiet|silence|shut up)", new MuteCommand(communicator));
        repository.addCommand("unmute", new UnmuteCommand(communicator));
        repository.addCommand("resume( playback)?", new ResumePlaybackCommand(communicator));
        repository.addCommand("pause( playback)?", new PausePlaybackCommand(communicator));
        repository.addCommand("stop( playback)?", new StopPlaybackCommand(communicator));
        repository.addCommand("home( screen)?", new NavigateHomeCommand(communicator));
        repository.addCommand("(navigate )?left", new NavigateLeftCommand(communicator));
        repository.addCommand("(navigate )?right", new NavigateRightCommand(communicator));
        repository.addCommand("(navigate )?up", new NavigateUpCommand(communicator));
        repository.addCommand("(navigate )?down", new NavigateDownCommand(communicator));
        repository.addCommand("(select|ok|play)", new SelectCommand(communicator));
        repository.addCommand("(back|escape)", new BackCommand(communicator));
        repository.addCommand("info", new InfoCommand(communicator));
        repository.addCommand("(right click|context( menu)?|options|menu)", new ContextMenuCommand(communicator));
        repository.addCommand("(show )?controls", new ShowOsdCommand(communicator));
        repository.addCommand("next.*", new PlayerNextCommand(communicator));
        repository.addCommand("previous.*", new PlayerPreviousCommand(communicator));
        
        return repository;
    }
    
}
