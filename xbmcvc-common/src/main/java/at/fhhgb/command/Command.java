package at.fhhgb.command;


import at.fhhgb.xbmc.XbmcCommunicator;


public abstract class Command {
    protected final XbmcCommunicator communicator;
    
    public Command(XbmcCommunicator communicator) {
        this.communicator = communicator;
    }
    
    public abstract void execute();
}
