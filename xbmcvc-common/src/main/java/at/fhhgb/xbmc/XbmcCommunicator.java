package at.fhhgb.xbmc;


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


public class XbmcCommunicator {
    private final String urlString;
    
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
