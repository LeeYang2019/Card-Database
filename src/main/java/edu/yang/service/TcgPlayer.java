package edu.yang.service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TcgPlayer {

    private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
    private static final String clientId = "1BA51A45-070C-4729-9F0A-EF49A52D98CD";//clientId
    private static final String clientSecret = "4153F817-ADAA-4327-8988-250356C7A389";//client secret
    private static final String tokenUrl = "https://api.tcgplayer.com/token";
    private static final String auth = "&client_id=" + clientId + "&client_secret=" + clientSecret + "\"";
    //private static final String authentication = Base64.getEncoder().encodeToString(auth.getBytes());

    public String getClientCredentials() {
        String content = "grant_type=client_credentials" + "&client_id=" + clientId + "&client_secret=" + clientSecret + "\"";
        BufferedReader reader = null;
        HttpsURLConnection connection = null;
        String returnValue = "";
        try {
            URL url = new URL(tokenUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //connection.setRequestProperty();
            //connection.setRequestProperty("Authorization", "Basic " + content);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            PrintStream os = new PrintStream(connection.getOutputStream());
            os.print(content);
            os.close();

            System.out.println(connection);
            System.out.println(content);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String response = out.toString();
            Matcher matcher = pat.matcher(response);
            if (matcher.matches() && matcher.groupCount() > 0) {
                returnValue = matcher.group(1);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            connection.disconnect();
        }
        System.out.println(returnValue);
        return returnValue;
    }

    public void useBearerToken(String bearerToken) {
        BufferedReader reader = null;
        try {
            URL url = new URL("http://api.tcgplayer.com/v1.17.0/catalog/categories/24/search/manifest");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String response = out.toString();
            System.out.println(response);
        } catch (Exception e) {

        }
    }

}
