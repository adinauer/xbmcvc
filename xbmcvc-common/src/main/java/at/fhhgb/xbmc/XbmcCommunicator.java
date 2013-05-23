package at.fhhgb.xbmc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
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
    private static final String GET_PLAYER_ID_METHOD = "Player.GetActivePlayers";
    private static final String XBMC_JSON_TEMPLATE   = "{\"jsonrpc\": \"2.0\", \"method\": \"%s\", \"params\": { %s }, \"id\": 1}";
    private static final String GET_PLAYER_ID_JSON   = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.GetActivePlayers\", \"id\": 1}";
    
    private final String        urlString;
    
    public XbmcCommunicator(String host, String port) {
        urlString = "http://" + host + ":" + port + "/jsonrpc";
    }
    
    public String sendJson(String method) {
        return sendJson(method, "");
    }
    
    public String sendJson(String method, String parameters) {
        String result = null;
        String json = String.format(XBMC_JSON_TEMPLATE, method, parameters);
        
        System.out.println(json);
        
        try {
            result = sendRequest(json, urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(result);
        
        return result;
    }
    
    public void sendJsonIncludingPlayerId(String method, String parameters) {
        String result = sendJson(GET_PLAYER_ID_METHOD);
        
        String playerId = extractPlayerId(result);
        if (playerId == null) {
            throw new PlayerNotRunningException(
                    "Could not get Player-ID. Are you sure a player is running inside XBMC? ERROR: " + result);
        }
        
        sendJson(method, String.format(parameters, playerId));
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
        
        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (ConnectException e) {
            throw new RuntimeException(
                    "Could not connecto to XBMC. Are you sure XBMC is running and Allow control of XBMC via HTTP is checked in System > Settings > Services > Webserver? URL: "
                            + urlString);
        }
        
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
