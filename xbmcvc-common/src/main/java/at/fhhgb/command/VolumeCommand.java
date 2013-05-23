package at.fhhgb.command;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.fhhgb.xbmc.XbmcCommunicator;


public abstract class VolumeCommand
        extends
            Command {
    
    private static final String GET_VOLUME_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Application.GetProperties\", \"params\": { \"properties\": [ \"volume\" ] }, \"id\": 1}";
    private static final String SET_VOLUME_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Application.SetVolume\", \"params\": { \"volume\": %s }, \"id\": 1}";
    protected static final int VOLUME_CHANGE_AMOUNT = 10;
    
    public VolumeCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        Integer volume = getVolume();
        
        volume = calculateNewVolume(volume);
        System.out.println("new volume: " + volume);
        
        communicator.sendJson(String.format(SET_VOLUME_JSON, volume));
    }
    
    protected abstract Integer calculateNewVolume(Integer volume);
    
    private Integer getVolume() {
        String result = communicator.sendJson(GET_VOLUME_JSON);
        
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
