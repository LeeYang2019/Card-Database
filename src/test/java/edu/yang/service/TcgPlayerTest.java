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
    void getClientCredentials() {
    }

    @Test
    void getProductImage() {

        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList = tcgPlayerApi.getProductDetails(200820);

        for (int i = 0; i < productDetailsList.size(); i++) {
            System.out.println(productDetailsList.get(i).getCleanName());
            System.out.println(productDetailsList.get(i).getImageUrl());
        }

    }

    @Test
    void getProductPrice() {
        List<PriceObject> pricingList = new ArrayList<>();
        pricingList = tcgPlayerApi.getMarketPrice(200820);

        for (int i = 0; i < pricingList.size(); i++) {
            if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("1st Edition")) {
                System.out.println(pricingList.get(i).getMarketPrice());
            }
        }

    }

    @Test
    void searchCard() {

        int id = tcgPlayerApi.getProductId("Starliege Seyfert", "Chaos Impact", "Secret");
        System.out.println("productId : " + id);
    }

    @Test
    void getSearchResultsSuccess() {

        YugiohCard newCard = new YugiohCard();
        newCard.setUser(null);
        newCard.setCardName("Dark Magician");
        newCard.setCardType("Monster");
        newCard.setCardRarity("Ultra");

        String cardSet = "The Legend of Blue Eyes White Dragon";
        newCard.setCardSet("LOB");

        newCard.setIndex("001");


        int id = tcgPlayerApi.getProductId(newCard.getCardName(), cardSet, newCard.getCardRarity());

        List<ProductDetails> productDetailsList = tcgPlayerApi.getProductDetails(id);

        List<PriceObject> pricingList  = tcgPlayerApi.getMarketPrice(id);


        for (int i = 0; i < productDetailsList.size(); i++) {
            if (productDetailsList.get(i).getCleanName().equalsIgnoreCase(newCard.getCardName())) {
                newCard.setImage(productDetailsList.get(i).getImageUrl());
            }
        }

        for (int i = 0; i < pricingList.size(); i++) {
            if (pricingList.get(i).getSubTypeName().equalsIgnoreCase("1st Edition")){
                double marketPrice = (double) pricingList.get(i).getMarketPrice();
                newCard.setPrice(marketPrice);
            }
        }

        newCard.setQuantity(1);
        newCard.setStatus("Unsold");

        System.out.println(newCard.toString());
    }
}