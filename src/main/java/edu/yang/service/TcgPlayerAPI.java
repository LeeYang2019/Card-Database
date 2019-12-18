package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;



public class TcgPlayerAPI implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private Properties properties;

    public HttpURLConnection getConnection(String urlString, int parameter, boolean doOutput, String requestMethod) {

        String propertiesFile = "/indieproject.properties";
        properties = loadProperties(propertiesFile);
        String autToken2 = properties.getProperty("tcgPlayer.authenticationToken");
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
            return response;

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "There is no card by that name";
    }


    /**
     *
     * @param productID
     * @return String imageUrl
     */
    private List<ProductDetails> getProductDetails(int productID) {

        ObjectMapper objMapper = new ObjectMapper();
        List<ProductDetails> productDetailsList = new ArrayList<>();

        String urlString = "http://api.tcgplayer.com/v1.32.0/catalog/products/";

        try {
            JsonNode jsonNode = objMapper.readTree(processGetRequest(urlString, productID, false, "GET"));
            JsonNode resultsNode = jsonNode.get("results");
            productDetailsList = objMapper.readValue(resultsNode.toString(), new TypeReference<List<ProductDetails>>() {});
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return productDetailsList;
    }

    /**
     *
     * @param productID
     * @return double marketPrice
     */
    public double getMarketPrice(int productID, String cardEdition) {

        ObjectMapper objMapper = new ObjectMapper();
        List<PriceObject> pricingList = new ArrayList<>();

        String urlString = "http://api.tcgplayer.com/v1.32.0/pricing/product/";

        try {
            JsonNode jsonNode = objMapper.readTree(processGetRequest(urlString, productID, false, "GET"));
            JsonNode resultsNode = jsonNode.get("results");

            pricingList = objMapper.readValue(resultsNode.toString(), new TypeReference<List<PriceObject>>() {});

            for (int i = 0; i < pricingList.size(); i++) {
                if (pricingList.get(i).getSubTypeName().equalsIgnoreCase(cardEdition)){
                    double marketPrice = (double) pricingList.get(i).getMarketPrice();
                    return marketPrice;
                }
            }

        } catch (JsonProcessingException e) {
            logger.error(e);
       }

        return 0;
    }

    public String getCardName(int productId) {

        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList = getProductDetails(productId);

        String cardName = "";

        for (int i = 0; i < productDetailsList.size(); i++) {
            cardName = productDetailsList.get(i).getCleanName().trim();
        }
        return cardName;
    }


    public String getCardImage(int productId) {
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList = getProductDetails(productId);

        String cardImage = "";

        for (int i = 0; i < productDetailsList.size(); i++) {
            cardImage = productDetailsList.get(i).getImageUrl();
        }
        return cardImage;
    }

    /**
     * looks up the productId for a card based on name, set, and rarity
     * @param productName
     * @param productSet
     * @param productRarity
     * @return int productId
     */
    public int getProductId(String productName, String productSet, String productRarity) {

        ObjectMapper objMapper = new ObjectMapper();
        int productId = 0;

        String urlString = "http://api.tcgplayer.com/v1.32.0/catalog/categories/2/search";

        try {
            JsonNode jsonNode = objMapper.readTree(processPostRequest(urlString, 0,true, "POST", productName, productSet, productRarity));
            String results = jsonNode.get("results").toPrettyString();
            results = results.replaceAll("\\p{P}","").trim();
            productId = Integer.parseInt(results);
        } catch (JsonProcessingException e) {
            logger.error("The card does not exist");
        }
        return productId;
    }
}
