package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class NavigateLeftCommand
        extends
            Command {
    
    public NavigateLeftCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Left");
    }
}
