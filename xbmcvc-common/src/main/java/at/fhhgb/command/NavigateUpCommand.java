package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateUpCommand
        extends
            Command {
    
    public NavigateUpCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Up");
    }
}
