package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class VolumeDownCommand
        extends
            VolumeCommand {
    
    public VolumeDownCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    protected Integer calculateNewVolume(Integer volume) {
        volume -= VOLUME_CHANGE_AMOUNT;
        
        volume = volume < 0 ? 0 : volume;
        
        return volume;
    }
}
