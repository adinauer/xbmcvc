package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public class ContextMenuCommand
        extends
            Command {
    
    private static final String CONTEXT_MENU_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Input.ContextMenu\", \"id\": 1}";
    
    public ContextMenuCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        communicator.sendJson(CONTEXT_MENU_JSON);
    }
}
