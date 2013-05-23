package at.fhhgb.xbmc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class XbmcCommunicator {
    private static final String GET_PLAYER_ID_JSON = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.GetActivePlayers\", \"id\": 1}";
    
    private final String        urlString;
    
    public XbmcCommunicator(String host, String port) {
        urlString = "http://" + host + ":" + port + "/jsonrpc";
    }
    
    public String sendJson(String json) {
        String result = null;
        
        try {
            result = sendRequest(json, urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public void sendJsonIncludingPlayerId(String command) {
        String result = sendJson(GET_PLAYER_ID_JSON);
        
        String playerId = extractPlayerId(result);
        if (playerId == null) {
            throw new PlayerNotRunningException(
                    "Could not get Player-ID. Are you sure a player is running inside XBMC? ERROR: " + result);
        }
        
        sendJson(String.format(command, playerId));
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
    
    private String sendRequest(String json, String urlString) throws UnsupportedEncodingException, IOException, ClientProtocolException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(urlString);
        
        StringEntity content = new StringEntity(json, "UTF-8");
        content.setContentType("application/json");
        
        httppost.setEntity(content);
        
        HttpResponse response = httpclient.execute(httppost);
        
        return extractResponseText(response);
    }
    
    private String extractResponseText(HttpResponse response) throws IOException {
        String result = null;
        HttpEntity entity = response.getEntity();
        
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            } finally {
                instream.close();
            }
        }
        
        return result;
    }
}
