package at.fhhgb.command;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;




public class MuteCommand
        implements
            Command {
    
    public void execute() {
        System.out.println("muting ...");
        
        String json =
                "{\"jsonrpc\": \"2.0\", \"method\": \"Player.PlayPause\", \"params\": { \"playerid\": 1 }, \"id\": 1}";
        // String json = "{\"jsonrpc\": \"2.0\", \"method\": \"Player.GetActivePlayers\", \"id\": 1}";
        String urlString = "http://localhost:8082/jsonrpc";
        
        try {
            sendRequest(json, urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(String json, String urlString) throws UnsupportedEncodingException, IOException, ClientProtocolException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(urlString);
        
        StringEntity entity2 = new StringEntity(json, HTTP.UTF_8);
        entity2.setContentType("application/json");
        httppost.setEntity(entity2);
        
        HttpResponse response = httpclient.execute(httppost);
        extractResponseText(response);
    }
    
    private void extractResponseText(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        
        System.out.println(response.getStatusLine());
        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } finally {
                instream.close();
            }
        }
    }
}
