package edu.yang.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.yang.entity.YugiohCard;
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
    void getProductImage() {
        String image = tcgPlayerApi.getCardImage(200820);
    }

    @Test
    void getProductPrice() {
        double marketPrice = tcgPlayerApi.getMarketPrice(200820, "1st Edition");
    }

    @Test
    void searchCard() {

        int id = tcgPlayerApi.getProductId("Galaxy Soldier", "World Superstars", "Secret");
        System.out.println("productId : " + id);
    }

    @Test
    void getSearchResultsSuccess() {

        YugiohCard newCard = new YugiohCard();
        newCard.setUser(null);
        newCard.setCardName("Dark Magician");
        newCard.setCardType("Monster");
        newCard.setCardRarity("Ultra");
        newCard.setCardEdition("1st Edition");

        String cardSet = "The Legend of Blue Eyes White Dragon";
        newCard.setCardSet("LOB");

        newCard.setIndex("001");


        int id = tcgPlayerApi.getProductId(newCard.getCardName(), cardSet, newCard.getCardRarity());

    }
}