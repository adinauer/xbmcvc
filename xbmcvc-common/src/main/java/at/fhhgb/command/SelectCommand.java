package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class SelectCommand
        extends
            Command {
    
    public SelectCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Select");
    }
}
