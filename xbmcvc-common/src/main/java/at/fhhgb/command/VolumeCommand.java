package at.fhhgb.command;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.fhhgb.xbmc.XbmcCommunicator;


public abstract class VolumeCommand
        extends
            Command {
    
    protected static final int VOLUME_CHANGE_AMOUNT = 10;
    
    public VolumeCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        Integer volume = getVolume();
        
        volume = adjustVolume(volume);
        
        communicator.sendJsonWithParameters("Application.SetVolume", "\"volume\": " + volume);
    }
    
    protected abstract Integer adjustVolume(Integer volume);
    
    private Integer getVolume() {
        String result = communicator.sendJsonWithParameters("Application.GetProperties", "\"properties\": [ \"volume\" ]");
        
        Integer volume = extractVolume(result);
        
        if (volume == null) {
            throw new RuntimeException("Could not get current volume. ERROR: " + result);
        }
        return volume;
    }
    
    private Integer extractVolume(String result) {
        String volume = null;
        Pattern p = Pattern.compile("volume\":(\\d+)");
        Matcher matcher = p.matcher(result);
        if (matcher.find()) {
            volume = matcher.group(1);
        }
        
        if (volume == null) {
            return null;
        }
        
        return Integer.parseInt(volume);
    }
}
