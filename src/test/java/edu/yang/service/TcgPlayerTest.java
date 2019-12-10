package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.Json;
import javax.json.stream.JsonParser;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TcgPlayerTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    TcgPlayer tcgPlayerApi;
    ObjectMapper objMapper;

    /**
     * run these before each test
     */
    @BeforeEach
    void setUp() {
       tcgPlayerApi = new TcgPlayer();
       objMapper = new ObjectMapper();
    }


    @Test
    void getClientCredentials() {
    }

    @Test
    void useBearerToken() {
        TcgPlayer newTcgPlayer = new TcgPlayer();
        logger.info(newTcgPlayer.useBearerToken());
    }

    @Test
    void getProductImage() {

        try {
            JsonNode jsonNode = objMapper.readTree(tcgPlayerApi.getProductImage(21876));
            JsonNode resultsNode = jsonNode.get("results");
            logger.info("results path: " + resultsNode.toPrettyString());

            //String imageUrl = resultsNode.get("imageUrl")

        } catch (JsonProcessingException e) {
            logger.error(e);
        }

    }
}