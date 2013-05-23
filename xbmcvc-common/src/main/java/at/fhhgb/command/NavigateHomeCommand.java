package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateHomeCommand
        extends
            Command {
    
    public NavigateHomeCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Home");
    }
}
