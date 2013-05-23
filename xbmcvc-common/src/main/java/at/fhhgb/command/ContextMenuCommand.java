package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class ContextMenuCommand
        extends
            Command {
    
    
    public ContextMenuCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson("Input.ContextMenu");
    }
}
