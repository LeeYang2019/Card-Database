package edu.yang.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TcgPlayerAPI {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
    private static final String clientId = "1BA51A45-070C-4729-9F0A-EF49A52D98CD";//clientId
    private static final String clientSecret = "4153F817-ADAA-4327-8988-250356C7A389";//client secret
    private static final String tokenUrl = "https://api.tcgplayer.com/token";
    private static final String auth = "&client_id=" + clientId + "&client_secret=" + clientSecret + "\"";

    public HttpURLConnection getConnection(String urlString, int parameter, boolean doOutput, String requestMethod) {

        String autToken2 = "-6_80xFIHbdtKHidNcclVivwwgsGM_Z07eHy7eKJ36caJ7Jle5VQ45YEkMWVR9KFs7u12YY9ZtcbHamSiSM4gExVB3QYLtpWvy1aj88Kt9gm8j1wLek2EDj-pum_OmOstBcNDFoHohmPJHogQDV50FscRQTZ0sUOBQZj6chttHVH_sOrDR1hUd4___dov8BACfGH3raK-QDMUh7IdY9SaMC8I2YZnmOZOXMWL4wh1gBUOfa8NKrwColtjKapZlAqHBMMElaKWnGoVZ04lzxUxv3KI7QcziJhE8CYeYiZ9VoKBCY01uUJqjzzmK5oNT0Aafxpuw";
        String bearerToken = "bearer " + autToken2;

        HttpURLConnection connection = null;
        URL url = null;

        try {
            //if method is GET then append parameter, otherwise do not append parameter
            if (requestMethod.equalsIgnoreCase("GET")) {
                url = new URL(urlString + parameter);
            } else {
                url = new URL(urlString);
            }

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", bearerToken);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(doOutput);
            connection.setRequestMethod(requestMethod);

        } catch (ProtocolException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    public String processPostRequest(String urlString, int productId, boolean doOutput, String requestMethod, String productName, String productSet, String productRarity) {

        BufferedReader buffReader = null;
        StringBuilder response = null;

        try {

            HttpURLConnection connection = getConnection(urlString, productId, doOutput, requestMethod);

            String jsonInputString = "{\"sort\": \"MinPrice DESC\",\"limit\": 10,\"offset\": 0,\"filters\":" +
                    " [{\"name\": \"ProductName\",\"values\": [ \" " + productName + " \"]},{\"name\": \"SetName\",\"values\":" +
                    " [\"" + productSet + "\"]},{\"name\": \"Rarity\",\"values\": [\"" + productRarity + "\"]}]}";

            OutputStream os = connection.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);

            buffReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"));

            response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = buffReader.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "There is no card by that name";
    }

    public String processGetRequest(String urlString, int productId, boolean doOutput, String requestMethod) {

        BufferedReader reader = null;
        String response = "";

        try {
            HttpURLConnection connection = getConnection(urlString, productId, doOutput, requestMethod);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);

            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            response = out.toString();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }


    /**
     *
     * @param productID
     * @return String imageUrl
     */
    public String getProductImage(int productID) {
        String urlString = "http://api.tcgplayer.com/v1.32.0/catalog/products/";
        return processGetRequest(urlString, productID, false, "GET");
    }

    /**
     *
     * @param productID
     * @return double marketPrice
     */
    public String getMarketPrice(int productID) {
        String urlString = "http://api.tcgplayer.com/v1.32.0/pricing/product/";
        return processGetRequest(urlString, productID, false, "GET");
    }

    /**
     * looks up the productId for a card based on name, set, and rarity
     * @param productName
     * @param productSet
     * @param productRarity
     * @return int productId
     */
    public String getCardDetails(String productName, String productSet, String productRarity) {
        String urlString = "http://api.tcgplayer.com/v1.32.0/catalog/categories/2/search";
        return processPostRequest(urlString, 0,true, "POST", productName, productSet, productRarity);
    }
}
