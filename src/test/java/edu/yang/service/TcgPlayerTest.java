package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.json.stream.JsonParser;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TcgPlayerTest {

    private final Logger logger = LogManager.getLogger(this.getClass());

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
        TcgPlayer newTcgPlayer = new TcgPlayer();
        ObjectMapper object = new ObjectMapper();

        try {
            JsonNode jsonNode = object.readTree(newTcgPlayer.getProductImage());
            JsonNode resultsNode = jsonNode.get("results");
            logger.info(resultsNode.toPrettyString());

            String url = resultsNode.get("imageUrl").toPrettyString();

            logger.info(url);

/*
            for (Map.Entry<String, String> element: map.entrySet()) {
                //logger.info("key: " + element.getKey() + ", value: " + element.getValue());
            }
*/
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}