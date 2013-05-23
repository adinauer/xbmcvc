package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateRightCommand
        extends
            Command {
    
    public NavigateRightCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Right");
    }
}
