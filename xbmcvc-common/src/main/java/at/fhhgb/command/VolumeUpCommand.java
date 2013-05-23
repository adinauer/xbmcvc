package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class VolumeUpCommand
        extends
            Command {
    
    public VolumeUpCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        System.out.println("increasing volume ...");
    }
}
