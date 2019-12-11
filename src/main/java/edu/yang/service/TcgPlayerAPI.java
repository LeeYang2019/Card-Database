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

    public String getProductImage(int productID) {
        String urlString = "http://api.tcgplayer.com/v1.32.0/catalog/products/";
        return processGetRequest(urlString, productID, false, "GET");
    }

    public String getMarketPrice(int productID) {
        String urlString = "http://api.tcgplayer.com/v1.32.0/pricing/product/";
        return processGetRequest(urlString, productID, false, "GET");
    }

    public String searchCard(String paramOne, String paramTwo, String ParamThree) {
        String autToken2 = "-6_80xFIHbdtKHidNcclVivwwgsGM_Z07eHy7eKJ36caJ7Jle5VQ45YEkMWVR9KFs7u12YY9ZtcbHamSiSM4gExVB3QYLtpWvy1aj88Kt9gm8j1wLek2EDj-pum_OmOstBcNDFoHohmPJHogQDV50FscRQTZ0sUOBQZj6chttHVH_sOrDR1hUd4___dov8BACfGH3raK-QDMUh7IdY9SaMC8I2YZnmOZOXMWL4wh1gBUOfa8NKrwColtjKapZlAqHBMMElaKWnGoVZ04lzxUxv3KI7QcziJhE8CYeYiZ9VoKBCY01uUJqjzzmK5oNT0Aafxpuw";
        String bearerToken = "bearer " + autToken2;
        BufferedReader buffReader = null;

        try {

            URL url = new URL("http://api.tcgplayer.com/v1.32.0/catalog/categories/2/search");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", bearerToken);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            connection.setRequestMethod("Post");

            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("name", "productName");

            String jsonOutput = new ObjectMapper().writeValueAsString(jsonMap);
            logger.info(jsonOutput);

                    /*
                    `{'name': 'SetName', 'Values': ['The Legend of Blue Eyes White Dragon']},` +
                                     `{'name': 'Rarity', 'Values': ['Ultra']}`;

            OutputStream os = connection.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);

            buffReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"));

            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = buffReader.readLine()) != null) {
                response.append(responseLine.trim());
            }

            return response.toString();
*/
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    public String processGetRequest(String urlString, int parameter, boolean doOutput, String requestMethod) {

        BufferedReader reader = null;
        String response = "";

        try {
            HttpURLConnection connection = getConnection(urlString, parameter, doOutput, requestMethod);

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
}
