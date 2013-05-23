package at.fhhgb.command;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.fhhgb.xbmc.XbmcCommunicator;


public class StopPlaybackCommand
        extends
            Command {
    
    private static final String GET_PLAYER_ID_JSON     = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.GetActivePlayers\", \"id\": 1}";
    private static final String TOGGLE_PLAY_PAUSE_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.Stop\", \"params\": { \"playerid\": %s }, \"id\": 1}";
    
    public StopPlaybackCommand(XbmcCommunicator communicator) {
        super(communicator);
    }
    
    @Override
    public void execute() {
        String result = communicator.sendJson(GET_PLAYER_ID_JSON);
        
        String playerId = extractPlayerId(result);
        if (playerId == null) {
            throw new RuntimeException(
                    "Could not get Player-ID. Are you sure something is opened in the player? ERROR: " + result);
        }
        communicator.sendJson(String.format(TOGGLE_PLAY_PAUSE_JSON, playerId));
    }
    
    private String extractPlayerId(String result) {
        String playerId = null;
        Pattern p = Pattern.compile("playerid\":(\\d+)");
        Matcher matcher = p.matcher(result);
        if (matcher.find()) {
            playerId = matcher.group(1);
        }
        return playerId;
    }
}
