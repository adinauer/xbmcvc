package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class BackCommand
        extends
            Command {
    
    public BackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.Back");
    }
}
