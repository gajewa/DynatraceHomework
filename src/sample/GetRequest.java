package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class GetRequest {
    private URL connectionURL;
    private URLConnection connection;
    private String username;
    private String password;

    public GetRequest(String path) throws IOException {
        this.connectionURL = new URL(path);
    }

    public void setAuthData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getNextDataset() throws IOException {
        this.connection = connectionURL.openConnection();
        this.setAuthHeader();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return in.readLine();
    }

    private void setAuthHeader(){
        String encoded = Base64.getEncoder().encodeToString((this.username+":"+this.password).getBytes(StandardCharsets.UTF_8));
        this.connection.setRequestProperty("Authorization", "Basic "+encoded);
    }

}
