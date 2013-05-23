package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateDownCommand
        extends
            Command {
    
    public NavigateDownCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Down");
    }
}
