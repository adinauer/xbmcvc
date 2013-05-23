package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class ShowOsdCommand
        extends
            Command {
    
    public ShowOsdCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.ShowOSD");
    }
}
