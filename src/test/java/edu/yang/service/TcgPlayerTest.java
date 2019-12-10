package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void useBearerToken() {
        TcgPlayerAPI newTcgPlayerAPI = new TcgPlayerAPI();
        logger.info(newTcgPlayerAPI.useBearerToken());
    }

    @Test
    void getProductImage() {

        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getProductImage(21876));
            JsonNode resultsNode = jsonNode.get("results");
            logger.info("results path: " + resultsNode.toPrettyString());

        } catch (JsonProcessingException e) {
            logger.error(e);
        }

    }

    @Test
    void getProductPrice() {

        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getMarketPrice(21876));
            JsonNode resultsNode = jsonNode.get("results");
            logger.info("results path: " + resultsNode.toPrettyString());

        } catch (JsonProcessingException e) {
            logger.error(e);
        }
    }
}