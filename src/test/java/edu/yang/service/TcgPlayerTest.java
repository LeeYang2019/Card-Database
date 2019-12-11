package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TcgPlayerTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    TcgPlayerAPI tcgPlayerApi;
    ObjectMapper objMapper;

    /**
     * run these before each test
     */
    @BeforeEach
    void setUp() {
       tcgPlayerApi = new TcgPlayerAPI();
       objMapper = new ObjectMapper();
    }


    @Test
    void getClientCredentials() {
    }

    @Test
    void getProductImage() {


        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getProductImage(21876));
            JsonNode resultsNode = jsonNode.get("results");
            logger.info("results path: " + resultsNode.toPrettyString());
            System.out.println("results path: " + resultsNode.toPrettyString());

            ProductDetails newProduct = new ProductDetails();

            System.out.println("clean name: ");

            newProduct = objMapper.readValue(resultsNode.toString(), ProductDetails.class);

            System.out.println("clean name: ");
            System.out.println("imageUrl: " + newProduct.getImageUrl());

        } catch (JsonProcessingException e) {
            logger.error(e);
        }

    }

    @Test
    void getProductPrice() {

        List<PriceObject> pricingList = new ArrayList<>();

        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getMarketPrice(21876));
            JsonNode resultsNode = jsonNode.get("results");
            logger.info(resultsNode.toPrettyString());
            System.out.println(resultsNode.toPrettyString());

            pricingList = objMapper.readValue(resultsNode.toString(), new TypeReference<List<PriceObject>>() {});

            for (int i = 0; i < pricingList.size(); i++) {
                if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("1st Edition")) {
                    System.out.println("The marketprice is : " + pricingList.get(i).getMarketPrice());
                } else if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("unlimited")) {
                    System.out.println("The marketprice is : " + pricingList.get(i).getMarketPrice());
                }
            }


        } catch (JsonProcessingException e) {
            logger.error(e);
        }
    }

    @Test
    void searchCard() {

        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getCardDetails("Blue Eyes White Dragon", "The Legend of Blue Eyes White Dragon", "Ultra"));
            logger.info(jsonNode.toPrettyString());
            System.out.println(jsonNode.toPrettyString());

            String results = jsonNode.get("results").toPrettyString();
            results = results.replaceAll("\\p{P}","").trim();

            int productID = Integer.parseInt(results);
            System.out.println("returned productID : " + productID);

        } catch (JsonProcessingException e) {
            logger.error(e);
        }
    }
}