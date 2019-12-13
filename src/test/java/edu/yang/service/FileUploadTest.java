package edu.yang.service;

import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class FileUploadTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    UploadFileReader newTest;

    @BeforeEach
    void setUp() {
        newTest = new UploadFileReader();
    }

    @Test
    void readExcelFile() {
        String excelFilePath = "docs/Cards.xlsx";
        List<YugiohCard> list = newTest.readExcelFile(excelFilePath, null);

        logger.info("Cards from " + excelFilePath);
        for (YugiohCard card: list) {
            logger.info(card.toString());
        }
    }

    @Test
    void readCardSetFileSuccess() {
       Map<String, String> cardSetsMap = newTest.readFile();

       for (Map.Entry<String, String> entry : cardSetsMap.entrySet()) {
           System.out.println("Key = " + entry.getKey() +
                   ", Value = " + entry.getValue());
       }
    }
}