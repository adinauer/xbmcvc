package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class VolumeUpCommand
        extends
            VolumeCommand {
    
    public VolumeUpCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    protected Integer adjustVolume(Integer volume) {
        volume += VOLUME_CHANGE_AMOUNT;
        
        volume = volume > 100 ? 100 : volume;
        
        return volume;
    }
}
